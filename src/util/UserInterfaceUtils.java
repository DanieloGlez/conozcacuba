package util;

import dto.ContractServiceDto;
import dto.Dto;
import dto.RelationContractServiceDailyActivityDto;
import dto.nom.NomenclatorDto;
import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import ui.Main;
import ui.controller.datamanager.DataManagerFormController;
import ui.controller.datamanager.NomenclatorForm;

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


    public static Stage showDataManagerForm(String dtoClassSimpleName, Dto dto, Stage stage) throws IOException {
        Stage modalStage = new Stage();
        modalStage.initModality(Modality.WINDOW_MODAL);
        modalStage.initOwner(stage);

        Scene scene = null;
        String url = null;


        if(ConstantUtils.getTableNames().get(dtoClassSimpleName).getSuperclass().equals(NomenclatorDto.class)) {
            url = "/ui/view/datamanager/nomenclator_datamanager_form.fxml";

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(url));
            scene = new Scene(fxmlLoader.load());

            NomenclatorForm nomenclatorForm = fxmlLoader.getController();
            nomenclatorForm.initializeNomenclatorClassName(dtoClassSimpleName);
            nomenclatorForm.setDto(dto);

        }
        else {
            url = "/ui/view/datamanager/" + dtoClassSimpleName.toLowerCase() + "_datamanager_form.fxml";

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(url));

            scene = new Scene(fxmlLoader.load());
            DataManagerFormController dataManagerFormController = fxmlLoader.getController();
            dataManagerFormController.setDto(dto);
        }

        modalStage.setTitle("Touristic Packages Planning");
        modalStage.setScene(scene);
        return modalStage;
    }

    public static Stage showModalWindow(Stage stage, String resourceUrl) throws IOException {
        Stage modalStage = new Stage();
        modalStage.initModality(Modality.WINDOW_MODAL);
        modalStage.initOwner(stage);

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(resourceUrl));
        Scene scene = new Scene(fxmlLoader.load());

        modalStage.setTitle("Touristic Packages Planning");
        modalStage.setScene(scene);
        return modalStage;
    }
}
