package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
    private static String url;
    private static String user;
    private static String password;

    private static Connection connection;

    // Initializer
    public static void createConnection(String url, String user, String password) throws SQLException {
        url = url;
        user = user;
        password = password;

        connection = DriverManager.getConnection(url, user, password);
    }

    // Getters & Setters
    public static Connection getConnection() {
        return connection;
    }
}
