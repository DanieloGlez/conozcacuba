package ui.controller.datamanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.sun.org.apache.xpath.internal.operations.Mod;
import dto.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import service.ServicesLocator;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ModalityTransportKm extends DataManagerFormController {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(ActionEvent event) throws SQLException {
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

    @Override
    public void update(ActionEvent event) throws SQLException {
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
