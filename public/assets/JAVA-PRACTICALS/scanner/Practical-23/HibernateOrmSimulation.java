import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

@Retention(RetentionPolicy.RUNTIME)
@interface Entity {}

@Retention(RetentionPolicy.RUNTIME)
@interface Table {
    String name();
}

@Retention(RetentionPolicy.RUNTIME)
@interface Id {}

@Entity
@Table(name = "products")
class Product {
    @Id
    private int id;
    private String name;
    private double price;

    public Product() {}

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
}

class SimpleEntityManager {
    private Connection conn;

    public SimpleEntityManager(Connection conn) {
        this.conn = conn;
    }

    public void persist(Object entity) throws Exception {
        Class<?> clazz = entity.getClass();
        if (!clazz.isAnnotationPresent(Entity.class)) {
            throw new IllegalArgumentException("Class is not an @Entity");
        }
        Table table = clazz.getAnnotation(Table.class);
        String tableName = table != null ? table.name() : clazz.getSimpleName().toLowerCase();

        Field[] fields = clazz.getDeclaredFields();
        StringBuilder sql = new StringBuilder("INSERT INTO ").append(tableName).append(" (");
        StringBuilder values = new StringBuilder(" VALUES (");

        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            sql.append(fields[i].getName());
            values.append("?");
            if (i < fields.length - 1) {
                sql.append(", ");
                values.append(", ");
            }
        }
        sql.append(")").append(values).append(")");

        try (PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
            for (int i = 0; i < fields.length; i++) {
                pstmt.setObject(i + 1, fields[i].get(entity));
            }
            pstmt.executeUpdate();
            System.out.println("Hibernate/ORM Persisted Entity -> " + clazz.getSimpleName() + " into MySQL table '" + tableName + "'");
        }
    }
}

public class HibernateOrmSimulation {
    private static final String MYSQL_URL = "jdbc:mysql://localhost:3306/javadb?createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
    private static final String MYSQL_USER = "root";
    private static final String MYSQL_PASSWORD = "";

    private static final String H2_URL = "jdbc:h2:mem:ormdb;DB_CLOSE_DELAY=-1";
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

        System.out.println("=== Hibernate / ORM Database Persistence (User Input) ===");

        try (Connection conn = getConnection()) {
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("DROP TABLE IF EXISTS products");
                stmt.execute("CREATE TABLE products (id INT PRIMARY KEY, name VARCHAR(50), price DOUBLE)");
            }

            SimpleEntityManager em = new SimpleEntityManager(conn);

            System.out.print("\nHow many products do you want to persist into database? ");
            int n = sc.nextInt();

            for (int i = 0; i < n; i++) {
                System.out.println("\nEnter Product #" + (i + 1) + " details:");
                System.out.print("  Product ID: ");
                int id = sc.nextInt();
                sc.nextLine(); // consume newline
                System.out.print("  Product Name: ");
                String name = sc.nextLine();
                System.out.print("  Product Price: Rs.");
                double price = sc.nextDouble();

                Product p = new Product(id, name, price);
                em.persist(p);
            }

            System.out.println("\n--- Querying Saved Entities from MySQL Database ---");
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM products")) {
                while (rs.next()) {
                    System.out.println("Product ID: " + rs.getInt("id") + " | Name: " + rs.getString("name") + " | Price: Rs." + rs.getDouble("price"));
                }
            }

        } catch (Exception e) {
            System.err.println("Hibernate Error: " + e.getMessage());
        }

        sc.close();
    }
}
