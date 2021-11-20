package service;

import service.functionality.ServicesCombo;
import service.functionality.UserServices;
import util.ConfigurationUtils;
import util.DatabaseUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class ServicesLocator {
    private static UserServices userServices;
    private static ServicesCombo servicesCombo;
    private static VehicleServices vehicleServices;
    private static VehicleBrandServices vehicleBrandServices;

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
public static VehicleServices getVehicleServices() {
        if(vehicleServices == null)
            vehicleServices = new VehicleServices();

        return vehicleServices;
    }


    public static ServicesCombo getServicesCombo(){
        if(servicesCombo==null)
            servicesCombo=new ServicesCombo();
        return servicesCombo;
    }

    public static VehicleBrandServices getVehicleBrandServices() {
        if(vehicleBrandServices == null)
            vehicleBrandServices = new VehicleBrandServices();

        return vehicleBrandServices;
    }
}
