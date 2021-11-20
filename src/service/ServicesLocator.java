package service;

import service.functionality.UserServices;
import service.nomenclators.ServiceTypeServices;
import util.ConfigurationUtils;
import util.DatabaseUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class ServicesLocator {
    private static UserServices userServices;
    private static VehicleServices vehicleServices;
    private static VehicleBrandServices vehicleBrandServices;
    private static ServiceTypeServices serviceTypeServices;

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
        if (userServices == null)
            userServices = new UserServices();

        return userServices;
    }

    public static VehicleServices getVehicleServices() {
        if (vehicleServices == null)
            vehicleServices = new VehicleServices();

        return vehicleServices;
    }

    public static VehicleBrandServices getVehicleBrandServices() {
        if (vehicleBrandServices == null)
            vehicleBrandServices = new VehicleBrandServices();

        return vehicleBrandServices;
    }

    public static ServiceTypeServices getServiceTypeServices() {
        if (serviceTypeServices == null)
            serviceTypeServices = new ServiceTypeServices();

        return serviceTypeServices;
    }
}
