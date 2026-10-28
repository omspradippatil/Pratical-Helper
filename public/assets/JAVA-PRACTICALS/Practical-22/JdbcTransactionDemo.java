import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.sql.SQLException;

public class JdbcTransactionDemo {
    private static final String MYSQL_URL = "jdbc:mysql://localhost:3306/javadb?createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
    private static final String MYSQL_USER = "root";
    private static final String MYSQL_PASSWORD = "";

    private static final String H2_URL = "jdbc:h2:mem:bankdb;DB_CLOSE_DELAY=-1";
    private static final String H2_USER = "sa";
    private static final String H2_PASSWORD = "";

    private static boolean isMySql = false;

    public static Connection getConnection() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(MYSQL_URL, MYSQL_USER, MYSQL_PASSWORD);
            isMySql = true;
            System.out.println("Connected to local MySQL database successfully.");
            System.out.println("Database Engine: " + conn.getMetaData().getDatabaseProductName() + " " + conn.getMetaData().getDatabaseProductVersion());
            return conn;
        } catch (SQLException e) {
            isMySql = false;
            System.out.println("[Notice] Local MySQL not reachable (" + e.getMessage() + "). Falling back to embedded H2 database.");
            return DriverManager.getConnection(H2_URL, H2_USER, H2_PASSWORD);
        }
    }

    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("DROP TABLE IF EXISTS accounts");
                stmt.execute("CREATE TABLE accounts (acc_no INT PRIMARY KEY, holder VARCHAR(50), balance DOUBLE)");
                stmt.execute("INSERT INTO accounts VALUES (101, 'Alice', 1000.0)");
                stmt.execute("INSERT INTO accounts VALUES (102, 'Bob', 500.0)");

                if (isMySql) {
                    stmt.execute("DROP PROCEDURE IF EXISTS GetAccountBalance");
                    stmt.execute("CREATE PROCEDURE GetAccountBalance(IN p_acc_no INT, OUT p_balance DOUBLE) " +
                                 "BEGIN " +
                                 "  SELECT balance INTO p_balance FROM accounts WHERE acc_no = p_acc_no; " +
                                 "END");
                    System.out.println("MySQL Stored Procedure 'GetAccountBalance' created successfully.");
                } else {
                    stmt.execute("CREATE ALIAS IF NOT EXISTS GET_BALANCE FOR \"JdbcTransactionDemo.getBalanceProcedure\"");
                }
            }

            System.out.println("\nExecuting Transaction: Transferring 300 from Alice (101) to Bob (102)");
            conn.setAutoCommit(false);
            try {
                try (PreparedStatement debit = conn.prepareStatement("UPDATE accounts SET balance = balance - ? WHERE acc_no = ?")) {
                    debit.setDouble(1, 300.0);
                    debit.setInt(2, 101);
                    debit.executeUpdate();
                }

                try (PreparedStatement credit = conn.prepareStatement("UPDATE accounts SET balance = balance + ? WHERE acc_no = ?")) {
                    credit.setDouble(1, 300.0);
                    credit.setInt(2, 102);
                    credit.executeUpdate();
                }

                conn.commit();
                System.out.println("Transaction committed successfully.");
            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Transaction rolled back due to error: " + e.getMessage());
            } finally {
                conn.setAutoCommit(true);
            }

            System.out.println("\nCalling Stored Procedure using CallableStatement:");
            if (isMySql) {
                try (CallableStatement cstmt = conn.prepareCall("{CALL GetAccountBalance(?, ?)}")) {
                    cstmt.setInt(1, 101);
                    cstmt.registerOutParameter(2, Types.DOUBLE);
                    cstmt.execute();
                    System.out.println("Procedure Result - Alice New Balance: " + cstmt.getDouble(2));

                    cstmt.setInt(1, 102);
                    cstmt.registerOutParameter(2, Types.DOUBLE);
                    cstmt.execute();
                    System.out.println("Procedure Result - Bob New Balance: " + cstmt.getDouble(2));
                }
            } else {
                try (CallableStatement cstmt = conn.prepareCall("{CALL GET_BALANCE(?)}")) {
                    cstmt.setInt(1, 101);
                    ResultSet rs1 = cstmt.executeQuery();
                    if (rs1.next()) System.out.println("Procedure Result - Alice New Balance: " + rs1.getDouble("balance"));

                    cstmt.setInt(1, 102);
                    ResultSet rs2 = cstmt.executeQuery();
                    if (rs2.next()) System.out.println("Procedure Result - Bob New Balance: " + rs2.getDouble("balance"));
                }
            }

        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }
    }

    public static ResultSet getBalanceProcedure(Connection conn, int accNo) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT balance FROM accounts WHERE acc_no = ?");
        stmt.setInt(1, accNo);
        return stmt.executeQuery();
    }
}
