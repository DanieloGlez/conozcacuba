package ui.controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import dto.ContractServiceDto;
import dto.nom.NomenclatorDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import service.Services;
import service.ServicesLocator;
import util.ConstantUtils;
import util.UserInterfaceUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

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
        String selectedTableName = tables_jfxlistview.getSelectionModel().getSelectedItem().toLowerCase();
        System.out.println(selectedTableName);
        try {
            UserInterfaceUtils.createModalView("/ui/view/datamanager/" + selectedTableName + "_datamanager_form.fxml");
        } catch (IOException e) {
            System.out.println(selectedTableName + "is not a datamanager form fxml file");
        }
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

        /*LinkedList<ContractServiceDto> p = (LinkedList<ContractServiceDto>) ServicesLocator.getContractServiceServices().loadAll();
        Iterator<ContractServiceDto> i = p.iterator();
        while (i.hasNext())
            System.out.println(i.next().getId());*/


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

        // Avoid blank content in nomenclator table
        if (dtoClass.getSuperclass().equals(NomenclatorDto.class))
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
}

