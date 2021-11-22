package ui.controller.datamanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ModalityTransportHrKm extends DataManagerFormController {

    @FXML
    private JFXTextField travelcost_jfxtextfield;

    @FXML
    private JFXTextField kmextracost_jfxtextfield;

    @FXML
    private JFXTextField hoursextracost_jfxtextfield;

    @FXML
    private JFXComboBox<?> contract_jfxcombobox;

    @FXML
    private JFXComboBox<?> vehicle_combobox;


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
