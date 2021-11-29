package ui.controller.datamanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.*;
import dto.nom.VehicleBrandDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import service.ServicesLocator;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            vehicle_combobox.getItems().addAll(ServicesLocator.getVehicleServices().loadAll());
            contract_jfxcombobox.getItems().addAll(ServicesLocator.getContractTransportServices().loadAll());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void insert(ActionEvent event) throws SQLException {
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

    @Override
    public void update(ActionEvent event) throws SQLException {
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
