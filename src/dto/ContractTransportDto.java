package dto;

import dto.nom.CompanyTransportDto;
import dto.nom.ContractTypeDto;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class ContractTransportDto extends ContractDto {
    private CompanyTransportDto transportCompany;
    private List<VehicleDto> vehicles;

    // Constructors
    public ContractTransportDto(int id, ContractTypeDto contractTypeDto, Date startDate, Date finishDate, Date conciliationDate, String description, CompanyTransportDto transportCompany) {
        super(id, contractTypeDto, startDate, finishDate, conciliationDate, description);
        this.transportCompany = transportCompany;
        this.vehicles = new LinkedList<>();
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
        ListIterator<VehicleDto> listIterator = vehicles.listIterator();

        while (listIterator.hasNext()) {
            VehicleDto currentVehicleDto = listIterator.next();
            this.vehicles.add(currentVehicleDto);
        }
    }

    @Override
    public String toString() {
        return this.getTransportCompany().getName();
    }
}
