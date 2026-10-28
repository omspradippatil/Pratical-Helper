import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcCrudDemo {
    private static final String MYSQL_URL = "jdbc:mysql://localhost:3306/javadb?createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
    private static final String MYSQL_USER = "root";
    private static final String MYSQL_PASSWORD = "";

    private static final String H2_URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
    private static final String H2_USER = "sa";
    private static final String H2_PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(MYSQL_URL, MYSQL_USER, MYSQL_PASSWORD);
            System.out.println("Connected to local MySQL database successfully.");
            System.out.println("Database Engine: " + conn.getMetaData().getDatabaseProductName() + " " + conn.getMetaData().getDatabaseProductVersion());
            return conn;
        } catch (SQLException e) {
            System.out.println("[Notice] Local MySQL not reachable (" + e.getMessage() + "). Falling back to embedded H2 database.");
            return DriverManager.getConnection(H2_URL, H2_USER, H2_PASSWORD);
        }
    }

    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("DROP TABLE IF EXISTS students");
                String createTable = "CREATE TABLE students (id INT PRIMARY KEY, name VARCHAR(50), marks DOUBLE)";
                stmt.execute(createTable);
                System.out.println("Table 'students' created successfully.");
            }

            String insertSQL = "INSERT INTO students (id, name, marks) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                pstmt.setInt(1, 1);
                pstmt.setString(2, "Rahul");
                pstmt.setDouble(3, 88.5);
                pstmt.executeUpdate();

                pstmt.setInt(1, 2);
                pstmt.setString(2, "Priya");
                pstmt.setDouble(3, 92.0);
                pstmt.executeUpdate();
                System.out.println("Records inserted using PreparedStatement.");
            }

            System.out.println("\nReading Records using Statement:");
            String selectSQL = "SELECT * FROM students";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(selectSQL)) {
                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") + ", Marks: " + rs.getDouble("marks"));
                }
            }

            String updateSQL = "UPDATE students SET marks = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
                pstmt.setDouble(1, 95.0);
                pstmt.setInt(2, 1);
                pstmt.executeUpdate();
                System.out.println("\nUpdated Rahul's marks to 95.0 using PreparedStatement.");
            }

            String deleteSQL = "DELETE FROM students WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
                pstmt.setInt(1, 2);
                pstmt.executeUpdate();
                System.out.println("Deleted record with ID 2 using PreparedStatement.");
            }

            System.out.println("\nFinal Records in Database:");
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(selectSQL)) {
                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") + ", Marks: " + rs.getDouble("marks"));
                }
            }

        } catch (SQLException e) {
            System.err.println("JDBC Error: " + e.getMessage());
        }
    }
}
