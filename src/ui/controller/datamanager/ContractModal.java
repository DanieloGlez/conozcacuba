package ui.controller.datamanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import dto.ContractDto;
import dto.Dto;
import dto.nom.ContractTypeDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import service.ServicesLocator;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ContractModal extends DataManagerFormController{
    private final DateTimeFormatter sqlDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final DateTimeFormatter localDateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

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
    private JFXButton insert_jfxbutton;

    @FXML
    private JFXButton cancel_jfxbutton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            contracttype_jfxcombobox.getItems().addAll(ServicesLocator.getContractTypeServices().loadAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(ActionEvent event) throws SQLException, ParseException {
        ServicesLocator.getContractServices().insert(new ContractDto(
                0,
                contracttype_jfxcombobox.getValue(),
                Date.valueOf(startdate_jfxdatepicker.getValue().format(sqlDateTimeFormatter)),
                Date.valueOf(finishdate_jfxdatepicker.getValue().format(sqlDateTimeFormatter)),
                Date.valueOf(conciliationdate_jfxdatepicker.getValue().format(sqlDateTimeFormatter)),
                description_jfxtextarea.getText()
        ));

        ((Stage) startdate_jfxdatepicker.getScene().getWindow()).close();
    }

    @Override
    public void update(ActionEvent event) throws SQLException {
        ContractDto contractDto = (ContractDto) dto;
        contractDto.setContractTypeDto(contracttype_jfxcombobox.getValue());
        contractDto.setStartDate(Date.valueOf(startdate_jfxdatepicker.getValue().format(sqlDateTimeFormatter)));
        contractDto.setFinishDate(Date.valueOf(finishdate_jfxdatepicker.getValue().format(sqlDateTimeFormatter)));
        contractDto.setConciliationDate(Date.valueOf(conciliationdate_jfxdatepicker.getValue().format(sqlDateTimeFormatter)));
        contractDto.setDescription(description_jfxtextarea.getText());

        ServicesLocator.getContractServices().update(contractDto);

        ((Stage) startdate_jfxdatepicker.getScene().getWindow()).close();
    }

    @Override
    public void setDto(Dto dto) {
        super.setDto(dto);

        if(dto != null) {
            ContractDto contractDto = (ContractDto) dto;

            startdate_jfxdatepicker.setValue(contractDto.getStartDate().toLocalDate());
            finishdate_jfxdatepicker.setValue(contractDto.getFinishDate().toLocalDate());
            conciliationdate_jfxdatepicker.setValue(contractDto.getConciliationDate().toLocalDate());
            description_jfxtextarea.setText(contractDto.getDescription());
            contracttype_jfxcombobox.getSelectionModel().select(contractDto.getContractTypeDto());
        }
    }
}

