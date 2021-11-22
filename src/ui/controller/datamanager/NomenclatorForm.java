package ui.controller.datamanager;

import com.jfoenix.controls.JFXTextField;
import dto.Dto;
import dto.nom.NomenclatorDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import service.Services;
import service.ServicesLocator;
import util.ConstantUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class NomenclatorForm extends DataManagerFormController {
    private String nomenclatorClassName;

    @FXML
    private JFXTextField nomenclator_jfxtextfield;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initializeNomenclatorClassName(String nomenclator) {
        nomenclatorClassName = nomenclator;
        nomenclator_jfxtextfield.setPromptText(nomenclatorClassName);
    }

    @Override
    public void insert(ActionEvent event) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, SQLException, InstantiationException {
        Class dtoClass = ConstantUtils.getTableNames().get(nomenclatorClassName);
        Constructor<?> constructor = dtoClass.getConstructor(int.class, String.class);

        Services services = (Services) ServicesLocator.class.getMethod("get" + nomenclatorClassName + "Services").invoke(null);

        services.insert(constructor.newInstance(0, nomenclator_jfxtextfield.getText()));

        ((Stage) nomenclator_jfxtextfield.getScene().getWindow()).close();
    }

    @Override
    public void update(ActionEvent event) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, SQLException {
        ((NomenclatorDto) dto).setName(nomenclator_jfxtextfield.getText());

        Services services = (Services) ServicesLocator.class.getMethod("get" + nomenclatorClassName + "Services").invoke(null);
        services.update(dto);

        ((Stage) nomenclator_jfxtextfield.getScene().getWindow()).close();
    }

    @Override
    public void setDto(Dto dto) {
        super.setDto(dto);

        if (dto != null)
            nomenclator_jfxtextfield.setText(((NomenclatorDto) dto).getName());
    }
}
