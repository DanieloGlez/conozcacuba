package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtils {
    private final Connection connection;

    public DatabaseUtils(String server, String database, String user, String password) throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:postgresql://" + server + ":5432/" + database,
                user,
                password
        );
    }

    public Connection getConnection() {
        return connection;
    }
}
