package dto;

import dto.nom.CompanyTransportDto;
import dto.nom.ContractTypeDto;

import java.util.Date;
import java.util.List;

public class ContractTransportDto extends ContractDto {
    private CompanyTransportDto transportCompany;

    // Constructors
    public ContractTransportDto(int id, java.sql.Date startDate, java.sql.Date finishDate, java.sql.Date conciliationDate, String description, ContractTypeDto contractTypeDto, CompanyTransportDto transportCompany) {
        super(id, contractTypeDto,startDate, finishDate, conciliationDate, description);
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
