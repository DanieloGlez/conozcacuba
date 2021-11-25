package ui.controller.settings;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import dto.fun.UserDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.ServicesLocator;
import util.ConfigurationUtils;
import util.UserInterfaceUtils;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SettingsMenu implements Initializable {
    UserDto selectedUserDto;

    @FXML
    private AnchorPane usercontrolcontainer_anchorpane;

    @FXML
    private JFXListView<UserDto> user_jfxlistview;

    @FXML
    private JFXButton insert_jfxbutton;

    @FXML
    private JFXButton delete_jfxbutton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (ConfigurationUtils.getActiveUser().isAdministrator()) {
            usercontrolcontainer_anchorpane.setDisable(false);

            try {
                user_jfxlistview.getItems().addAll(ServicesLocator.getUserServices().loadAll());
            } catch (SQLException e) {
                e.printStackTrace();
            }

            user_jfxlistview.setOnMouseClicked(event -> {
                JFXListCell<UserDto> jfxListCell = (JFXListCell<UserDto>) event.getTarget();
                selectedUserDto = jfxListCell.getItem();

                if (delete_jfxbutton.isDisable())
                    delete_jfxbutton.setDisable(false);
                else if (ConfigurationUtils.getActiveUser().getId() == selectedUserDto.getId())
                    delete_jfxbutton.setDisable(true);
            });
        }
    }

    @FXML
    void delete(ActionEvent event) throws SQLException {
        ServicesLocator.getUserServices().delete(selectedUserDto.getId());

        refreshJfxListViewContent();
    }

    @FXML
    void insert(ActionEvent event) throws IOException, SQLException {
        Stage modalStage = UserInterfaceUtils.showModalWindow((Stage) usercontrolcontainer_anchorpane.getScene().getWindow(), "/ui/view/settings/user_settings_form.fxml");
        modalStage.showAndWait();

        refreshJfxListViewContent();
    }

    void refreshJfxListViewContent() throws SQLException {
        user_jfxlistview.getItems().clear();
        user_jfxlistview.getItems().addAll(ServicesLocator.getUserServices().loadAll());
    }
}
