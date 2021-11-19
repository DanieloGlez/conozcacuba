package service;

import service.functionality.ServicesCombo;
import service.functionality.UserServices;
import util.ConfigurationUtils;
import util.ConstantUtils;
import util.DatabaseUtils;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;

public class ServicesLocator {
    private static UserServices userServices;
    private static DailyActivityServices dailyActivityServices;

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

    public static DailyActivityServices getDailyActivityServices() {
        if(dailyActivityServices == null)
            dailyActivityServices = new DailyActivityServices();

        return dailyActivityServices;
    }

    // Utils
    /*public static Services<?> getServicesByName(String servicesName) {
        Class dtoClass = ConstantUtils.getTableNames().get(servicesName);

        for (Method method : dtoClass.getMethods()) {

        }
    }*/
}
