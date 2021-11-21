package ui.controller.modalsController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class RoomTypeModal implements Initializable {
    @FXML
    private JFXTextField tf_roomTypeName;

    @FXML
    private JFXButton jfxbtn_insert;

    @FXML
    private JFXButton jfxbtn_cancel;

    @FXML
    void cancelIserntion(ActionEvent event) {

    }

    @FXML
    void insertRoomType(ActionEvent event) {

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
