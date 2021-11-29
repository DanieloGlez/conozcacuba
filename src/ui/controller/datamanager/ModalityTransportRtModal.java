package ui.controller.datamanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.ContractTransportDto;
import dto.ModalityTransportHrKmDto;
import dto.ModalityTransportRtDto;
import dto.VehicleDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import service.ServicesLocator;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ModalityTransportRtModal extends DataManagerFormController {

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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void insert(ActionEvent event) throws SQLException {
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

    @Override
    public void update(ActionEvent event) throws SQLException {
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
