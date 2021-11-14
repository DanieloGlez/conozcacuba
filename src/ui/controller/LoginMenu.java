package ui.controller;

import com.jfoenix.controls.*;
import com.jfoenix.validation.RequiredFieldValidator;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar;
import dto.functionality.UserDto;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import org.json.simple.parser.ParseException;
import service.ServicesLocator;
import util.ConfigurationUtils;
import util.UserInterfaceUtils;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class LoginMenu implements Initializable {

    @FXML
    private AnchorPane container_anchorpane;

    @FXML
    private JFXTextField username_jfxtextfield;

    @FXML
    private JFXPasswordField password_jfxpasswordfield;

    @FXML
    private JFXComboBox<String> host_jfxcombobox;

    @FXML
    private JFXToggleButton remotehost_jfxtogglebutton;

    @FXML
    private Label errormessage_label;

    @FXML
    private JFXButton login_jfxbutton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator("This field is required");
        username_jfxtextfield.setValidators(requiredFieldValidator);
        password_jfxpasswordfield.setValidators(requiredFieldValidator);
    }

    @FXML
    void login(ActionEvent event) throws IOException, ParseException {
        if (formInputIsValid()) {
            ConfigurationUtils.initializeDatabaseHost(remotehost_jfxtogglebutton.isSelected());
            checkCredentials();
        }
    }

    boolean formInputIsValid() {
        boolean usernameIsValid = username_jfxtextfield.validate();
        boolean passwordIsValid = password_jfxpasswordfield.validate();
        return usernameIsValid && passwordIsValid;
    }

    void checkCredentials() {
        String inputUsername = username_jfxtextfield.getText();
        String inputPassword = password_jfxpasswordfield.getText();

        login_jfxbutton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        Task<UserDto> checkCredentialsTask = new Task<UserDto>() {
            @Override
            protected UserDto call() throws Exception {
                // Return variable
                UserDto userDto = null;

                // List with all users and input username
                List<UserDto> users = null;

                try {
                    users = ServicesLocator.getUserServices().getAll();
                } catch (SQLException e) {
                    UserInterfaceUtils.showTemporaryLabel(errormessage_label, "Unknown error", Color.RED, 5);
                }

                // Search the input username inside the users list
                if (users != null) {
                    Iterator<UserDto> iterator = users.iterator();
                    boolean found = false;
                    while (iterator.hasNext() && !found) {
                        UserDto currentUser = iterator.next();

                        if (currentUser.getUsername().equals(inputUsername)) {
                            userDto = currentUser;
                            found = true;
                        }
                    }
                }

                return userDto;
            }
        };

        checkCredentialsTask.setOnSucceeded(event -> {
            try {
                UserDto userDto = checkCredentialsTask.get();

                if(userDto != null) {
                    if(userDto.getPassword().equals(inputPassword)) {
                        goToMainMenu();
                    } else {
                        UserInterfaceUtils.showTemporaryLabel(errormessage_label, "Incorrect password", Color.RED, 5);
                    }
                } else {
                    UserInterfaceUtils.showTemporaryLabel(errormessage_label, "User not found", Color.RED, 5);
                }
            } catch (InterruptedException | ExecutionException | IOException e) {
                e.printStackTrace();
            }

            login_jfxbutton.setContentDisplay(ContentDisplay.TEXT_ONLY);
        });

        new Thread(checkCredentialsTask).start();
    }

    void goToMainMenu() throws IOException {
        UserInterfaceUtils.changeScene(container_anchorpane, "/ui/view/main_menu.fxml");
    }
}
