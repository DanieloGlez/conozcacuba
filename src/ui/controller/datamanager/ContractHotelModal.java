package ui.controller.datamanager;

import com.jfoenix.controls.JFXComboBox;
import dto.*;
import dto.nom.ContractTypeDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;
import service.ServicesLocator;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

public class ContractHotelModal extends DataManagerFormController{
    private final DateTimeFormatter sqlDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        @FXML
        private JFXDatePicker startdate_jfxdatepicker;

        @FXML
        private JFXDatePicker finishdate_jfxdatepicker;

        @FXML
        private JFXDatePicker conciliationdate_jfxdatepicker;

        @FXML
        private JFXTextField description_jfxtextarea;

        @FXML
        private JFXComboBox<ContractTypeDto> contracttype_jfxcombobox;

        @FXML
        private JFXComboBox<HotelDto> hotel_jfxcombobox;

        @FXML
        private CheckComboBox<SeasonDto> season_checkcombobox;

        @FXML
        private JFXButton insert_jfxbutton;

        @FXML
        private JFXButton update_jfxbutton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            contracttype_jfxcombobox.getItems().addAll(ServicesLocator.getContractTypeServices().loadAll());
            hotel_jfxcombobox.getItems().addAll(ServicesLocator.getHotelServices().loadAll());
            season_checkcombobox.getItems().addAll(ServicesLocator.getSeasonServices().loadAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(ActionEvent event) throws SQLException {
        ContractHotelDto contractHotelDto = new ContractHotelDto(
                0,
                contracttype_jfxcombobox.getValue(),
                Date.valueOf (startdate_jfxdatepicker.getValue().format(sqlDateTimeFormatter)),
                Date.valueOf(finishdate_jfxdatepicker.getValue().format(sqlDateTimeFormatter)),
                Date.valueOf(conciliationdate_jfxdatepicker.getValue().format(sqlDateTimeFormatter)),
                description_jfxtextarea.getText(),
                season_checkcombobox.getCheckModel().getCheckedItems(),
                hotel_jfxcombobox.getValue()
        );

        ServicesLocator.getContractHotelServices().insert(contractHotelDto);
        ServicesLocator.getRelationContractHotelSeasonServices().insert(contractHotelDto);
        ((Stage) startdate_jfxdatepicker.getScene().getWindow()).close();
    }

    @Override
    public void update(ActionEvent event) throws SQLException {
        ContractHotelDto contractHotelDto = (ContractHotelDto) dto;

        contractHotelDto.setContractTypeDto(contracttype_jfxcombobox.getValue());
        contractHotelDto.setStartDate( Date.valueOf (startdate_jfxdatepicker.getValue().format(sqlDateTimeFormatter)));
        contractHotelDto.setFinishDate(Date.valueOf(finishdate_jfxdatepicker.getValue().format(sqlDateTimeFormatter)));
        contractHotelDto.setConciliationDate(Date.valueOf(conciliationdate_jfxdatepicker.getValue().format(sqlDateTimeFormatter)));
        contractHotelDto.setDescription(description_jfxtextarea.getText());
        season_checkcombobox.getItems().removeAll(contractHotelDto.getSeasons());
        contractHotelDto.setSeasons(season_checkcombobox.getCheckModel().getCheckedItems());
        contractHotelDto.setHotel(hotel_jfxcombobox.getValue());

        ServicesLocator.getContractHotelServices().update(contractHotelDto);
        ServicesLocator.getRelationContractHotelSeasonServices().update(contractHotelDto);

        ((Stage) contracttype_jfxcombobox.getScene().getWindow()).close();
    }

    @Override
    public void setDto(Dto dto) {
        super.setDto(dto);

        if (dto != null) {
            ContractHotelDto contractHotelDto = (ContractHotelDto) dto;

            startdate_jfxdatepicker.setValue(contractHotelDto.getStartDate().toLocalDate());
            finishdate_jfxdatepicker.setValue(contractHotelDto.getFinishDate().toLocalDate());
            conciliationdate_jfxdatepicker.setValue(contractHotelDto.getConciliationDate().toLocalDate());
            description_jfxtextarea.setText(contractHotelDto.getDescription());
            contracttype_jfxcombobox.getSelectionModel().select(contractHotelDto.getContractTypeDto());
            hotel_jfxcombobox.getSelectionModel().select(contractHotelDto.getHotel());
        }
    }
}

