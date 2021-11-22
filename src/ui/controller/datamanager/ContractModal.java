package ui.controller.datamanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

public class ContractModal extends DataManagerFormController{

    @FXML
    private JFXDatePicker startdate_jfxdatepicker;

    @FXML
    private JFXDatePicker finishdate_jfxdate;

    @FXML
    private JFXDatePicker conciliationdate_jfxdate;

    @FXML
    private JFXTextField description_jfxtextarea;

    @FXML
    private JFXComboBox<?> contracttype_jfxcombobox;

    @FXML
    private JFXButton insert_jfxbutton;

    @FXML
    private JFXButton cancel_jfxbutton;

    @FXML
    void cancel(ActionEvent event) {

    }

    @Override
    public void insert(ActionEvent event) {

    }

    @Override
    public void update(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

