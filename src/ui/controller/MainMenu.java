package ui.controller;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setHamburgerTransition();

        try {
            setDrawerSidePane();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            System.out.println(node.getId());
            node.setOnMouseClicked(event -> {
                switch (node.getId()) {
                    case "datamanager_jfxbutton":
                        dynamiccontainer_anchorpane.setVisible(true);
                        dynamiccontainer_anchorpane.getChildren().clear();

                        try {
                            dynamiccontainer_anchorpane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ui/view/data_manager.fxml"))));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "settings_jfxbutton":
                        System.out.println("Settings");
                        break;

                    case "aboutus_jfxbutton":
                        System.out.println("About Us");
                        break;

                    default:
                        break;
                }
            });
        });
    }
}
