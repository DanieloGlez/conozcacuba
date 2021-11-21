package ui.controller.modalsController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.nom.ContractTypeDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import service.ServicesLocator;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ContractServiceTypeModal implements Initializable {
    @FXML
    private JFXTextField tf_serviceTypeName;

    @FXML
    private JFXButton jfxbtn_insert;

    @FXML
    private JFXButton jfxbtn_cancel;

    @FXML
    void cancelIserntion(ActionEvent event) {

    }

    @FXML
    void insertType(ActionEvent event) {

        try {
            ServicesLocator.getContractTypeServices().insert(
                    new ContractTypeDto(0,tf_serviceTypeName.getText())
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }


}
