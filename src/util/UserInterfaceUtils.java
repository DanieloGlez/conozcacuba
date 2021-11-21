package util;

import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import ui.Main;

import java.io.IOException;

public class UserInterfaceUtils {
    public static void showTemporaryLabel(Label label, String message, Color color, int time) {
        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(time));
        pauseTransition.setOnFinished(event -> label.setVisible(false));

        label.setText(message);
        label.setTextFill(color);
        label.setVisible(true);

        pauseTransition.play();
    }

    public static void changeScene(Pane containerPane, String fxmlURL) throws IOException {
        Stage stage = (Stage) containerPane.getScene().getWindow();
        AnchorPane anchorPane = new FXMLLoader(Main.class.getResource(fxmlURL)).load();
        Scene scene = new Scene(anchorPane);

        stage.setScene(scene);
        stage.centerOnScreen();
    }


    public static void createModalView(String fxmlURL) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/ui/view/datamanager/"+fxmlURL));
        Scene scene = new Scene(fxmlLoader.load());
        Stage modalStage = new Stage();

        System.out.println(true);
        modalStage.setTitle("Touristic Packages Planning");
        modalStage.setScene(scene);
        modalStage.show();
    }
}
