package service;

import service.functionality.UserServices;
import util.ConfigurationUtils;
import util.DatabaseUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class ServicesLocator {
    private static UserServices userServices;

    // Connection
    public static Connection getConnection() throws SQLException {
        return new DatabaseUtils(
                ConfigurationUtils.getServer(),
                ConfigurationUtils.getDatabase(),
                ConfigurationUtils.getUser(),
                ConfigurationUtils.getPassword()
        ).getConnection();
    }

    // Services
    public static UserServices getUserServices() {
        if(userServices == null)
            userServices = new UserServices();

        return userServices;
    }
}
