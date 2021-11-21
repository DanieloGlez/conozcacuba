package ui.controller.modalsController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class HotelModal implements Initializable {


    @FXML
    private JFXTextField tf_nameHotel;

    @FXML
    private JFXTextField tf_addres;

    @FXML
    private JFXTextField tf_telephoneNumber;

    @FXML
    private JFXTextField tf_fax;

    @FXML
    private JFXTextField tf_distanceCity;

    @FXML
    private JFXTextField tf_distanceAirport;

    @FXML
    private JFXTextField tf_roomsAmount;

    @FXML
    private JFXTextField tf_email;

    @FXML
    private JFXComboBox<?> cb_categories;

    @FXML
    private JFXComboBox<?> cb_localizations;

    @FXML
    private JFXComboBox<?> cb_hotelFranchises;

    @FXML
    private JFXComboBox<?> cb_provinces;

    @FXML
    private JFXButton jfxbtn_insert;

    @FXML
    private JFXButton jfxbtn_cancel;

    @FXML
    void cancelIserntion(ActionEvent event) {

    }

    @FXML
    void insertModality(ActionEvent event) {

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
