package ui.controller.datamanager;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import dto.Dto;
import dto.fun.UserDto;
import dto.nom.NomenclatorDto;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import service.Services;
import service.ServicesLocator;
import util.ConfigurationUtils;
import util.ConstantUtils;
import util.UserInterfaceUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class DataManager implements Initializable {
    private String selectedTableName;
    private Services currentlyInUseServices;
    private Class selectedTableClass;

    @FXML
    private AnchorPane container_anchorpane;

    @FXML
    private TableView datamanager_tableview;

    @FXML
    private StackPane loadingdialog_stackpane;

    @FXML
    private VBox crudcontrolscontainer_vbox;

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
        UserDto activeUser = ConfigurationUtils.getActiveUser();

        if(activeUser.isAdministrator() || activeUser.isResponsable()) {
            container_anchorpane.setDisable(false);

            try {
                initializeTablesJfxListView();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void delete(ActionEvent event) throws NoSuchMethodException, SQLException, InvocationTargetException, IllegalAccessException {
        Services<?> service = (Services<?>) ServicesLocator.class.getMethod("get" + selectedTableName + "Services").invoke(null);
        Dto selectedDto = (Dto) datamanager_tableview.getSelectionModel().getSelectedItem();
        service.delete(selectedDto.getId());
        refreshChanges();
    }

    @FXML
    void insert(ActionEvent event) {
        try {
            Stage dataManagerFormStage = UserInterfaceUtils.showDataManagerForm(selectedTableName, null, (Stage) container_anchorpane.getScene().getWindow());
            dataManagerFormStage.showAndWait();

            refreshChanges();
        } catch (IOException e) {
            System.out.println(selectedTableName + " is not a datamanager form fxml file");
        }
    }

    @FXML
    void update(ActionEvent event) {
        Dto selectedDto = (Dto) datamanager_tableview.getSelectionModel().getSelectedItem();

        try {
            Stage dataManagerFormStage = UserInterfaceUtils.showDataManagerForm(selectedTableName, selectedDto, (Stage) container_anchorpane.getScene().getWindow());
            dataManagerFormStage.showAndWait();

            refreshChanges();
        } catch (IOException e) {
            System.out.println(selectedTableName + " is not a datamanager form fxml file");
        }
    }

    void initializeTablesJfxListView() throws SQLException, ClassNotFoundException {
        fillTablesJfxListView();

        tables_jfxlistview.setOnMouseClicked(event -> {
            JFXListCell<?> jfxListCell = (JFXListCell<?>) event.getTarget();
            selectedTableName = jfxListCell.getText();

            try {
                showContentInDataManagerTableView(selectedTableName);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                System.out.println("get" + selectedTableName + "Services() hasn't been implemented yet in ServicesLocator class");
            }
        });

        tables_jfxlistview.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                showContentInDataManagerTableView(newValue);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                System.out.println("get" + newValue + "Services() hasn't been implemented yet in ServicesLocator class");
            }
        });
    }

    void fillTablesJfxListView() {
        tables_jfxlistview.getItems().addAll(ConstantUtils.getTableNames().keySet());
    }

    void showContentInDataManagerTableView(String tableName) throws ClassNotFoundException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
       Task<List<?>> callServiceContentTask = new Task<List<?>>() {
           @Override
           protected List<?> call() throws Exception {
               // Show loading dialog
               crudcontrolscontainer_vbox.setVisible(false);
               loadingdialog_stackpane.setVisible(true);

               // Disable views
               datamanager_tableview.setDisable(true);
               tables_jfxlistview.setDisable(true);

               // Add Objects
               return getDtosFromServices(tableName);
               /*getDtosFromServices(tableName).forEach(dto -> {
                   datamanager_tableview.getItems().add(dto);
               });*/
           }
       };

        callServiceContentTask.setOnSucceeded(event -> {
            Map<String, Class> tableNames = ConstantUtils.getTableNames();

            // Clear Table
            datamanager_tableview.getColumns().clear();
            datamanager_tableview.getItems().clear();

            // Add Columns
            getColumnsFromClass(tableNames.get(tableName)).forEach(tableColumn -> {
                datamanager_tableview.getColumns().add(tableColumn);
            });

            // Add Objects
            try {
                callServiceContentTask.get().forEach(dto -> {
                    datamanager_tableview.getItems().add(dto);
                });
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            // Resize Columns
            datamanager_tableview.setColumnResizePolicy(new Callback<TableView.ResizeFeatures, Boolean>() {
                @Override
                public Boolean call(TableView.ResizeFeatures param) {
                    return true;
                }
            });

            // Show crud controls
            crudcontrolscontainer_vbox.setVisible(true);
            loadingdialog_stackpane.setVisible(false);

            // Enable views
            datamanager_tableview.setDisable(false);
            tables_jfxlistview.setDisable(false);
        });

        new Thread(callServiceContentTask).start();
    }

    List<TableColumn> getColumnsFromClass(Class dtoClass) {
        List<TableColumn> tableColumns = new LinkedList<>();

        // Avoid blank content in nomenclator table
        if(dtoClass.getSuperclass().equals(NomenclatorDto.class))
            dtoClass = dtoClass.getSuperclass();

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

    void refreshChanges() {
        tables_jfxlistview.getSelectionModel().select(selectedTableName);
    }
}

