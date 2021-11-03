package dto;

import java.util.List;

public class VehicleHiredDto {
    private VehicleDto vehicle;
    private List<ModalityTransportDto> transportModalities;

    // Constructors
    public VehicleHiredDto(VehicleDto vehicle, List<ModalityTransportDto> transportModalities) {
        this.vehicle = vehicle;
        this.transportModalities = transportModalities;
    }

    // Getter & Setter
    public VehicleDto getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleDto vehicle) {
        this.vehicle = vehicle;
    }

    public List<ModalityTransportDto> getTransportModalities() {
        return transportModalities;
    }

    public void setTransportModalities(List<ModalityTransportDto> transportModalities) {
        this.transportModalities = transportModalities;
    }
}
