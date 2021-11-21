package ui.controller.modalsController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.nom.CompanyServiceDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import service.ServicesLocator;
import service.nom.CompanyServiceServices;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CompanyServiceModal implements Initializable {
    @FXML
    private JFXTextField tf_companyService;

    @FXML
    private JFXButton jfxbtn_insert;

    @FXML
    private JFXButton jfxbtn_cancel;

    @FXML
    void cancelIserntion(ActionEvent event) {

    }

    @FXML
    void insertCompanyService(ActionEvent event) {
        try {
            ServicesLocator.getCompanyServiceServices().insert(
                    new CompanyServiceDto(0,tf_companyService.getText())
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
