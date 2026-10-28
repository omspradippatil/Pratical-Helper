import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.sql.SQLException;
import java.util.Scanner;

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
        Scanner sc = new Scanner(System.in);

        System.out.println("=== JDBC Transaction Management & CallableStatement (User Input) ===");

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

            System.out.println("\nInitial Account Balances: Alice (101) = Rs.1000.0 | Bob (102) = Rs.500.0");
            System.out.print("Enter amount to transfer from Alice (101) to Bob (102): Rs.");
            double transferAmount = sc.nextDouble();

            System.out.println("\n--- Executing Atomic Transaction ---");
            conn.setAutoCommit(false);
            try {
                if (transferAmount > 1000.0) {
                    throw new SQLException("Insufficient funds in Alice's account (Max Rs.1000.0).");
                }

                try (PreparedStatement debit = conn.prepareStatement("UPDATE accounts SET balance = balance - ? WHERE acc_no = ?")) {
                    debit.setDouble(1, transferAmount);
                    debit.setInt(2, 101);
                    debit.executeUpdate();
                }

                try (PreparedStatement credit = conn.prepareStatement("UPDATE accounts SET balance = balance + ? WHERE acc_no = ?")) {
                    credit.setDouble(1, transferAmount);
                    credit.setInt(2, 102);
                    credit.executeUpdate();
                }

                conn.commit();
                System.out.println("Transaction committed successfully: Transferred Rs." + transferAmount + ".");
            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Transaction rolled back due to error: " + e.getMessage());
            } finally {
                conn.setAutoCommit(true);
            }

            // Stored Procedure Call via CallableStatement
            System.out.println("\n--- Invoke Stored Procedure via CallableStatement ---");
            System.out.print("Enter account number to fetch updated balance (101 or 102): ");
            int queryAcc = sc.nextInt();

            if (isMySql) {
                try (CallableStatement cstmt = conn.prepareCall("{CALL GetAccountBalance(?, ?)}")) {
                    cstmt.setInt(1, queryAcc);
                    cstmt.registerOutParameter(2, Types.DOUBLE);
                    cstmt.execute();
                    double bal = cstmt.getDouble(2);
                    System.out.println("Procedure Result -> Account " + queryAcc + " New Balance: Rs." + bal);
                }
            } else {
                try (CallableStatement cstmt = conn.prepareCall("{CALL GET_BALANCE(?)}")) {
                    cstmt.setInt(1, queryAcc);
                    ResultSet rs = cstmt.executeQuery();
                    if (rs.next()) {
                        System.out.println("Procedure Result -> Account " + queryAcc + " New Balance: Rs." + rs.getDouble("balance"));
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }

        sc.close();
    }

    public static ResultSet getBalanceProcedure(Connection conn, int accNo) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT balance FROM accounts WHERE acc_no = ?");
        stmt.setInt(1, accNo);
        return stmt.executeQuery();
    }
}
