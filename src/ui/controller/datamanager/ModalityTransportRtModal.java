package ui.controller.datamanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ModalityTransportRtModal extends DataManagerFormController {

    @FXML
    private JFXTextField routedescription_jfxtextfield;

    @FXML
    private JFXTextField costroute_jfxtextfield;

    @FXML
    private JFXTextField costroundtrip_jfxtextfield;

    @FXML
    private JFXComboBox<?> contract_jfxcombobox;

    @FXML
    private JFXComboBox<?> vehicle_jfxcombobox;


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
