package Library;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    // JDBC URL for connecting to the database
    private static final String URL = "jdbc:mysql://localhost:3306/task2"; // Adjusted to your database name
    private static final String USER = "root"; // Replace with your MySQL username
    private static final String PASSWORD = "Jawad4693!"; // Replace with your MySQL password

    // Method to establish a connection to the database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}