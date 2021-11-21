package dto;

import dto.nom.CompanyTransportDto;

import java.util.Date;
import java.util.List;

public class ContractTransportDto extends ContractDto {
    private CompanyTransportDto transportCompany;

    // Constructors
    public ContractTransportDto(String id, String idContractType, Date startDate, Date finishDate, Date conciliationDate, String description, CompanyTransportDto transportCompany) {
        super(id, idContractType, startDate, finishDate, conciliationDate, description);
        this.transportCompany = transportCompany;
    }

    // Getters & Setters
    public CompanyTransportDto getTransportCompany() {
        return transportCompany;
    }

    public void setTransportCompany(CompanyTransportDto transportCompany) {
        this.transportCompany = transportCompany;
    }
}
