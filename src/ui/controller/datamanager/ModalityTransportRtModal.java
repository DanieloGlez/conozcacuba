package ui.controller.datamanager;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RegexValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import dto.ContractTransportDto;
import dto.ModalityTransportRtDto;
import dto.VehicleDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import service.ServicesLocator;
import util.Validator;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ModalityTransportRtModal extends DataManagerFormController {
    Validator p = new Validator();

    @FXML
    private JFXTextField routedescription_jfxtextfield;

    @FXML
    private JFXTextField costroute_jfxtextfield;

    @FXML
    private JFXTextField costroundtrip_jfxtextfield;

    @FXML
    private JFXComboBox<ContractTransportDto> contract_jfxcombobox;

    @FXML
    private JFXComboBox<VehicleDto> vehicle_jfxcombobox;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            vehicle_jfxcombobox.getItems().addAll(ServicesLocator.getVehicleServices().loadAll());
            contract_jfxcombobox.getItems().addAll(ServicesLocator.getContractTransportServices().loadAll());

            RegexValidator regexTextValidator = new RegexValidator("This field requires a text");
            RegexValidator regexNumericValidator = new RegexValidator("This fields requires a Number");
            RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator("This field is require");
            regexTextValidator.setRegexPattern("[a-zA-Z].*" + "");
            regexNumericValidator.setRegexPattern("[+-]?\\d*(\\.\\d+)?");

            costroute_jfxtextfield.getValidators().add(regexNumericValidator);
            costroute_jfxtextfield.getValidators().add(requiredFieldValidator);
            costroute_jfxtextfield.focusedProperty().addListener((o, oldVal, newVal) -> {
                if (!newVal) costroute_jfxtextfield.validate();
            });
            costroundtrip_jfxtextfield.getValidators().add(regexNumericValidator);
            costroundtrip_jfxtextfield.getValidators().add(requiredFieldValidator);
            costroundtrip_jfxtextfield.focusedProperty().addListener((o, oldVal, newVal) -> {
                if (!newVal) costroundtrip_jfxtextfield.validate();
            });

            routedescription_jfxtextfield.getValidators().add(regexTextValidator);
            routedescription_jfxtextfield.getValidators().add(requiredFieldValidator);
            routedescription_jfxtextfield.focusedProperty().addListener((o, oldVal, newVal) -> {
                if (!newVal) routedescription_jfxtextfield.validate();
            });

            contract_jfxcombobox.getValidators().add(requiredFieldValidator);
            contract_jfxcombobox.focusedProperty().addListener((o, oldVal, newVal) -> {
                if (!newVal) contract_jfxcombobox.validate();
            });

            vehicle_jfxcombobox.getValidators().add(requiredFieldValidator);
            vehicle_jfxcombobox.focusedProperty().addListener((o, oldVal, newVal) -> {
                if (!newVal) vehicle_jfxcombobox.validate();
            });


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void insert(ActionEvent event) throws SQLException {
        if (p.validateNumberFloat(costroundtrip_jfxtextfield) && p.validateText(routedescription_jfxtextfield) && p.validateNumberFloat(costroundtrip_jfxtextfield) && p.validateCombobox(vehicle_jfxcombobox) && p.validateCombobox(contract_jfxcombobox)) {
            ServicesLocator.getModalityTransportRtServices().insert(new ModalityTransportRtDto(
                    0,
                    routedescription_jfxtextfield.getText(),
                    Float.parseFloat(costroute_jfxtextfield.getText()),
                    Float.parseFloat(costroundtrip_jfxtextfield.getText()),
                    contract_jfxcombobox.getValue(),
                    vehicle_jfxcombobox.getValue()
            ));
            ((Stage) routedescription_jfxtextfield.getScene().getWindow()).close();
        }
    }

    @Override
    public void update(ActionEvent event) throws SQLException {
        if (p.validateNumberFloat(costroundtrip_jfxtextfield) && p.validateText(routedescription_jfxtextfield) && p.validateNumberFloat(costroundtrip_jfxtextfield) && p.validateCombobox(vehicle_jfxcombobox) && p.validateCombobox(contract_jfxcombobox)) {

            ServicesLocator.getModalityTransportRtServices().update(new ModalityTransportRtDto(
                    dto.getId(),
                    routedescription_jfxtextfield.getText(),
                    Float.parseFloat(costroute_jfxtextfield.getText()),
                    Float.parseFloat(costroundtrip_jfxtextfield.getText()),
                    contract_jfxcombobox.getValue(),
                    vehicle_jfxcombobox.getValue()
            ));
            ((Stage) routedescription_jfxtextfield.getScene().getWindow()).close();
        }
    }
}
