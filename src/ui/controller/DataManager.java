package ui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class DataManager implements Initializable {

    @FXML
    private AnchorPane container_anchorpane;

    @FXML
    private JFXTreeTableView<?> datamanager_jfxtreetableview;

    @FXML
    private JFXButton insert_jfxbutton;

    @FXML
    private JFXButton update_jfxbutton;

    @FXML
    private JFXButton delete_jfxbutton;

    @FXML
    private JFXListView<String> tables_jfxlistview;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTablesJfxListView();
    }

    @FXML
    void delete(ActionEvent event) {

    }

    @FXML
    void insert(ActionEvent event) {

    }

    @FXML
    void update(ActionEvent event) {

    }

    void initializeTablesJfxListView() {
        fillTablesJfxListView();
        tables_jfxlistview.setOnMouseClicked(event -> {
            JFXListCell<?> jfxListCell = (JFXListCell<?>) event.getTarget();
            String tableName = jfxListCell.getText();
            showContentInDataManagerJfxTreeTableView(tableName);
        });
    }

    void fillTablesJfxListView() {
        tables_jfxlistview.getItems().addAll(
                "CompanyService",
                "CompanyTransport",
                "ContractHotel",
                "ContractService",
                "ContractTransport",
                "DailyActivity",
                "FoodPlan",
                "Hotel",
                "HotelFranchise",
                "Localization",
                "ModalityCommercial",
                "ModalityTransport",
                "ModalityTransportHrKm",
                "ModalityTransportKm",
                "ModalityTransportRt",
                "User"
        );
    }

    void showContentInDataManagerJfxTreeTableView(String tableName) {
        String dtoClassName = tableName + "Dto";

        switch (dtoClassName) {
            case "UserDto":
                // TODO implement a base case as example for the rest of cases
                System.out.println("Showing " + tableName + " table");
                break;

            default:
                System.out.println(tableName + " is not a case inside the switch yet");
                break;
        }
    }
}

