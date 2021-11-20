package ui.controller.modalsController;


import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import util.UserInterfaceUtils;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import dto.VehicleDto;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import service.ServicesLocator;
import service.VehicleServices;

public class VehicleModal implements Initializable {



    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField tf_vehicle_id;

    @FXML
    private JFXTextField tf_capacity_with_bagage;

    @FXML
    private JFXTextField tf_capacity_without_bagage;

    @FXML
    private JFXDatePicker dp_production_date;

    @FXML
    private Label errormessage_label;


    @FXML
    private JFXComboBox<?> cb_vehicle_brand;

    @FXML
    private JFXButton btn_insert_vehicle;

    @FXML
    private JFXButton btn_cancel;


    @FXML
    void cancelInsertion(ActionEvent event) {

    }

    @FXML
    void insertVehicle(ActionEvent event) {

    }

   /* @FXML
    void initialize() {
        assert btn_insert_vehicle != null : "fx:id=\"btn_insert_vehicle\" was not injected: check your FXML file 'vehicle_modal.fxml'.";
        assert btn_cancel != null : "fx:id=\"btn_cancel\" was not injected: check your FXML file 'vehicle_modal.fxml'.";

    }*/

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator("This field is required");
        tf_vehicle_id.setValidators(requiredFieldValidator);
        tf_capacity_without_bagage.setValidators(requiredFieldValidator);
        tf_capacity_with_bagage.setValidators(requiredFieldValidator);



    }

    public void addComboBoxItems(){

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

                        if (currentVehicle.getId().equals(tf_vehicle_id.getText())) {
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

                if(vehicleDto == null) {
                    VehicleServices vehicleServices=ServicesLocator.getVehicleServices();
                    vehicleServices.insert(vehicleDto);
                } else
                    UserInterfaceUtils.showTemporaryLabel(errormessage_label, "Vehicle already exist", Color.RED, 5);

            } catch (InterruptedException | ExecutionException | SQLException e) {
                e.printStackTrace();
            }



        });

        new Thread(checkVehicleExistenceTask).start();
    }


}

