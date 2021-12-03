package ui.controller.report;

import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import service.ServicesLocator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ReportMenu implements Initializable {
    private JRViewer jrViewer;

    @FXML
    private SwingNode jasperreportcontainer_swingnode;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        JasperReport jasperReport = null;
        try {
            jasperReport = JasperCompileManager.compileReport(new FileInputStream("./src/ui/report/contract_hotel.jrxml"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), new JRBeanCollectionDataSource(ServicesLocator.getContractHotelReportServices().loadAll()));

            jrViewer = new JRViewer(jasperPrint);

            jasperreportcontainer_swingnode.setContent(jrViewer);
        } catch (JRException | FileNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
