package dto.rep;

import dto.ContractHotelDto;
import dto.ContractServiceDto;
import dto.SeasonDto;
import dto.VehicleDto;
import dto.nom.ServiceTypeDto;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class ContractServiceReportDto {

    private String startDate;
    private String finishDate;
    private String conciliationDate;
    private String description;
    private String paxCost;
    private String province;
    private JRBeanCollectionDataSource serviceTypeDataSource;
    private List<String> serviceTypes;

    //Constructors
    public ContractServiceReportDto(ContractServiceDto contractServiceDto) {
        startDate = contractServiceDto.getStartDate().toString();
        finishDate = contractServiceDto.getFinishDate().toString();
        conciliationDate = contractServiceDto.getConciliationDate().toString();
        description = contractServiceDto.getDescription();
        paxCost = String.valueOf(contractServiceDto.getPaxCost());
        province = contractServiceDto.getIdProvince().getName();

        List<ServiceTypeDto> services = contractServiceDto.getServiceType();
        ListIterator<ServiceTypeDto> listIterator = services.listIterator();

        while (listIterator.hasNext()) {
            assert false;
            serviceTypes.add(listIterator.next().getName());
        }
    }

    //Getter and Setter


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPaxCost() {
        return paxCost;
    }

    public void setPaxCost(String paxCost) {
        this.paxCost = paxCost;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setServiceTypeDataSource(JRBeanCollectionDataSource serviceTypeDataSource) {
        this.serviceTypeDataSource = serviceTypeDataSource;
    }

    public JRBeanCollectionDataSource getServiceTypeDataSource() {

        return new JRBeanCollectionDataSource(serviceTypes, false);
    }

}
