package ui.controller.datamanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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

import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class HotelModal extends DataManagerFormController {
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

    @Override
    public void update(ActionEvent event) {

    }
}
