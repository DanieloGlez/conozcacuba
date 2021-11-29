package ui.controller.datamanager;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RegexValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import dto.Dto;
import dto.nom.NomenclatorDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import service.Services;
import service.ServicesLocator;
import util.ConstantUtils;
import util.Validator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class NomenclatorForm extends DataManagerFormController {
    private String nomenclatorClassName;
    private Validator p= new Validator();
    @FXML
    private JFXTextField nomenclator_jfxtextfield;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RegexValidator regexValidator= new RegexValidator("This field is not a text");
        regexValidator.setRegexPattern("[a-zA-Z].*"+ "");
        RequiredFieldValidator requiredFieldValidator=new RequiredFieldValidator("This field is required");

        nomenclator_jfxtextfield.getValidators().add(regexValidator);
        nomenclator_jfxtextfield.getValidators().add(requiredFieldValidator);
        nomenclator_jfxtextfield.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) nomenclator_jfxtextfield.validate();
        });

    }

    public void initializeNomenclatorClassName(String nomenclator) {
        nomenclatorClassName = nomenclator;
        nomenclator_jfxtextfield.setPromptText(nomenclatorClassName);
    }

    @Override
    public void insert(ActionEvent event) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, SQLException, InstantiationException {
        if (p.validateText(nomenclator_jfxtextfield)) {
            Class dtoClass = ConstantUtils.getTableNames().get(nomenclatorClassName);
            Constructor<?> constructor = dtoClass.getConstructor(int.class, String.class);

            Services services = (Services) ServicesLocator.class.getMethod("get" + nomenclatorClassName + "Services").invoke(null);

            services.insert(constructor.newInstance(0, nomenclator_jfxtextfield.getText()));

            ((Stage) nomenclator_jfxtextfield.getScene().getWindow()).close();
        }
    }

    @Override
    public void update(ActionEvent event) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, SQLException {
        if (p.validateText(nomenclator_jfxtextfield)) {

            ((NomenclatorDto) dto).setName(nomenclator_jfxtextfield.getText());

            Services services = (Services) ServicesLocator.class.getMethod("get" + nomenclatorClassName + "Services").invoke(null);
            services.update(dto);

            ((Stage) nomenclator_jfxtextfield.getScene().getWindow()).close();
        }
    }

    @Override
    public void setDto(Dto dto) {
        super.setDto(dto);

        if (dto != null)
            nomenclator_jfxtextfield.setText(((NomenclatorDto) dto).getName());
    }
}
