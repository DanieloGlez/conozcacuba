package ui.controller.datamanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RegexValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import com.sun.org.apache.xpath.internal.operations.Mod;
import dto.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import service.ServicesLocator;
import util.Validator;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ModalityTransportKm extends DataManagerFormController {
    Validator p = new Validator();
    @FXML
    private JFXTextField costkm_jfxtextfield;

    @FXML
    private JFXTextField costkmroundtrip_jfxtextfield;

    @FXML
    private JFXTextField costhrwait_jfxtextfield;

    @FXML
    private JFXComboBox<ContractTransportDto> contract_jfxcombobox;

    @FXML
    private JFXComboBox<VehicleDto> vehicle_jfxcombobox;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            contract_jfxcombobox.getItems().addAll(ServicesLocator.getContractTransportServices().loadAll());
            vehicle_jfxcombobox.getItems().addAll(ServicesLocator.getVehicleServices().loadAll());

            RegexValidator regexTextValidator = new RegexValidator("This field requires a text");
            RegexValidator regexNumericValidator = new RegexValidator("This field requires a Number");
            RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator("This field is require");
            regexTextValidator.setRegexPattern("[a-zA-Z].*" + "");
            regexNumericValidator.setRegexPattern("[+-]?\\d*(\\.\\d+)?");


            costhrwait_jfxtextfield.getValidators().add(regexNumericValidator);
            costhrwait_jfxtextfield.getValidators().add(requiredFieldValidator);
            costhrwait_jfxtextfield.focusedProperty().addListener((o, oldVal, newVal) -> {
                if (!newVal) costhrwait_jfxtextfield.validate();
            });

            costkm_jfxtextfield.getValidators().add(regexNumericValidator);
            costkm_jfxtextfield.getValidators().add(requiredFieldValidator);
            costkm_jfxtextfield.focusedProperty().addListener((o, oldVal, newVal) -> {
                if (!newVal) costkm_jfxtextfield.validate();
            });

            costkmroundtrip_jfxtextfield.getValidators().add(regexNumericValidator);
            costkmroundtrip_jfxtextfield.getValidators().add(requiredFieldValidator);
            costkmroundtrip_jfxtextfield.focusedProperty().addListener((o, oldVal, newVal) -> {
                if (!newVal) costkmroundtrip_jfxtextfield.validate();
            });

            contract_jfxcombobox.getValidators().add(requiredFieldValidator);
            contract_jfxcombobox.focusedProperty().addListener((o, oldVal, newVal) -> {
                if (!newVal) contract_jfxcombobox.validate();
            });

            vehicle_jfxcombobox.getValidators().add(requiredFieldValidator);
            vehicle_jfxcombobox.focusedProperty().addListener((o, oldVal, newVal) -> {
                if (!newVal) vehicle_jfxcombobox.validate();
            });




        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(ActionEvent event) throws SQLException {
        if (p.validateNumberFloat(costhrwait_jfxtextfield) && p.validateNumberFloat(costkm_jfxtextfield) && p.validateNumberFloat(costkmroundtrip_jfxtextfield) && p.validateCombobox(contract_jfxcombobox) && p.validateCombobox(vehicle_jfxcombobox)) {
            ServicesLocator.getModalityTransportKmServices().insert(new ModalityTransportKmDto(
                    0,
                    Float.parseFloat(costkm_jfxtextfield.getText()),
                    Float.parseFloat(costkmroundtrip_jfxtextfield.getText()),
                    Float.parseFloat(costhrwait_jfxtextfield.getText()),
                    contract_jfxcombobox.getValue(),
                    vehicle_jfxcombobox.getValue()
            ));
            ((Stage) costkm_jfxtextfield.getScene().getWindow()).close();
        }
    }

    @Override
    public void update(ActionEvent event) throws SQLException {
        if (p.validateNumberFloat(costhrwait_jfxtextfield) && p.validateNumberFloat(costkm_jfxtextfield) && p.validateNumberFloat(costkmroundtrip_jfxtextfield) && p.validateCombobox(contract_jfxcombobox) && p.validateCombobox(vehicle_jfxcombobox)) {

            ServicesLocator.getModalityTransportKmServices().update(new ModalityTransportKmDto(
                    dto.getId(),
                    Float.parseFloat(costkm_jfxtextfield.getText()),
                    Float.parseFloat(costkmroundtrip_jfxtextfield.getText()),
                    Float.parseFloat(costhrwait_jfxtextfield.getText()),
                    contract_jfxcombobox.getValue(),
                    vehicle_jfxcombobox.getValue()
            ));

            ((Stage) costkm_jfxtextfield.getScene().getWindow()).close();
        }
    }

    @Override
    public void setDto(Dto dto) {
        super.setDto(dto);

        if(dto != null) {
            ModalityTransportKmDto modalityTransportKmDto = (ModalityTransportKmDto) dto;
            costkm_jfxtextfield.setText(String.valueOf(modalityTransportKmDto.getCostKm()));
            costkmroundtrip_jfxtextfield.setText(String.valueOf(modalityTransportKmDto.getCostKmRoundTrip()));
            costhrwait_jfxtextfield.setText(String.valueOf(modalityTransportKmDto.getCostHrWait()));

            for (ContractTransportDto contractTransportDto : contract_jfxcombobox.getItems()) {
                if (contractTransportDto.getId() == modalityTransportKmDto.getContractTransport().getId()) {
                    contract_jfxcombobox.getSelectionModel().select(contractTransportDto);
                    break;
                }
            }

            for (VehicleDto vehicleDto : vehicle_jfxcombobox.getItems()) {
                if (vehicleDto.getId() == modalityTransportKmDto.getVehicle().getId()) {
                    vehicle_jfxcombobox.getSelectionModel().select(vehicleDto);
                    break;
                }
            }
        }
    }
}
