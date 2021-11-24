package ui.controller.datamanager;


import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

import javafx.scene.Node;
import javafx.stage.Stage;
import util.UserInterfaceUtils;
import dto.nom.*;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import dto.VehicleDto;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import service.*;

public class VehicleModal extends DataManagerFormController {
    @FXML
    private JFXTextField chapavehicle_jfxtextfield;

    @FXML
    private Label errormessage_label;

    @FXML
    private JFXTextField capacitywithbaggage_jfxtextfield;

    @FXML
    private JFXTextField capacitywithoutbaggage_jfxtextfield;

    @FXML
    private JFXDatePicker productiondate_jfxdatepicker;

    @FXML
    private JFXComboBox<String> vehiclebrand_jfxcombobox;

    /*@FXML
    void insertVehicle(ActionEvent event) throws SQLException {

        ServicesLocator.getVehicleServices().insert(new VehicleDto(
                0,
                tf_vehicle_id.getText(),
                findBrand(),
                Integer.parseInt(tf_capacity_without_bagage.getText()),
                Integer.parseInt(tf_capacity_with_bagage.getText()),
                dp_production_date.getValue()));

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator("This field is required");
        tf_vehicle_id.setValidators(requiredFieldValidator);
        tf_capacity_without_bagage.setValidators(requiredFieldValidator);
        tf_capacity_with_bagage.setValidators(requiredFieldValidator);
        try {
            addComboBoxItems();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }




    public VehicleBrandDto findBrand() throws SQLException {
        LinkedList<VehicleBrandDto> vehicleBrandDtoList = (LinkedList<VehicleBrandDto>) ServicesLocator.getVehicleBrandServices().loadAll();
        Iterator i = vehicleBrandDtoList.iterator();
        boolean found = false;
        VehicleBrandDto currentVehicleBrandDto = null;
        while (i.hasNext() && !found) {
            currentVehicleBrandDto = (VehicleBrandDto) i.next();
            if (currentVehicleBrandDto.getName().equals(cb_vehicle_brand.getValue()))
                found = true;
        }

        return currentVehicleBrandDto;
    }


    void checkVehicleExistence() {
        String inputVehicleId = tf_vehicle_id.getText();

        btn_insert_vehicle.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        Task<VehicleDto> checkVehicleExistenceTask = new Task<VehicleDto>() {
            @Override
            protected VehicleDto call() throws Exception {
                // Return variable
                VehicleDto vehicleDto = null;

                // List with all vehicles and input username
                List<VehicleDto> vehicles = null;

                try {
                    vehicles = ServicesLocator.getVehicleServices().loadAll();
                } catch (SQLException e) {
                    util.UserInterfaceUtils.showTemporaryLabel(errormessage_label, "Unknown error", Color.RED, 5);
                }

                // Search the input vehicle inside the vehicles list
                if (vehicles != null) {
                    Iterator<VehicleDto> iterator = vehicles.iterator();
                    boolean found = false;
                    while (iterator.hasNext() && !found) {
                        VehicleDto currentVehicle = iterator.next();

                        if (currentVehicle.getRegistration().equals(tf_vehicle_id.getText())) {
                            vehicleDto = currentVehicle;
                            found = true;
                        }
                    }
                }

                return vehicleDto;
            }
        };

        checkVehicleExistenceTask.setOnSucceeded(event -> {
            try {
                VehicleDto vehicleDto = checkVehicleExistenceTask.get();

                if (vehicleDto == null) {
                    VehicleServices vehicleServices = ServicesLocator.getVehicleServices();
                    vehicleServices.insert(vehicleDto);
                } else
                    UserInterfaceUtils.showTemporaryLabel(errormessage_label, "Vehicle already exist", Color.RED, 5);

            } catch (InterruptedException | ExecutionException | SQLException e) {
                e.printStackTrace();
            }


        });

        new Thread(checkVehicleExistenceTask).start();
    }
*/

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            addComboBoxItems();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void insert(ActionEvent event) throws SQLException {

        ServicesLocator.getVehicleServices().insert(new VehicleDto(
                0,
                chapavehicle_jfxtextfield.getText(),
                findBrand(),
                Integer.parseInt(capacitywithoutbaggage_jfxtextfield.getText()),
                Integer.parseInt(capacitywithbaggage_jfxtextfield.getText()),
                productiondate_jfxdatepicker.getValue()));
    }

    @Override
    public void update(ActionEvent event) {

    }


    public void addComboBoxItems() throws SQLException {
        LinkedList<VehicleBrandDto> t = (LinkedList<VehicleBrandDto>) ServicesLocator.getVehicleBrandServices().loadAll();

        Iterator<VehicleBrandDto> i = t.iterator();

        while (i.hasNext()) {

                vehiclebrand_jfxcombobox.getItems().add(i.next().getName());

        }

    }



   //function to find the VehicleBrandDto that is going
   // to be passed as a parameter
   // to the vehicle insert function
    public VehicleBrandDto findBrand() throws SQLException {
        LinkedList<VehicleBrandDto> vehicleBrandDtoList = (LinkedList<VehicleBrandDto>) ServicesLocator.getVehicleBrandServices().loadAll();
        Iterator i = vehicleBrandDtoList.iterator();
        boolean found = false;
        VehicleBrandDto currentVehicleBrandDto = null;
        while (i.hasNext() && !found) {
            currentVehicleBrandDto = (VehicleBrandDto) i.next();
            if (currentVehicleBrandDto.getName().equals(vehiclebrand_jfxcombobox.getValue()))
                found = true;
        }

        return currentVehicleBrandDto;
    }

}

