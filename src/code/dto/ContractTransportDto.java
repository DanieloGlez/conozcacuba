package code.dto;

import code.dto.nomenclators.CompanyTransportDto;

import java.util.Date;
import java.util.List;

public class ContractTransportDto extends ContractDto {
    private CompanyTransportDto transportCompany;
    private List<VehicleHiredDto> hiredVehicles;

    // Constructors
    public ContractTransportDto(String id, Date startDate, Date finishDate, Date conciliationDate, String description, CompanyTransportDto transportCompany, List<VehicleHiredDto> hiredVehicles) {
        super(id, startDate, finishDate, conciliationDate, description);
        this.transportCompany = transportCompany;
        this.hiredVehicles = hiredVehicles;
    }

    // Getters & Setters
    public CompanyTransportDto getTransportCompany() {
        return transportCompany;
    }

    public void setTransportCompany(CompanyTransportDto transportCompany) {
        this.transportCompany = transportCompany;
    }

    public List<VehicleHiredDto> getHiredVehicles() {
        return hiredVehicles;
    }

    public void setHiredVehicles(List<VehicleHiredDto> hiredVehicles) {
        this.hiredVehicles = hiredVehicles;
    }
}
