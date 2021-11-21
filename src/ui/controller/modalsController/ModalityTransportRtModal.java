package ui.controller.modalsController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ModalityTransportRtModal implements Initializable {

    @FXML
    private JFXTextField tf_rt_description;

    @FXML
    private JFXTextField tf_cost_rt;

    @FXML
    private JFXTextField tf_cost_round_trip;

    @FXML
    private JFXButton jfxbtn_insert;

    @FXML
    private JFXButton jfxbtn_cancel;
    @FXML
    private JFXComboBox<?> cb_contracts;

    @FXML
    private JFXComboBox<?> cb_vehicles;

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
