package ui.controller.datamanager;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.validation.RegexValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import dto.*;
import dto.nom.ContractTypeDto;
import dto.nom.DailyActivityDto;
import dto.nom.FoodPlanDto;
import dto.nom.RoomTypeDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;
import service.ServicesLocator;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import util.Validator;

public class ContractHotelModal extends DataManagerFormController{
    private final DateTimeFormatter sqlDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
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
    private JFXComboBox<HotelDto> hotel_jfxcombobox;

    @FXML
    private CheckComboBox<SeasonDto> season_checkcombobox;

    @FXML
    private CheckComboBox<RoomTypeDto> roomtype_checkcombobox;

    @FXML
    private CheckComboBox<FoodPlanDto> foodplan_checkcombobox;

    @FXML
    private JFXButton insert_jfxbutton;

    @FXML
    private JFXButton update_jfxbutton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            contracttype_jfxcombobox.getItems().addAll(ServicesLocator.getContractTypeServices().loadAll());
            hotel_jfxcombobox.getItems().addAll(ServicesLocator.getHotelServices().loadAll());
            roomtype_checkcombobox.getItems().addAll(ServicesLocator.getRoomTypeServices().loadAll());
            season_checkcombobox.getItems().addAll(ServicesLocator.getSeasonServices().loadAll());
            foodplan_checkcombobox.getItems().addAll(ServicesLocator.getFoodPlanServices().loadAll());

            RegexValidator regexTextValidator = new RegexValidator("This field requires a text");
            RegexValidator regexNumericValidator = new RegexValidator("This field requires a Number");
            RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator("This field is require");
            regexTextValidator.setRegexPattern("[a-zA-Z].*" + "");
            regexNumericValidator.setRegexPattern("[+-]?\\d*(\\.\\d+)?");

            contracttype_jfxcombobox.getValidators().add(requiredFieldValidator);
            hotel_jfxcombobox.getValidators().add(requiredFieldValidator);
            description_jfxtextarea.getValidators().add(requiredFieldValidator);
            description_jfxtextarea.getValidators().add(regexTextValidator);
            startdate_jfxdatepicker.getValidators().add(requiredFieldValidator);
            finishdate_jfxdatepicker.getValidators().add(requiredFieldValidator);
            conciliationdate_jfxdatepicker.getValidators().add(requiredFieldValidator);


            contracttype_jfxcombobox.focusedProperty().addListener((o, oldVal, newVal) -> {
                if (!newVal) contracttype_jfxcombobox.validate();
            });
            hotel_jfxcombobox.focusedProperty().addListener((o, oldVal, newVal) -> {
                if (!newVal) hotel_jfxcombobox.validate();
            });
            description_jfxtextarea.focusedProperty().addListener((o, oldVal, newVal) -> {
                if (!newVal) description_jfxtextarea.validate();
            });
            startdate_jfxdatepicker.focusedProperty().addListener((o, oldVal, newVal) -> {
                if (!newVal) startdate_jfxdatepicker.validate();
            });
            finishdate_jfxdatepicker.focusedProperty().addListener((o, oldVal, newVal) -> {
                if (!newVal) finishdate_jfxdatepicker.validate();
            });
            conciliationdate_jfxdatepicker.focusedProperty().addListener((o, oldVal, newVal) -> {
                if (!newVal) conciliationdate_jfxdatepicker.validate();
            });



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(ActionEvent event) throws SQLException {
        if (p.validateDates(startdate_jfxdatepicker,finishdate_jfxdatepicker,conciliationdate_jfxdatepicker)&& p.validateText(description_jfxtextarea)) {
            ContractHotelDto contractHotelDto = new ContractHotelDto(
                    0,
                    contracttype_jfxcombobox.getValue(),
                    Date.valueOf(startdate_jfxdatepicker.getValue().format(sqlDateTimeFormatter)),
                    Date.valueOf(finishdate_jfxdatepicker.getValue().format(sqlDateTimeFormatter)),
                    Date.valueOf(conciliationdate_jfxdatepicker.getValue().format(sqlDateTimeFormatter)),
                    description_jfxtextarea.getText(),
                    season_checkcombobox.getCheckModel().getCheckedItems(),
                    hotel_jfxcombobox.getValue()
            );

            ServicesLocator.getContractHotelServices().insert(contractHotelDto);
            ServicesLocator.getRelationContractHotelSeasonServices().insert(contractHotelDto);

            Stage pricesStage = null;
            Scene scene = null;
            pricesStage = (Stage) conciliationdate_jfxdatepicker.getScene().getWindow();
            RoomTypePricesModal roomTypePricesModal = new RoomTypePricesModal();
            roomTypePricesModal.initializeNameTablePrices(roomtype_checkcombobox.getCheckModel().getCheckedItems(),
                    season_checkcombobox.getCheckModel().getCheckedItems(),
                    foodplan_checkcombobox.getCheckModel().getCheckedItems(),
                    contractHotelDto.getId(),
                    pricesStage);
        }
    }

    @Override
    public void update(ActionEvent event) throws SQLException {
        ContractHotelDto contractHotelDto = (ContractHotelDto) dto;



            contractHotelDto.setContractTypeDto(contracttype_jfxcombobox.getValue());
            contractHotelDto.setStartDate(Date.valueOf(startdate_jfxdatepicker.getValue().format(sqlDateTimeFormatter)));
            contractHotelDto.setFinishDate(Date.valueOf(finishdate_jfxdatepicker.getValue().format(sqlDateTimeFormatter)));
            contractHotelDto.setConciliationDate(Date.valueOf(conciliationdate_jfxdatepicker.getValue().format(sqlDateTimeFormatter)));
            contractHotelDto.setDescription(description_jfxtextarea.getText());
            season_checkcombobox.getItems().removeAll(contractHotelDto.getSeasons());
            contractHotelDto.setSeasons(season_checkcombobox.getCheckModel().getCheckedItems());
            contractHotelDto.setHotel(hotel_jfxcombobox.getValue());
        if (p.validateDates(startdate_jfxdatepicker,finishdate_jfxdatepicker,conciliationdate_jfxdatepicker)&& p.validateText(description_jfxtextarea)) {


            ServicesLocator.getContractHotelServices().update(contractHotelDto);
            ServicesLocator.getRelationContractHotelSeasonServices().update(contractHotelDto);
            ServicesLocator.getRelationContractHotelRoomFoodSeasonServices().delete(contractHotelDto.getId());
        }
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

