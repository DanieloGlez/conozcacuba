package ui.controller;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import service.ServicesLocator;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainMenu implements Initializable {
    private HamburgerBasicCloseTransition hamburgerBasicCloseTransition;

    @FXML
    private AnchorPane container_anchorpane;

    @FXML
    private AnchorPane dynamiccontainer_anchorpane;

    @FXML
    private JFXDrawer drawer_jfxdrawer;

    @FXML
    private JFXHamburger hamburger_jfxhamburger;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setHamburgerTransition();

        try {
            setDrawerSidePane();
            dynamiccontainer_anchorpane.setVisible(true);
            dynamiccontainer_anchorpane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ui/view/home.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        handleDrawerEvent(true);

        try {
            ServicesLocator.getContractHotelReportServices().loadAll().forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setHamburgerTransition() {
        hamburgerBasicCloseTransition = new HamburgerBasicCloseTransition(hamburger_jfxhamburger);
        hamburgerBasicCloseTransition.setRate(-1);

        hamburger_jfxhamburger.setOnMouseClicked(event -> handleDrawerEvent(true));
    }

    private void handleDrawerEvent(boolean autoTransition) {
        if (autoTransition) {
            hamburgerBasicCloseTransition.setRate(hamburgerBasicCloseTransition.getRate() * -1);
            hamburgerBasicCloseTransition.play();

            if (drawer_jfxdrawer.isOpened()) {
                drawer_jfxdrawer.close();
                drawer_jfxdrawer.setDisable(true);
            } else {
                drawer_jfxdrawer.setDisable(false);
                drawer_jfxdrawer.open();
            }
        }
    }

    private void setDrawerSidePane() throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ui/view/side_bar.fxml")));
        drawer_jfxdrawer.setSidePane(anchorPane);

        VBox container_vbox = (VBox) anchorPane.getChildren().get(0);
        container_vbox.getChildren().forEach(node -> {
            node.setOnMouseClicked(event -> {
                switch (node.getId()) {
                    case "home_jfxbutton":
                        try {
                            switchView("/ui/view/home.fxml", false);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "datamanager_jfxbutton":
                        try {
                            switchView("/ui/view/datamanager/data_manager.fxml", true);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "report_jfxbutton":
                        try {
                            switchView("/ui/view/report/report_menu.fxml", true);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "settings_jfxbutton":
                        try {
                            switchView("/ui/view/settings/settings_menu.fxml", true);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;

                    default:
                        break;
                }
            });
        });
    }

    private void switchView(String url, boolean autoTransition) throws IOException {
        dynamiccontainer_anchorpane.getChildren().clear();
        dynamiccontainer_anchorpane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource(url))));
        handleDrawerEvent(autoTransition);
    }
}
