package dto.rep;

import dto.ContractTransportDto;
import dto.VehicleDto;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class ContractTransportReportDto {

   private String companyName;
   private String startDate;
   private String finishDate;
   private String conciliationDate;
   private JRBeanCollectionDataSource vehiclesDataSource;
   private List<VehicleReportDto> vehicles;

   public ContractTransportReportDto(ContractTransportDto contractTransportDto) {
      companyName = contractTransportDto.getTransportCompany().getName();
      startDate = contractTransportDto.getStartDate().toString();
      finishDate = contractTransportDto.getFinishDate().toString();
      conciliationDate = contractTransportDto.getConciliationDate().toString();
      vehicles = contractTransportDto.getVehiclesReport((LinkedList<VehicleDto>) contractTransportDto.getVehicles());
   }

   //Getter and Setter

   public String getCompanyName() {
      return companyName;
   }

   public void setCompanyName(String companyName) {
      this.companyName = companyName;
   }

   public String getStartDate() {
      return startDate;
   }

   public void setStartDate(String startDate) {
      this.startDate = startDate;
   }

   public String getFinishDate() {
      return finishDate;
   }

   public void setFinishDate(String finishDate) {
      this.finishDate = finishDate;
   }

   public String getConciliationDate() {
      return conciliationDate;
   }

   public void setConciliationDate(String conciliationDate) {
      this.conciliationDate = conciliationDate;
   }

   public void setVehiclesDataSource(JRBeanCollectionDataSource vehiclesDataSource) {
      this.vehiclesDataSource = vehiclesDataSource;
   }

   public JRBeanCollectionDataSource getVehiclesDataSource() {

      return new JRBeanCollectionDataSource(vehicles, false);
   }



}
