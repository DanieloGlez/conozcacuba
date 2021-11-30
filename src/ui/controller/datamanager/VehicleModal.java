package ui.controller.datamanager;


import java.net.URL;
import java.sql.SQLException;
import java.util.*;

import com.jfoenix.validation.RegexValidator;
import dto.Dto;
import javafx.stage.Stage;
import dto.nom.*;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import dto.VehicleDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import service.*;
import util.Validator;

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
    private JFXComboBox<VehicleBrandDto> vehiclebrand_jfxcombobox;

    private Validator p= new Validator();

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
            RegexValidator regexNumericValidator= new RegexValidator("This field is not a number");
            regexNumericValidator.setRegexPattern("[+-]?\\d*(\\.\\d+)?");
            RegexValidator regexRegistrationValidator= new RegexValidator("Registration field is incorrect");
            regexRegistrationValidator.setRegexPattern("^[B,F,M,P]{1}\\[1-9]{5}");
            RequiredFieldValidator requiredFieldValidator= new RequiredFieldValidator("This field is required");

            capacitywithoutbaggage_jfxtextfield.getValidators().add(regexNumericValidator);
            capacitywithoutbaggage_jfxtextfield.focusedProperty().addListener((o,oldVal,newVal)->{
                if(!newVal) capacitywithoutbaggage_jfxtextfield.validate();
            });
            capacitywithoutbaggage_jfxtextfield.getValidators().add(requiredFieldValidator);

            capacitywithbaggage_jfxtextfield.getValidators().add(regexNumericValidator);
            capacitywithbaggage_jfxtextfield.focusedProperty().addListener((o,oldVal,newVal)->{
                if(!newVal) capacitywithbaggage_jfxtextfield.validate();
            });
            capacitywithbaggage_jfxtextfield.getValidators().add(requiredFieldValidator);

            chapavehicle_jfxtextfield.getValidators().add(requiredFieldValidator);
            //chapavehicle_jfxtextfield.getValidators().add(regexRegistrationValidator);
            chapavehicle_jfxtextfield.focusedProperty().addListener((o,oldVal,newVal)->{
                if(!newVal) chapavehicle_jfxtextfield.validate();
            });

            productiondate_jfxdatepicker.getValidators().add(requiredFieldValidator);
            productiondate_jfxdatepicker.focusedProperty().addListener((o,oldVal,newVal)->{
                if(!newVal) productiondate_jfxdatepicker.validate();
            });

            vehiclebrand_jfxcombobox.getValidators().add(requiredFieldValidator);
            vehiclebrand_jfxcombobox.focusedProperty().addListener((o,oldVal,newVal)->{
                if(!newVal) vehiclebrand_jfxcombobox.validate();
            });




        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void insert(ActionEvent event) throws SQLException {

        Validator p= new Validator();

        ArrayList<JFXTextField> prueba1=new ArrayList<>();
        prueba1.add(capacitywithoutbaggage_jfxtextfield);
        ArrayList<JFXTextField> prueba2=new ArrayList<>();
        prueba2.add(capacitywithbaggage_jfxtextfield);
        if (p.validateNumberInteger(capacitywithoutbaggage_jfxtextfield) && p.validateNumberInteger(capacitywithbaggage_jfxtextfield) && p.validateRegistration(chapavehicle_jfxtextfield) && p.validateCombobox(vehiclebrand_jfxcombobox)) {

            ServicesLocator.getVehicleServices().insert(new VehicleDto(
                    0,
                    chapavehicle_jfxtextfield.getText(),
                    vehiclebrand_jfxcombobox.getValue(),
                    Integer.parseInt(capacitywithoutbaggage_jfxtextfield.getText()),
                    Integer.parseInt(capacitywithbaggage_jfxtextfield.getText()),
                    productiondate_jfxdatepicker.getValue()));
            ((Stage) chapavehicle_jfxtextfield.getScene().getWindow()).close();
        }
        else{


        }



    }

    @Override
    public void update(ActionEvent event) throws SQLException {
        Validator p= new Validator();

        if (p.validateNumberInteger(capacitywithoutbaggage_jfxtextfield) && p.validateNumberInteger(capacitywithbaggage_jfxtextfield) && p.validateRegistration(chapavehicle_jfxtextfield) && p.validateCombobox(vehiclebrand_jfxcombobox)) {

            VehicleDto vehicleDto = (VehicleDto) dto;
            vehicleDto.setRegistration(chapavehicle_jfxtextfield.getText());
            vehicleDto.setCapacityWithoutBaggage(Integer.parseInt(capacitywithoutbaggage_jfxtextfield.getText()));
            vehicleDto.setCapacityWithBaggage(Integer.parseInt(capacitywithbaggage_jfxtextfield.getText()));
            vehicleDto.setProductionDate(productiondate_jfxdatepicker.getValue());
            vehicleDto.setBrand(vehiclebrand_jfxcombobox.getValue());
            ServicesLocator.getVehicleServices().update(vehicleDto);

            ((Stage) chapavehicle_jfxtextfield.getScene().getWindow()).close();
        }else {

        }




    }


    //function for add items to combobox
    public void addComboBoxItems() throws SQLException {

        vehiclebrand_jfxcombobox.getItems().addAll(ServicesLocator.getVehicleBrandServices().loadAll());
    }


    @Override
    public void setDto(Dto dto) {
        super.setDto(dto);

        if(dto != null) {
            VehicleDto vehicleDtoDto = (VehicleDto) dto;
            chapavehicle_jfxtextfield.setText(vehicleDtoDto.getRegistration());
            capacitywithoutbaggage_jfxtextfield.setText(String.valueOf(vehicleDtoDto.getCapacityWithoutBaggage()));
            capacitywithbaggage_jfxtextfield.setText(String.valueOf(vehicleDtoDto.getCapacityWithBaggage()));
            productiondate_jfxdatepicker.setValue(vehicleDtoDto.getProductionDate());
            vehiclebrand_jfxcombobox.getSelectionModel().select(vehicleDtoDto.getBrand());


        }
    }


}

