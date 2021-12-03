package ui.controller.datamanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RegexValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import dto.Dto;
import dto.HotelDto;
import dto.nom.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;
import service.RelationHotelFoodPlanServices;
import service.RelationHotelModalityCommercialServices;
import service.RelationHotelRoomTypeServices;
import service.ServicesLocator;
import util.Validator;

import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class HotelModal extends DataManagerFormController {
    Validator p=new Validator();
    @FXML
    private JFXTextField name_jfxtextfield;

    @FXML
    private JFXTextField address_jfxtextfield;

    @FXML
    private JFXTextField telephone_jfxtextfield;

    @FXML
    private JFXTextField fax_jfxtextfield;

    @FXML
    private JFXTextField distancetocity_jfxtextfield;

    @FXML
    private JFXTextField distancetoairport_jfxtextfield;

    @FXML
    private JFXTextField roomsamount_jfxtextfield;

    @FXML
    private JFXTextField flooramount_jfxtextfield;

    @FXML
    private JFXTextField email_jfxtextfield;

    @FXML
    private JFXComboBox<LocalizationDto> localization_jfxcombobox;

    @FXML
    private JFXComboBox<HotelFranchiseDto> hotelfranchise_jfxcombobox;

    @FXML
    private JFXComboBox<ProvinceDto> province_jfxcombobox;

    @FXML
    private CheckComboBox<RoomTypeDto> roomtypes_checkcombobox;

    @FXML
    private CheckComboBox<FoodPlanDto> foodplans_checkcombobox;

    @FXML
    private CheckComboBox<ModalityCommercialDto> commercialmodalities_checkcombobox;

    @FXML
    private JFXComboBox<String> category_jfxcombobox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> categories = new LinkedList<>();
        List<ModalityCommercialDto> list = new LinkedList<>();
        categories.add(String.valueOf(1));
        categories.add(String.valueOf(2));
        categories.add(String.valueOf(3));
        categories.add(String.valueOf(4));
        categories.add(String.valueOf(5));
        RegexValidator regexTextValidator = new RegexValidator("This field requires a text");
        RegexValidator regexNumericValidator = new RegexValidator("This field requires a Number");
        RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator("This field is require");
        regexTextValidator.setRegexPattern("[a-zA-Z].*" + "");
        regexNumericValidator.setRegexPattern("[+-]?\\d*(\\.\\d+)?");

        telephone_jfxtextfield.getValidators().add(regexNumericValidator);
        telephone_jfxtextfield.getValidators().add(requiredFieldValidator);
        address_jfxtextfield.getValidators().add(requiredFieldValidator);
        distancetoairport_jfxtextfield.getValidators().add(regexNumericValidator);
        distancetoairport_jfxtextfield.getValidators().add(requiredFieldValidator);
        distancetocity_jfxtextfield.getValidators().add(regexNumericValidator);
        distancetocity_jfxtextfield.getValidators().add(requiredFieldValidator);
        fax_jfxtextfield.getValidators().add(regexNumericValidator);
        fax_jfxtextfield.getValidators().add(requiredFieldValidator);
        flooramount_jfxtextfield.getValidators().add(regexNumericValidator);
        flooramount_jfxtextfield.getValidators().add(requiredFieldValidator);
        name_jfxtextfield.getValidators().add(regexTextValidator);
        name_jfxtextfield.getValidators().add(requiredFieldValidator);
        roomsamount_jfxtextfield.getValidators().add(regexNumericValidator);
        roomsamount_jfxtextfield.getValidators().add(requiredFieldValidator);
        category_jfxcombobox.getValidators().add(requiredFieldValidator);
        province_jfxcombobox.getValidators().add(requiredFieldValidator);
        hotelfranchise_jfxcombobox.getValidators().add(requiredFieldValidator);
        localization_jfxcombobox.getValidators().add(requiredFieldValidator);


        telephone_jfxtextfield.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) telephone_jfxtextfield.validate();
        });
        address_jfxtextfield.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) address_jfxtextfield.validate();
        });
        distancetocity_jfxtextfield.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) distancetocity_jfxtextfield.validate();
        });
        distancetoairport_jfxtextfield.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) distancetoairport_jfxtextfield.validate();
        });
        fax_jfxtextfield.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) fax_jfxtextfield.validate();
        });
        flooramount_jfxtextfield.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) flooramount_jfxtextfield.validate();
        });
        name_jfxtextfield.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) name_jfxtextfield.validate();
        });
        roomsamount_jfxtextfield.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) roomsamount_jfxtextfield.validate();
        });

        category_jfxcombobox.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) category_jfxcombobox.validate();
        });
        province_jfxcombobox.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) province_jfxcombobox.validate();
        });
        hotelfranchise_jfxcombobox.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) hotelfranchise_jfxcombobox.validate();
        });

        localization_jfxcombobox.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) localization_jfxcombobox.validate();
        });

        try {
            localization_jfxcombobox.getItems().addAll(ServicesLocator.getLocalizationServices().loadAll());
            hotelfranchise_jfxcombobox.getItems().addAll(ServicesLocator.getHotelFranchiseServices().loadAll());
            province_jfxcombobox.getItems().addAll(ServicesLocator.getProvinceServices().loadAll());
            roomtypes_checkcombobox.getItems().addAll(ServicesLocator.getRoomTypeServices().loadAll());
            foodplans_checkcombobox.getItems().addAll(ServicesLocator.getFoodPlanServices().loadAll());
            commercialmodalities_checkcombobox.getItems().addAll(ServicesLocator.getModalityCommercialServices().loadAll());
            category_jfxcombobox.getItems().addAll(categories);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(ActionEvent event) throws SQLException {
        if (p.validateText(name_jfxtextfield) && p.validateNumberInteger(telephone_jfxtextfield) && p.validateNumberInteger(fax_jfxtextfield) && p.validateNumberFloat(distancetoairport_jfxtextfield) && p.validateNumberFloat(distancetocity_jfxtextfield) &&p.validateNumberInteger(flooramount_jfxtextfield) &&p.validateNumberInteger(roomsamount_jfxtextfield)) {
            HotelDto hotelDto = new HotelDto(
                    0,
                    name_jfxtextfield.getText(),
                    address_jfxtextfield.getText(),
                    category_jfxcombobox.getValue(),
                    telephone_jfxtextfield.getText(),
                    fax_jfxtextfield.getText(),
                    email_jfxtextfield.getText(),
                    Float.parseFloat(distancetocity_jfxtextfield.getText()),
                    Float.parseFloat(distancetoairport_jfxtextfield.getText()),
                    Integer.parseInt(roomsamount_jfxtextfield.getText()),
                    Integer.parseInt(flooramount_jfxtextfield.getText()),
                    hotelfranchise_jfxcombobox.getValue(),
                    province_jfxcombobox.getValue(),
                    localization_jfxcombobox.getValue(),
                    roomtypes_checkcombobox.getCheckModel().getCheckedItems(),
                    foodplans_checkcombobox.getCheckModel().getCheckedItems(),
                    commercialmodalities_checkcombobox.getCheckModel().getCheckedItems()
            );

            ServicesLocator.getHotelServices().insert(hotelDto);
            ServicesLocator.getRelationHotelFoodPlanServices().insert(hotelDto);
            ServicesLocator.getRelationHotelModalityCommercialServices().insert(hotelDto);
            ServicesLocator.getRelationHotelRoomTypeServices().insert(hotelDto);

            ((Stage) name_jfxtextfield.getScene().getWindow()).close();
        }
    }

    @Override
    public void update(ActionEvent event) throws SQLException {
        HotelDto hotelDto = (HotelDto) dto;

        hotelDto.setName(name_jfxtextfield.getText());
        hotelDto.setAddress(address_jfxtextfield.getText());
        hotelDto.setCategory(category_jfxcombobox.getValue());
        hotelDto.setTelephoneNumber(telephone_jfxtextfield.getText());
        hotelDto.setFax(fax_jfxtextfield.getText());
        hotelDto.setEmail(email_jfxtextfield.getText());
        hotelDto.setDistToCity(Float.parseFloat(distancetocity_jfxtextfield.getText()));
        hotelDto.setDistToAirport(Float.parseFloat(distancetoairport_jfxtextfield.getText()));
        hotelDto.setRoomsAmount(Integer.parseInt(roomsamount_jfxtextfield.getText()));
        hotelDto.setFloorsAmount(Integer.parseInt(flooramount_jfxtextfield.getText()));
        hotelDto.setHotelFranchise(hotelfranchise_jfxcombobox.getValue());
        hotelDto.setProvince(province_jfxcombobox.getValue());
        hotelDto.setLocalization(localization_jfxcombobox.getValue());
        hotelDto.setRoomTypes(roomtypes_checkcombobox.getCheckModel().getCheckedItems());
        hotelDto.setFoodPlans(foodplans_checkcombobox.getCheckModel().getCheckedItems());
        hotelDto.setCommercialModalities(commercialmodalities_checkcombobox.getCheckModel().getCheckedItems());

        ServicesLocator.getRelationHotelRoomTypeServices().update(hotelDto);
        ServicesLocator.getRelationHotelFoodPlanServices().update(hotelDto);
        ServicesLocator.getRelationHotelModalityCommercialServices().update(hotelDto);

        ((Stage) name_jfxtextfield.getScene().getWindow()).close();
    }


    @Override
    public void setDto(Dto dto) {
        super.setDto(dto);

        if (dto!=null){
            HotelDto hotelDto=(HotelDto) dto;

            fax_jfxtextfield.setText(hotelDto.getFax());
            telephone_jfxtextfield.setText(hotelDto.getTelephoneNumber());
            name_jfxtextfield.setText(hotelDto.getName());
            email_jfxtextfield.setText(hotelDto.getEmail());
            address_jfxtextfield.setText(hotelDto.getAddress());
            distancetoairport_jfxtextfield.setText(String.valueOf(hotelDto.getDistToAirport()));
            distancetocity_jfxtextfield.setText(String.valueOf(hotelDto.getDistToCity()));
            roomsamount_jfxtextfield.setText(String.valueOf(hotelDto.getRoomsAmount()));
            flooramount_jfxtextfield.setText(String.valueOf(hotelDto.getFloorsAmount()));
            category_jfxcombobox.getSelectionModel().select(hotelDto.getCategory());
            hotelfranchise_jfxcombobox.getSelectionModel().select(hotelDto.getHotelFranchise());
            province_jfxcombobox.getSelectionModel().select(hotelDto.getProvince());
            localization_jfxcombobox.getSelectionModel().select(hotelDto.getLocalization());

        }
    }
}
