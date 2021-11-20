package ui.controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import service.Services;
import service.ServicesLocator;
import util.ConstantUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class DataManager implements Initializable {

    @FXML
    private AnchorPane container_anchorpane;

    @FXML
    private TableView datamanager_tableview;

    @FXML
    private JFXButton insert_jfxbutton;

    @FXML
    private JFXButton update_jfxbutton;

    @FXML
    private JFXButton delete_jfxbutton;

    @FXML
    private JFXListView<String> tables_jfxlistview;

    Services currentlyInUseServices;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            initializeTablesJfxListView();
        } catch (SQLException | ClassNotFoundException e) {
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
                showContentInDataManagerTableView(tableName);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                System.out.println("get" + tableName + "Services() hasn't been implemented yet in ServicesLocator class");
            }
        });
    }

    void fillTablesJfxListView() {
        tables_jfxlistview.getItems().addAll(ConstantUtils.getTableNames().keySet());
    }

    void showContentInDataManagerTableView(String tableName) throws ClassNotFoundException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        Map<String, Class> tableNames = ConstantUtils.getTableNames();

        // Clear Table
        datamanager_tableview.getColumns().clear();
        datamanager_tableview.getItems().clear();

        // Add Columns
        getColumnsFromClass(tableNames.get(tableName)).forEach(tableColumn -> {
            datamanager_tableview.getColumns().add(tableColumn);
        });

        // Resize Columns
        datamanager_tableview.getColumns().forEach(tableColumn -> {
            ((TableColumn) tableColumn).prefWidthProperty().bind(datamanager_tableview.widthProperty().divide(datamanager_tableview.getColumns().size()));
        });

        // Add Objects
        getDtosFromServices(tableName).forEach(dto -> {
            datamanager_tableview.getItems().add(dto);
        });
    }

    List<TableColumn> getColumnsFromClass(Class dtoClass) {
        List<TableColumn> tableColumns = new LinkedList<>();

        for (Field declaredField : dtoClass.getDeclaredFields()) {
            String declaredFieldName = declaredField.getName();

            TableColumn<?, ?> currentTableColumn = new TableColumn<>(declaredFieldName);
            currentTableColumn.setCellValueFactory(new PropertyValueFactory<>(declaredFieldName));

            tableColumns.add(currentTableColumn);
        }

        return tableColumns;
    }

    List getDtosFromServices(String entityName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, SQLException {
        Services<?> service = (Services<?>) ServicesLocator.class.getMethod("get" + entityName + "Services").invoke(null);
        return service.loadAll();
    }
}

