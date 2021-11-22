package dto;

import dto.nom.CompanyTransportDto;
import dto.nom.ContractTypeDto;

import java.sql.Date;
import java.util.List;

public class ContractTransportDto extends ContractDto {
    private CompanyTransportDto transportCompany;
    private List<VehicleDto> vehicles;

    // Constructors
    public ContractTransportDto(int id, ContractTypeDto contractTypeDto, Date startDate, Date finishDate, Date conciliationDate, String description, CompanyTransportDto transportCompany, List<VehicleDto> vehicles) {
        super(id, contractTypeDto, startDate, finishDate, conciliationDate, description);
        this.transportCompany = transportCompany;
    }

    // Getters & Setters
    public CompanyTransportDto getTransportCompany() {
        return transportCompany;
    }

    public void setTransportCompany(CompanyTransportDto transportCompany) {
        this.transportCompany = transportCompany;
    }

    public List<VehicleDto> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleDto> vehicles) {
        this.vehicles = vehicles;
    }
}
