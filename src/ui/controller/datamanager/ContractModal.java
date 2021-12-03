package ui.controller.datamanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RegexValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import dto.ContractDto;
import dto.Dto;
import dto.nom.ContractTypeDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import service.ServicesLocator;
import util.Validator;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ContractModal extends DataManagerFormController {
    private final DateTimeFormatter sqlDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final DateTimeFormatter localDateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    Validator p=new Validator();

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

            RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator("This field is required");
            RegexValidator regexValidator = new RegexValidator("This field requires a text");
            regexValidator.setRegexPattern("[a-zA-Z].*" + "");

            startdate_jfxdatepicker.getValidators().add(requiredFieldValidator);
            startdate_jfxdatepicker.focusedProperty().addListener((o, oldVal, newVal) -> {
                if (!newVal) startdate_jfxdatepicker.validate();
            });
            finishdate_jfxdatepicker.getValidators().add(requiredFieldValidator);
            finishdate_jfxdatepicker.focusedProperty().addListener((o, oldVal, newVal) -> {
                if (!newVal) finishdate_jfxdatepicker.validate();
            });

            conciliationdate_jfxdatepicker.getValidators().add(requiredFieldValidator);
            conciliationdate_jfxdatepicker.focusedProperty().addListener((o, oldVal, newVal) -> {
                if (!newVal) conciliationdate_jfxdatepicker.validate();
            });


            description_jfxtextarea.getValidators().add(requiredFieldValidator);
            description_jfxtextarea.getValidators().add(regexValidator);
            description_jfxtextarea.focusedProperty().addListener((o,oldVal,newVal)->{
                if(!newVal) description_jfxtextarea.validate();
            });

            contracttype_jfxcombobox.getValidators().add(requiredFieldValidator);
            contracttype_jfxcombobox.focusedProperty().addListener((o,oldVal,newVal)->{
                if(!newVal) contracttype_jfxcombobox.validate();
            });


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(ActionEvent event) throws SQLException, ParseException {
        if (p.validateDates(startdate_jfxdatepicker,finishdate_jfxdatepicker,conciliationdate_jfxdatepicker)&&p.validateText(description_jfxtextarea)&&p.validateCombobox(contracttype_jfxcombobox)) {
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
    }

    @Override
    public void update(ActionEvent event) throws SQLException {

            ContractDto contractDto = (ContractDto) dto;
            contractDto.setContractTypeDto(contracttype_jfxcombobox.getValue());
            contractDto.setStartDate(Date.valueOf(startdate_jfxdatepicker.getValue().format(sqlDateTimeFormatter)));
            contractDto.setFinishDate(Date.valueOf(finishdate_jfxdatepicker.getValue().format(sqlDateTimeFormatter)));
            contractDto.setConciliationDate(Date.valueOf(conciliationdate_jfxdatepicker.getValue().format(sqlDateTimeFormatter)));
            contractDto.setDescription(description_jfxtextarea.getText());

        if (p.validateDates(startdate_jfxdatepicker,finishdate_jfxdatepicker,conciliationdate_jfxdatepicker)&&p.validateText(description_jfxtextarea)&&p.validateCombobox(contracttype_jfxcombobox)) {

            ServicesLocator.getContractServices().update(contractDto);

            ((Stage) startdate_jfxdatepicker.getScene().getWindow()).close();
        }
    }

    @Override
    public void setDto(Dto dto) {
        super.setDto(dto);

        if (dto != null) {
            ContractDto contractDto = (ContractDto) dto;

            startdate_jfxdatepicker.setValue(contractDto.getStartDate().toLocalDate());
            finishdate_jfxdatepicker.setValue(contractDto.getFinishDate().toLocalDate());
            conciliationdate_jfxdatepicker.setValue(contractDto.getConciliationDate().toLocalDate());
            description_jfxtextarea.setText(contractDto.getDescription());
            contracttype_jfxcombobox.getSelectionModel().select(contractDto.getContractTypeDto());
        }
    }
}

