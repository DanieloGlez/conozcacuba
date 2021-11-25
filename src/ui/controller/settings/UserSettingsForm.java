package ui.controller.settings;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dto.fun.RoleDto;
import dto.fun.UserDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import service.ServicesLocator;
import util.EncryptionUtils;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserSettingsForm implements Initializable {
    @FXML
    private JFXButton insert_jfxbutton;

    @FXML
    private JFXTextField name_jfxtextfield;

    @FXML
    private JFXTextField username_jfxtextfield;

    @FXML
    private JFXPasswordField password_passwordfield;

    @FXML
    private JFXPasswordField confirmpassword_passwordfield;

    @FXML
    private JFXComboBox<RoleDto> role_jfxcombobox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            role_jfxcombobox.getItems().addAll(ServicesLocator.getRoleServices().loadAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void insert(ActionEvent event) throws NoSuchAlgorithmException, SQLException {
        ServicesLocator.getUserServices().insert(new UserDto(
                0,
                EncryptionUtils.getMd5From(username_jfxtextfield.getText()),
                name_jfxtextfield.getText(),
                EncryptionUtils.getMd5From(password_passwordfield.getText()),
                role_jfxcombobox.getSelectionModel().getSelectedItem()
        ));

        ((Stage) insert_jfxbutton.getScene().getWindow()).close();
    }
}
