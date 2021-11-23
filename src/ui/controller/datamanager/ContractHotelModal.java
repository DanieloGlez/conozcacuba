package ui.controller.datamanager;

import com.jfoenix.controls.JFXComboBox;
import dto.ContractDto;
import dto.ContractHotelDto;
import dto.Dto;
import dto.HotelDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
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

        ((Stage) contract_jfxcombobox.getScene().getWindow()).close();
    }

    @Override
    public void update(ActionEvent event) throws SQLException {
        ContractDto contractDto = contract_jfxcombobox.getValue();
        HotelDto hotelDto = hotel_jfxcombobox.getValue();

        ServicesLocator.getContractHotelServices().update(new ContractHotelDto(
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
    public void setDto(Dto dto) {
        super.setDto(dto);

        ContractHotelDto contractHotelDto = (ContractHotelDto) dto;

        for (ContractDto loopContractDto : contract_jfxcombobox.getItems()) {
            if (loopContractDto.getId() == contractHotelDto.getId())
                contract_jfxcombobox.getSelectionModel().select(loopContractDto);
        }

        hotel_jfxcombobox.getSelectionModel().select(contractHotelDto.getHotel());
    }
}

