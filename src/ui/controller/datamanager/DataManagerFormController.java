package ui.controller.datamanager;

import com.jfoenix.controls.JFXButton;
import dto.Dto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;

public abstract class DataManagerFormController implements Initializable {
    protected Dto dto;

    @FXML
    protected JFXButton insert_jfxbutton;

    @FXML
    protected JFXButton update_jfxbutton;

    public abstract void insert(ActionEvent event) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, SQLException, InstantiationException, ParseException;
    public abstract void update(ActionEvent event) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, SQLException;
    public void setDto(Dto dto) {
        this.dto = dto;

        if(dto == null)
            update_jfxbutton.setDisable(true);
        else
            insert_jfxbutton.setDisable(true);
    }
}
