package ui.controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import service.ServicesLocator;
import util.ConstantUtils;

import java.net.URL;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
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
        try {
            initializeTablesJfxListView();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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

    void initializeTablesJfxListView() throws SQLException, ClassNotFoundException {
        fillTablesJfxListView();
        tables_jfxlistview.setOnMouseClicked(event -> {
            JFXListCell<?> jfxListCell = (JFXListCell<?>) event.getTarget();
            String tableName = jfxListCell.getText();
            try {
                showContentInDataManagerJfxTreeTableView(tableName);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }



    void fillTablesJfxListView() throws SQLException, ClassNotFoundException {

        tables_jfxlistview.getItems().addAll(ConstantUtils.getTableName());

    }

    void showContentInDataManagerJfxTreeTableView(String tableName) throws SQLException, ClassNotFoundException {
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

