import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

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
        Scanner sc = new Scanner(System.in);

        System.out.println("=== JDBC MySQL CRUD Operations (User Input) ===");

        try (Connection conn = getConnection()) {
            // 1. DDL: Create Table using Statement
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("DROP TABLE IF EXISTS students");
                stmt.execute("CREATE TABLE students (id INT PRIMARY KEY, name VARCHAR(50), marks DOUBLE)");
                System.out.println("Table 'students' created in database.");
            }

            // 2. CREATE (Insert) using PreparedStatement from user input
            System.out.print("\nHow many students do you want to insert? ");
            int count = sc.nextInt();

            String insertSQL = "INSERT INTO students (id, name, marks) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                for (int i = 0; i < count; i++) {
                    System.out.println("Enter details for Student #" + (i + 1) + ":");
                    System.out.print("  ID: ");
                    int id = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("  Name: ");
                    String name = sc.nextLine();
                    System.out.print("  Marks: ");
                    double marks = sc.nextDouble();

                    pstmt.setInt(1, id);
                    pstmt.setString(2, name);
                    pstmt.setDouble(3, marks);
                    pstmt.executeUpdate();
                }
                System.out.println("All " + count + " records inserted successfully using PreparedStatement.");
            }

            // 3. READ (Select) using Statement
            System.out.println("\n--- Current Records in Database (via Statement) ---");
            displayRecords(conn);

            // 4. UPDATE using PreparedStatement from user input
            System.out.println("\n--- Update Student Marks ---");
            System.out.print("Enter Student ID to update marks: ");
            int updateId = sc.nextInt();
            System.out.print("Enter New Marks: ");
            double newMarks = sc.nextDouble();

            String updateSQL = "UPDATE students SET marks = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
                pstmt.setDouble(1, newMarks);
                pstmt.setInt(2, updateId);
                int rows = pstmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("Updated marks for ID " + updateId + " to " + newMarks + ".");
                } else {
                    System.out.println("Student ID " + updateId + " not found.");
                }
            }

            // 5. DELETE using PreparedStatement from user input
            System.out.println("\n--- Delete Student Record ---");
            System.out.print("Enter Student ID to delete: ");
            int deleteId = sc.nextInt();

            String deleteSQL = "DELETE FROM students WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
                pstmt.setInt(1, deleteId);
                int rows = pstmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("Deleted record with ID " + deleteId + ".");
                } else {
                    System.out.println("Student ID " + deleteId + " not found.");
                }
            }

            // 6. Final READ
            System.out.println("\n--- Final Database Table State ---");
            displayRecords(conn);

        } catch (SQLException e) {
            System.err.println("JDBC Error: " + e.getMessage());
        }

        sc.close();
    }

    private static void displayRecords(Connection conn) throws SQLException {
        String selectSQL = "SELECT * FROM students";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {
            boolean hasRecords = false;
            while (rs.next()) {
                hasRecords = true;
                System.out.println("ID: " + rs.getInt("id") + " | Name: " + rs.getString("name") + " | Marks: " + rs.getDouble("marks"));
            }
            if (!hasRecords) {
                System.out.println("No records found in table.");
            }
        }
    }
}
