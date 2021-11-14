package ui;

import dto.functionality.UserDto;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.Service;
import service.ServicesLocator;
import util.ConfigurationUtils;

import java.lang.reflect.Field;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/ui/view/login_menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        primaryStage.setTitle("Touristic Packages Planning");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
