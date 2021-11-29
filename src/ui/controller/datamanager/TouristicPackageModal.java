package ui.controller.datamanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.ContractDto;
import dto.TouristicPackageDto;
import dto.VehicleDto;
import dto.nom.VehicleBrandDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;
import service.ServicesLocator;

import java.net.URL;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class TouristicPackageModal extends DataManagerFormController {
    @FXML
    private JFXTextField promotionalname_jfxtextfield;

    @FXML
    private JFXTextField daysamount_jfxtextfield;

    @FXML
    private JFXTextField nightsamount_jfxtextfield;

    @FXML
    private JFXTextField paxamount_jfxtextfield;

    @FXML
    private JFXTextField costhotel_jfxtextfield;

    @FXML
    private JFXTextField costtransport_jfxtextfield;

    @FXML
    private JFXTextField hotelairportcost_jfxtextfield;

    @FXML
    private JFXTextField totalcost_jfxtextfield;

    @FXML
    private JFXTextField pricepackage_jfxtextfield;

    @FXML
    private CheckComboBox<ContractDto> contracts_checkcombobox;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            addCheckComboBoxItems();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    @Override
    public void insert(ActionEvent event) throws SQLException {
        ServicesLocator.getTouristicPackageServices().insert(new TouristicPackageDto(
                0,
                promotionalname_jfxtextfield.getText(),
                Integer.parseInt(daysamount_jfxtextfield.getText()),
                Integer.parseInt(nightsamount_jfxtextfield.getText()),
                Integer.parseInt(paxamount_jfxtextfield.getText()),
                Float.parseFloat(costhotel_jfxtextfield.getText()),
                Float.parseFloat(costtransport_jfxtextfield.getText()),
                Float.parseFloat(hotelairportcost_jfxtextfield.getText()),
                Float.parseFloat(totalcost_jfxtextfield.getText()),
                Float.parseFloat(pricepackage_jfxtextfield.getText())));
                contracts_checkcombobox.getCheckModel().getCheckedItems();

    }

    @Override
    public void update(ActionEvent event) throws SQLException {
        ((TouristicPackageDto) dto).setPromotionalName(promotionalname_jfxtextfield.getText());
        ((TouristicPackageDto) dto).setDaysAmount(Integer.parseInt(nightsamount_jfxtextfield.getText()));
        ((TouristicPackageDto) dto).setNightsAmount(Integer.parseInt(nightsamount_jfxtextfield.getText()));
        ((TouristicPackageDto) dto).setPaxAmount(Integer.parseInt(paxamount_jfxtextfield.getText()));
        ((TouristicPackageDto) dto).setCostHotel(Float.parseFloat(costhotel_jfxtextfield.getText()));
        ((TouristicPackageDto) dto).setCostTransport(Float.parseFloat(costtransport_jfxtextfield.getText()));
        ((TouristicPackageDto) dto).setCostTransportHA(Float.parseFloat(hotelairportcost_jfxtextfield.getText()));
        ((TouristicPackageDto) dto).setCostTotal(Float.parseFloat(totalcost_jfxtextfield.getText()));
        ((TouristicPackageDto) dto).setPrice(Float.parseFloat(pricepackage_jfxtextfield.getText()));

        ServicesLocator.getTouristicPackageServices().update((TouristicPackageDto) dto);
        contracts_checkcombobox.getCheckModel().getCheckedItems();

    }

    public void addCheckComboBoxItems() throws SQLException {

        contracts_checkcombobox.getItems().addAll(ServicesLocator.getContractServices().loadAll());

    }
}


