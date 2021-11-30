package ui.controller.datamanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RegexValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import dto.*;
import dto.nom.VehicleBrandDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import service.ServicesLocator;
import util.Validator;

import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class ModalityTransportHrKm extends DataManagerFormController {

    public JFXTextField costhr_jfxtextfield;
    @FXML
    private JFXTextField travelcost_jfxtextfield;

    @FXML
    private JFXTextField kmextracost_jfxtextfield;

    @FXML
    private JFXTextField hoursextracost_jfxtextfield;

    @FXML
    private JFXComboBox<ContractTransportDto> contract_jfxcombobox;

    @FXML
    private JFXComboBox<VehicleDto> vehicle_combobox;

    private Validator p = new Validator();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            vehicle_combobox.getItems().addAll(ServicesLocator.getVehicleServices().loadAll());
            contract_jfxcombobox.getItems().addAll(ServicesLocator.getContractTransportServices().loadAll());



            RegexValidator regexTextValidator = new RegexValidator("This fields requires a text");
            RegexValidator regexNumericValidator = new RegexValidator("This fields requires a Number");
            RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator("This field is require");
            regexTextValidator.setRegexPattern("[a-zA-Z].*" + "");
            regexNumericValidator.setRegexPattern("[+-]?\\d*(\\.\\d+)?");


            costhr_jfxtextfield.getValidators().add(regexNumericValidator);
            costhr_jfxtextfield.getValidators().add(requiredFieldValidator);
            costhr_jfxtextfield.focusedProperty().addListener((o, oldVal, newVal) -> {
                if (!newVal) costhr_jfxtextfield.validate();
            });

            hoursextracost_jfxtextfield.getValidators().add(regexNumericValidator);
            hoursextracost_jfxtextfield.getValidators().add(requiredFieldValidator);
            hoursextracost_jfxtextfield.focusedProperty().addListener((o, oldVal, newVal) -> {
                if (!newVal) hoursextracost_jfxtextfield.validate();
            });

            kmextracost_jfxtextfield.getValidators().add(regexNumericValidator);
            kmextracost_jfxtextfield.getValidators().add(requiredFieldValidator);
            kmextracost_jfxtextfield.focusedProperty().addListener((o, oldVal, newVal) -> {
                if (!newVal) kmextracost_jfxtextfield.validate();
            });

            travelcost_jfxtextfield.getValidators().add(regexNumericValidator);
            travelcost_jfxtextfield.getValidators().add(requiredFieldValidator);
            travelcost_jfxtextfield.focusedProperty().addListener((o, oldVal, newVal) -> {
                if (!newVal) travelcost_jfxtextfield.validate();
            });

            contract_jfxcombobox.getValidators().add(requiredFieldValidator);
            contract_jfxcombobox.focusedProperty().addListener((o, oldVal, newVal) -> {
                if (!newVal) contract_jfxcombobox.validate();
            });

            vehicle_combobox.getValidators().add(requiredFieldValidator);
            vehicle_combobox.focusedProperty().addListener((o, oldVal, newVal) -> {
                if (!newVal) vehicle_combobox.validate();
            });


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void insert(ActionEvent event) throws SQLException {
        if (p.validateNumberFloat(costhr_jfxtextfield) && p.validateNumberFloat(hoursextracost_jfxtextfield) && p.validateNumberFloat(kmextracost_jfxtextfield)&&p.validateNumberFloat(travelcost_jfxtextfield) &&p.validateCombobox(vehicle_combobox) &p.validateCombobox(contract_jfxcombobox)) {
            ServicesLocator.getModalityTransportHrKmServices().insert(new ModalityTransportHrKmDto(
                    0,
                    Float.parseFloat(costhr_jfxtextfield.getText()),
                    Float.parseFloat(travelcost_jfxtextfield.getText()),
                    Float.parseFloat(kmextracost_jfxtextfield.getText()),
                    Float.parseFloat(hoursextracost_jfxtextfield.getText()),
                    contract_jfxcombobox.getValue(),
                    vehicle_combobox.getValue()
            ));
            ((Stage) costhr_jfxtextfield.getScene().getWindow()).close();
        }
    }

    @Override
    public void update(ActionEvent event) throws SQLException {
        if (p.validateNumberFloat(costhr_jfxtextfield) && p.validateNumberFloat(hoursextracost_jfxtextfield) && p.validateNumberFloat(kmextracost_jfxtextfield)&&p.validateNumberFloat(travelcost_jfxtextfield) &&p.validateCombobox(vehicle_combobox) &p.validateCombobox(contract_jfxcombobox)) {

            ServicesLocator.getModalityTransportHrKmServices().update(new ModalityTransportHrKmDto(
                    dto.getId(),
                    Float.parseFloat(costhr_jfxtextfield.getText()),
                    Float.parseFloat(travelcost_jfxtextfield.getText()),
                    Float.parseFloat(kmextracost_jfxtextfield.getText()),
                    Float.parseFloat(hoursextracost_jfxtextfield.getText()),
                    contract_jfxcombobox.getValue(),
                    vehicle_combobox.getValue()
            ));

            ((Stage) costhr_jfxtextfield.getScene().getWindow()).close();
        }
    }
}
