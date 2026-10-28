import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            System.out.println("Hibernate/ORM Persisted Entity -> " + clazz.getSimpleName() + " into table '" + tableName + "'");
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
        try (Connection conn = getConnection()) {
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("DROP TABLE IF EXISTS products");
                stmt.execute("CREATE TABLE products (id INT PRIMARY KEY, name VARCHAR(50), price DOUBLE)");
            }

            SimpleEntityManager em = new SimpleEntityManager(conn);

            Product p1 = new Product(101, "Mechanical Keyboard", 3499.0);
            Product p2 = new Product(102, "Wireless Gaming Mouse", 1999.0);

            em.persist(p1);
            em.persist(p2);

            System.out.println("\nReading back saved records from database table 'products':");
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM products")) {
                while (rs.next()) {
                    System.out.println("Product ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") + ", Price: Rs." + rs.getDouble("price"));
                }
            }

        } catch (Exception e) {
            System.err.println("Hibernate/ORM Error: " + e.getMessage());
        }
    }
}
