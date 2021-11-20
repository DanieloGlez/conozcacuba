package service;

import service.functionality.ServicesAux;
import service.functionality.UserServices;
import util.ConfigurationUtils;
import util.DatabaseUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class ServicesLocator {
    private static UserServices userServices;
    private static ServicesAux servicesCombo;
    private static VehicleServices vehicleServices;

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


    public static ServicesAux getServicesCombo(){
        if(servicesCombo==null)
            servicesCombo=new ServicesAux();
        return servicesCombo;
    }
}
