package ui.controller.modalsController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.nom.CompanyTransportDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import service.ServicesLocator;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CompanyTransportModal implements Initializable {

    @FXML
    private JFXTextField tf_companyTransportName;

    @FXML
    private JFXButton jfxbtn_insert;

    @FXML
    private JFXButton jfxbtn_cancel;

    @FXML
    void cancelIserntion(ActionEvent event) {

    }

    @FXML
    void insertCompanyTransport(ActionEvent event) {
        try {
            ServicesLocator.getCompanyTransportServices().insert(
                    new CompanyTransportDto(0,tf_companyTransportName.getText())
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
