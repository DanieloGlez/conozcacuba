package ui.controller.modalsController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class TouristicPackageModal implements Initializable {
    @FXML
    private JFXTextField tf_promotionalName;

    @FXML
    private JFXTextField tf_daysAmount;

    @FXML
    private JFXTextField tf_nightsAmount;

    @FXML
    private JFXTextField tf_paxAmount;

    @FXML
    private JFXTextField tf_costHotel;

    @FXML
    private JFXTextField tf_costTransport;

    @FXML
    private JFXTextField tf_costTransportHotelAirport;

    @FXML
    private JFXButton jfxbtn_insert;

    @FXML
    private JFXButton jfxbtn_cancel;

    @FXML
    void cancelIserntion(ActionEvent event) {

    }

    @FXML
    void insertTouristcPackage(ActionEvent event) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
