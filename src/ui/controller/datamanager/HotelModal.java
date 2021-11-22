package ui.controller.datamanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.controlsfx.control.CheckComboBox;

import java.net.URL;
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
    private JFXComboBox<?> localization_jfxcombobox;

    @FXML
    private JFXComboBox<?> hotelfranchise_jfxcombobox;

    @FXML
    private JFXComboBox<?> province_jfxcombobox;

    @FXML
    private CheckComboBox<?> roomtypes_checkcombobox;

    @FXML
    private CheckComboBox<?> foodplans_checkcombobox;

    @FXML
    private CheckComboBox<?> commercialmodalities_checkcombobox;

    @FXML
    private JFXComboBox<?> category_jfxcombobox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void insert(ActionEvent event) {

    }

    @Override
    public void update(ActionEvent event) {

    }
}
