package ui.controller.datamanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.validation.RegexValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import dto.ContractServiceDto;
import dto.Dto;
import dto.RelationContractServiceDailyActivityDto;
import dto.nom.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;
import service.ServicesLocator;
import util.UserInterfaceUtils;
import util.Validator;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.*;

public class ContractServiceModal extends DataManagerFormController {
    Validator p= new Validator();

    @FXML
    private AnchorPane container_anchorpane;
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
    private JFXComboBox<ContractTypeDto> contract_jfxcombobox;

    @FXML
    private JFXComboBox<ProvinceDto> province_jfxcombobox;

    @FXML
    private Spinner<Double> paxcost_spinner;

    @FXML
    private JFXDatePicker startdate_jfxdatepicker;

    @FXML
    private JFXDatePicker finishdate_jfxdatepicker;

    @FXML
    private JFXDatePicker conciliationndate_jfxdatepicker;
    @FXML
    private JFXTextArea description_jfxtextarea;


   /* @FXML
    private Spinner<Integer> idcontract_spinner;
*/

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeComboboxes();
        initializePaxCostSpinner();


        RegexValidator regexTextValidator = new RegexValidator("This field requires a text");
        RegexValidator regexNumericValidator = new RegexValidator("This field requires a Number");
        RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator("This field is require");
        regexTextValidator.setRegexPattern("[a-zA-Z].*" + "");
        regexNumericValidator.setRegexPattern("[+-]?\\d*(\\.\\d+)?");

        contract_jfxcombobox.getValidators().add(requiredFieldValidator);
        province_jfxcombobox.getValidators().add(requiredFieldValidator);
        description_jfxtextarea.getValidators().add(regexTextValidator);
        description_jfxtextarea.getValidators().add(requiredFieldValidator);


        contract_jfxcombobox.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) contract_jfxcombobox.validate();
        });

        province_jfxcombobox.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) province_jfxcombobox.validate();
        });

        description_jfxtextarea.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) description_jfxtextarea.validate();
        });


    }

    private void initializeComboboxes() {
        try {
            contract_jfxcombobox.getItems().addAll(ServicesLocator.getContractTypeServices().loadAll());
            servicetypes_checkcombobox.getItems().addAll(ServicesLocator.getServiceTypeServices().loadAll());
            servicecompanies_checkcombobox.getItems().addAll(ServicesLocator.getCompanyServiceServices().loadAll());
            dailyactivities_checkcombobox.getItems().addAll(ServicesLocator.getDailyActivityServices().loadAll());
            province_jfxcombobox.getItems().addAll(ServicesLocator.getProvinceServices().loadAll());


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initializePaxCostSpinner() {
        double initialValue = 120;
        SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(120, 2000, initialValue);

        paxcost_spinner.setValueFactory(valueFactory);

    }


    @Override
    public void insert(ActionEvent event) throws SQLException {
        java.sql.Date startDate = Date.valueOf(startdate_jfxdatepicker.getValue());
        java.sql.Date finishDate = Date.valueOf(finishdate_jfxdatepicker.getValue());
        java.sql.Date conciliationDate = Date.valueOf(conciliationndate_jfxdatepicker.getValue());
        List<DailyActivityDto> listDaily = dailyactivities_checkcombobox.getCheckModel().getCheckedItems();

        if (p.validateTextOfTextArea(description_jfxtextarea) && p.validateDates(startdate_jfxdatepicker,finishdate_jfxdatepicker,conciliationndate_jfxdatepicker) &&p.validateCombobox(contract_jfxcombobox)&&p.validateCombobox(province_jfxcombobox)) {
            ContractServiceDto contractServiceDto = new ContractServiceDto(0,
                    startDate,
                    finishDate,
                    conciliationDate,
                    description_jfxtextarea.getText(),
                    contract_jfxcombobox.getValue(),
                    Float.parseFloat(paxcost_spinner.getValue().toString()),
                    province_jfxcombobox.getValue(),
                    listDaily,
                    servicecompanies_checkcombobox.getCheckModel().getCheckedItems(),
                    servicetypes_checkcombobox.getCheckModel().getCheckedItems()
            );

            ServicesLocator.getContractServiceServices().insert(contractServiceDto);
            ServicesLocator.getRelationContractServiceServiceTypeServices().insert(contractServiceDto);
            ServicesLocator.getRelationContractServiceCompanyServiceServices().insert(contractServiceDto);

            Stage pricesStage = null;
            Scene scene = null;
            pricesStage = (Stage) container_anchorpane.getScene().getWindow();
            List<DailyActivityDto> checkedItems = dailyactivities_checkcombobox.getCheckModel().getCheckedItems();
            DailyActivityPricesModal dailyActivityPricesModal = new DailyActivityPricesModal();
            dailyActivityPricesModal.initializeNameTablePrices(checkedItems, contractServiceDto.getId(), pricesStage);

        }
        else
            UserInterfaceUtils.showTemporaryLabel(new Label(),"Review your entries", Color.RED,3);
    }

    @Override
    public void update(ActionEvent event) throws SQLException {
        java.sql.Date startDate = Date.valueOf(startdate_jfxdatepicker.getValue());
        java.sql.Date finishDate = Date.valueOf(finishdate_jfxdatepicker.getValue());
        java.sql.Date conciliationDate = Date.valueOf(conciliationndate_jfxdatepicker.getValue());
        ContractServiceDto contractServiceDto = (ContractServiceDto) dto;
        servicetypes_checkcombobox.getItems().removeAll(((ContractServiceDto) dto).getServiceType());
        servicecompanies_checkcombobox.getItems().removeAll(((ContractServiceDto) dto).getCompaniesService());
        dailyactivities_checkcombobox.getItems().removeAll(((ContractServiceDto) dto).getDailyActivities());
        contractServiceDto.setServiceType(servicetypes_checkcombobox.getCheckModel().getCheckedItems());
        contractServiceDto.setCompaniesService(servicecompanies_checkcombobox.getCheckModel().getCheckedItems());
        contractServiceDto.setDailyActivities(dailyactivities_checkcombobox.getCheckModel().getCheckedItems());
        contractServiceDto.setPaxCost(Float.parseFloat(paxcost_spinner.getValue().toString()));
        contractServiceDto.setIdProvince(province_jfxcombobox.getValue());
        contractServiceDto.setDescription(description_jfxtextarea.getText());
        contractServiceDto.setStartDate(startDate);
        contractServiceDto.setFinishDate(finishDate);
        contractServiceDto.setConciliationDate(conciliationDate);

        if (p.validateTextOfTextArea(description_jfxtextarea) && p.validateDates(startdate_jfxdatepicker,finishdate_jfxdatepicker,conciliationndate_jfxdatepicker) &&p.validateCombobox(contract_jfxcombobox)&&p.validateCombobox(province_jfxcombobox)) {

            ServicesLocator.getContractServiceServices().update(contractServiceDto);
            ServicesLocator.getRelationContractServiceCompanyServiceServices().update(contractServiceDto);
            ServicesLocator.getRelationContractServiceServiceTypeServices().update(contractServiceDto);
            List<DailyActivityDto> dailyActivities = dailyactivities_checkcombobox.getCheckModel().getCheckedItems();
            int idContract = contractServiceDto.getId();
            ServicesLocator.getRelationContractServiceDailyActServices().delete(idContract);
            ListIterator<DailyActivityDto> listIterator = dailyActivities.listIterator();

            while (listIterator.hasNext()) {
                ServicesLocator.getRelationContractServiceDailyActServices().update(new RelationContractServiceDailyActivityDto((float) 90.0, idContract, listIterator.next()));
            }

            ((Stage) contract_jfxcombobox.getScene().getWindow()).close();
        }
    }

    @Override
    public void setDto(Dto dto) {
        super.setDto(dto);

        if (dto != null) {
            ContractServiceDto contractDto = (ContractServiceDto) dto;

            startdate_jfxdatepicker.setValue(contractDto.getStartDate().toLocalDate());
            finishdate_jfxdatepicker.setValue(contractDto.getFinishDate().toLocalDate());
            conciliationndate_jfxdatepicker.setValue(contractDto.getConciliationDate().toLocalDate());
            description_jfxtextarea.setText(contractDto.getDescription());
            contract_jfxcombobox.getSelectionModel().select(contractDto.getContractTypeDto());
            province_jfxcombobox.getSelectionModel().select(contractDto.getIdProvince());
            paxcost_spinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(100, 2000, contractDto.getPaxCost()));

            ListIterator<DailyActivityDto> activityDtoListIterator = contractDto.getDailyActivities().listIterator();
            while (activityDtoListIterator.hasNext()) {
                dailyactivities_checkcombobox.getCheckModel().check(activityDtoListIterator.next());
            }
            ListIterator<CompanyServiceDto> companyServiceDtoListIterator = contractDto.getCompaniesService().listIterator();
            while (companyServiceDtoListIterator.hasNext()) {
                servicecompanies_checkcombobox.getCheckModel().check(companyServiceDtoListIterator.next());
            }
            ListIterator<ServiceTypeDto> serviceTypeDtoListIterator = contractDto.getServiceType().listIterator();
            while (serviceTypeDtoListIterator.hasNext()) {
                servicetypes_checkcombobox.getCheckModel().check(serviceTypeDtoListIterator.next());
            }
        }
    }
}
