package ui.controller.datamanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import dto.ContractDto;
import dto.nom.CompanyServiceDto;
import dto.nom.DailyActivityDto;
import dto.nom.ServiceTypeDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import org.controlsfx.control.CheckComboBox;
import service.ServicesLocator;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ContractServiceModal extends DataManagerFormController {

    @FXML
    private JFXButton insert_jfxbutton;

    @FXML
    private JFXButton update_jfxbutton;

    @FXML
    private CheckComboBox<ServiceTypeDto> servicetypes_checkcombobox;

    @FXML
    private CheckComboBox<CompanyServiceDto> servicecompanies_checkcombobox;

    @FXML
    private CheckComboBox<DailyActivityDto> dailyactivities_checkcombobox;

    @FXML
    private JFXComboBox<ContractDto> contract_jfxcombobox;

    @FXML
    private JFXComboBox<?> province_jfxcombobox;

    @FXML
    private Spinner<?> paxcost_spinner;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            servicetypes_checkcombobox.getItems().addAll(ServicesLocator.getServiceTypeServices().loadAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(ActionEvent event) {

    }

    @Override
    public void update(ActionEvent event) {

    }
}