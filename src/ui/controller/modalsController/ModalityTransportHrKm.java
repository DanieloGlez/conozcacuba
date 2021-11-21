package ui.controller.modalsController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ModalityTransportHrKm implements Initializable {

    @FXML
    private JFXTextField tf_costTraveledKm;

    @FXML
    private JFXTextField tf_costKmExtras;

    @FXML
    private JFXTextField tf_costHrExtras;

    @FXML
    private JFXComboBox<?> cb_contract;

    @FXML
    private JFXComboBox<?> cb_vehicle;

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
