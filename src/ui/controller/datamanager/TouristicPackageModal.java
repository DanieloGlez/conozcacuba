package ui.controller.datamanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class TouristicPackageModal extends DataManagerFormController {
    @FXML
    private JFXTextField promotionalname_jfxtextfield;

    @FXML
    private JFXTextField daysamount_jfxtextfield;

    @FXML
    private JFXTextField nightsamount_jfxtextfield;

    @FXML
    private JFXTextField paxamount_jfxtextfield;

    @FXML
    private JFXTextField costhotel_jfxtextfield;

    @FXML
    private JFXTextField costtransport_jfxtextfield;

    @FXML
    private JFXTextField hotelairportcost_jfxtextfield;


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
