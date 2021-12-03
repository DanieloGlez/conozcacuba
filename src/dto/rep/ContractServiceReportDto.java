package dto.rep;

import dto.ContractServiceDto;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ContractServiceReportDto {

    private String startDate;
    private String finishDate;
    private String conciliationDate;
    private String description;
    private String paxCost;
    private String province;
    private JRBeanCollectionDataSource serviceTypeDataSource;
    private String serviceTypes;

    //Constructors
    public ContractServiceReportDto(ContractServiceDto contractServiceDto) {
        startDate = contractServiceDto.getStartDate().toString();
        finishDate = contractServiceDto.getFinishDate().toString();
        conciliationDate = contractServiceDto.getConciliationDate().toString();
        description = contractServiceDto.getDescription();
        paxCost = String.valueOf(contractServiceDto.getPaxCost());
        province = contractServiceDto.getIdProvince().getName();

        serviceTypes = contractServiceDto
                .getServiceType()
                .toString()
                .replace("[", "")
                .replace("]", "")
        ;
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
}
