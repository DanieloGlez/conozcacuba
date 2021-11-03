package ui.controller;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import util.DBUtils;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainMenu implements Initializable {
    @FXML
    private AnchorPane container_anchorpane;

    @FXML
    private AnchorPane dynamiccontainer_anchorpane;

    @FXML
    private JFXDrawer drawer_jfxdrawer;

    @FXML
    private JFXHamburger hamburger_jfxhamburger;

    @FXML
    private VBox connectingtodb_vbox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connectToDatabase();
        setHamburgerTransition();

        try {
            setDrawerSidePane();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void connectToDatabase() {
        Task task = new Task() {
            @Override
            protected Object call() throws Exception {
                try {
                    DBUtils.createConnection(
                            "jdbc:postgresql://ec2-44-194-6-121.compute-1.amazonaws.com:5432/dho189ncehplp",
                            "vxwdrwradnlahk",
                            "5adc174d3fe736cfede769fad13ef3ddef00ff512a583d37784bb942035ea661"
                    );
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                return null;
            }
        };

        task.setOnSucceeded(event -> {
            connectingtodb_vbox.setVisible(false);
            hamburger_jfxhamburger.setDisable(false);
        });

        new Thread(task).start();
    }

    private void setHamburgerTransition() {
        HamburgerBasicCloseTransition hamburgerBasicCloseTransition = new HamburgerBasicCloseTransition(hamburger_jfxhamburger);
        hamburgerBasicCloseTransition.setRate(-1);

        hamburger_jfxhamburger.setOnMouseClicked(event -> {
            hamburgerBasicCloseTransition.setRate(hamburgerBasicCloseTransition.getRate() * -1);
            hamburgerBasicCloseTransition.play();

            if(drawer_jfxdrawer.isOpened())
                drawer_jfxdrawer.close();
            else
                drawer_jfxdrawer.open();
        });
    }

    private void setDrawerSidePane() throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ui/view/side_bar.fxml")));
        drawer_jfxdrawer.setSidePane(anchorPane);

        VBox container_vbox = (VBox) anchorPane.getChildren().get(0);
        container_vbox.getChildren().forEach(node -> {
            node.setOnMouseClicked(event -> {
                switch (node.getId()) {
                    case "datamanager_button":
                        dynamiccontainer_anchorpane.setVisible(true);
                        dynamiccontainer_anchorpane.getChildren().clear();

                        try {
                            dynamiccontainer_anchorpane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ui/view/data_manager.fxml"))));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "settings_button":
                        System.out.println("Settings");
                        break;

                    case "aboutus_button":
                        System.out.println("About Us");
                        break;

                    default:
                        break;
                }
            });
        });
    }
}
