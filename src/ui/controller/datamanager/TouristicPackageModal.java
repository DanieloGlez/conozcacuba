package ui.controller.datamanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.*;
import dto.nom.VehicleBrandDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;
import service.ServicesLocator;
import sun.awt.image.ImageWatched;
import util.Validator;

import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class TouristicPackageModal extends DataManagerFormController {
    Validator p = new Validator();
    @FXML
    private JFXTextField promotionalname_jfxtextfield;

    @FXML
    private JFXTextField daysamount_jfxtextfield;

    @FXML
    private JFXTextField nightsamount_jfxtextfield;

    @FXML
    private JFXTextField paxamount_jfxtextfield;


    @FXML
    private JFXTextField hotelairportcost_jfxtextfield;

    @FXML
    private CheckComboBox<ContractHotelDto> contractsHotel_checkcombobox;

    @FXML
    private CheckComboBox<ContractTransportDto> contractsTransport_checkcombobox;

    @FXML
    private CheckComboBox<ContractServiceDto> contractsServices_checkcombobox;

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
        float costHotel = getTotalHotelCost();
        float costTransport = getTotalModalityTransportCost();

        if (p.validateNumberFloat(hotelairportcost_jfxtextfield) && p.validateNumberInteger(daysamount_jfxtextfield) && p.validateNumberInteger(nightsamount_jfxtextfield) && p.validateNumberFloat(paxamount_jfxtextfield) && p.validateText(promotionalname_jfxtextfield)) {
            TouristicPackageDto touristicPackageDto = new TouristicPackageDto(
                    0,
                    promotionalname_jfxtextfield.getText(),
                    Integer.parseInt(daysamount_jfxtextfield.getText()),
                    Integer.parseInt(nightsamount_jfxtextfield.getText()),
                    Integer.parseInt(paxamount_jfxtextfield.getText()),
                    costHotel,
                    costTransport,
                    Float.parseFloat(hotelairportcost_jfxtextfield.getText()),
                    costHotel + costTransport,
                    (costHotel + costTransport) * (float) 1.1);
            ServicesLocator.getTouristicPackageServices().insert(touristicPackageDto);
            List<ContractDto> contractDtos = new LinkedList<>();
            contractDtos.addAll(contractsHotel_checkcombobox.getCheckModel().getCheckedItems());
            contractDtos.addAll(contractsTransport_checkcombobox.getCheckModel().getCheckedItems());
            contractDtos.addAll(contractsServices_checkcombobox.getCheckModel().getCheckedItems());
            touristicPackageDto.setContracts(contractDtos);
            ServicesLocator.getRelationTouristicPackageContractTransportServices().insert(touristicPackageDto);

            ((Stage) hotelairportcost_jfxtextfield.getScene().getWindow()).close();
        }
    }

    private float getTotalModalityTransportCost() throws SQLException {
        LinkedList<ModalityTransportDto> modalityTransportDtos = new LinkedList<>();
        float costTransport = 0;
        modalityTransportDtos.addAll(ServicesLocator.getModalityTransportHrKmServices().loadAll());
        modalityTransportDtos.addAll(ServicesLocator.getModalityTransportKmServices().loadAll());
        modalityTransportDtos.addAll(ServicesLocator.getModalityTransportRtServices().loadAll());

        List<ContractTransportDto> contractTransportDtos = contractsTransport_checkcombobox.getCheckModel().getCheckedItems();

        ListIterator<ModalityTransportDto> listIteratorModality = modalityTransportDtos.listIterator();
        ListIterator<ContractTransportDto> listIteratorContract = contractTransportDtos.listIterator();

        while (listIteratorModality.hasNext()) {
            ModalityTransportDto modalityTransportDtoCurrent = listIteratorModality.next();

            while (listIteratorContract.hasNext()) {
                ContractTransportDto contractTransportDtoCurrent = listIteratorContract.next();
                if (modalityTransportDtoCurrent instanceof ModalityTransportRtDto) {
                    if (((ModalityTransportRtDto) modalityTransportDtoCurrent).getContractTransport().equals(contractTransportDtoCurrent))
                        costTransport += modalityTransportDtoCurrent.getTotalCost();

                } else if (modalityTransportDtoCurrent instanceof ModalityTransportKmDto) {
                    if (((ModalityTransportKmDto) modalityTransportDtoCurrent).getContractTransport().equals(contractTransportDtoCurrent))
                        costTransport += modalityTransportDtoCurrent.getTotalCost();

                } else if (((ModalityTransportHrKmDto) modalityTransportDtoCurrent).getContractTransport().equals(contractTransportDtoCurrent))
                    costTransport += modalityTransportDtoCurrent.getTotalCost();

            }
        }

        return costTransport;

    }

    private float getTotalHotelCost() throws SQLException {
        List<ContractHotelDto> listContractHotel = contractsHotel_checkcombobox.getCheckModel().getCheckedItems();
        ListIterator<ContractHotelDto> listIterator = listContractHotel.listIterator();
        float totalHotelCost = 0;
        while (listIterator.hasNext()) {
            totalHotelCost += ServicesLocator.getRelationContractHotelRoomFoodSeasonServices().load(listIterator.next().getId());
        }

        return totalHotelCost;
    }

    @Override
    public void update(ActionEvent event) throws SQLException {

        float costHotel = getTotalHotelCost();
        float costTransport = getTotalModalityTransportCost();


        ((TouristicPackageDto) dto).setPromotionalName(promotionalname_jfxtextfield.getText());
        ((TouristicPackageDto) dto).setDaysAmount(Integer.parseInt(nightsamount_jfxtextfield.getText()));
        ((TouristicPackageDto) dto).setNightsAmount(Integer.parseInt(nightsamount_jfxtextfield.getText()));
        ((TouristicPackageDto) dto).setPaxAmount(Integer.parseInt(paxamount_jfxtextfield.getText()));
        ((TouristicPackageDto) dto).setCostHotel(costHotel);
        ((TouristicPackageDto) dto).setCostTransport(costTransport);
        ((TouristicPackageDto) dto).setCostTransportHA(Float.parseFloat(hotelairportcost_jfxtextfield.getText()));
        ((TouristicPackageDto) dto).setCostTotal(costHotel + costTransport);
        ((TouristicPackageDto) dto).setPrice((costHotel + costTransport) * (float) 1.1);
        List<ContractDto> contractDtos = new LinkedList<>();
        contractDtos.addAll(contractsHotel_checkcombobox.getCheckModel().getCheckedItems());
        contractDtos.addAll(contractsTransport_checkcombobox.getCheckModel().getCheckedItems());
        contractDtos.addAll(contractsServices_checkcombobox.getCheckModel().getCheckedItems());
        ((TouristicPackageDto) dto).setContracts(contractDtos);
        if (p.validateNumberFloat(hotelairportcost_jfxtextfield) && p.validateNumberInteger(daysamount_jfxtextfield) && p.validateNumberInteger(nightsamount_jfxtextfield) && p.validateNumberFloat(paxamount_jfxtextfield) && p.validateText(promotionalname_jfxtextfield)) {


            ServicesLocator.getTouristicPackageServices().update((TouristicPackageDto) dto);
            ServicesLocator.getRelationTouristicPackageContractTransportServices().update((TouristicPackageDto) dto);

            ((Stage) hotelairportcost_jfxtextfield.getScene().getWindow()).close();
        }
    }


    public void addCheckComboBoxItems() throws SQLException {

        contractsHotel_checkcombobox.getItems().addAll(ServicesLocator.getContractHotelServices().loadAll());
        contractsTransport_checkcombobox.getItems().addAll(ServicesLocator.getContractTransportServices().loadAll());
        contractsServices_checkcombobox.getItems().addAll(ServicesLocator.getContractServiceServices().loadAll());

    }
}


