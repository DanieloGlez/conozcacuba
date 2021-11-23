package ui.controller.datamanager;

import com.jfoenix.controls.JFXComboBox;
import dto.ContractDto;
import dto.ContractHotelDto;
import dto.HotelDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import service.ServicesLocator;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ContractHotelModal extends DataManagerFormController{

    @FXML
    private JFXComboBox<ContractDto> contract_jfxcombobox;

    @FXML
    private JFXComboBox<HotelDto> hotel_jfxcombobox;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            contract_jfxcombobox.getItems().addAll(ServicesLocator.getContractServices().loadAll());
            hotel_jfxcombobox.getItems().addAll(ServicesLocator.getHotelServices().loadAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(ActionEvent event) throws SQLException {
        ContractDto contractDto = contract_jfxcombobox.getValue();
        HotelDto hotelDto = hotel_jfxcombobox.getValue();

        ServicesLocator.getContractHotelServices().insert(new ContractHotelDto(
                contractDto.getId(),
                contractDto.getStartDate(),
                contractDto.getFinishDate(),
                contractDto.getConciliationDate(),
                contractDto.getDescription(),
                contractDto.getContractTypeDto(),
                ServicesLocator.getSeasonServices().loadRelated(contractDto.getId()),
                hotelDto
        ));
    }

    @Override
    public void update(ActionEvent event) {

    }
}

