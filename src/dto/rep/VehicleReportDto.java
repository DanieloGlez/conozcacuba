package dto.rep;

import dto.VehicleDto;

public class VehicleReportDto {

    private String registration;
    private String brand;
    private String capacityWithoutBaggage;
    private String capacityWithBaggage;
    private String totalCapacity;
    private String productionDate;

    // Constructors
    public VehicleReportDto(VehicleDto vehicleDto) {
        registration = vehicleDto.getRegistration();
        brand = vehicleDto.getBrand().getName();
        capacityWithoutBaggage = String.valueOf(vehicleDto.getCapacityWithoutBaggage());
        capacityWithBaggage = String.valueOf(vehicleDto.getCapacityWithBaggage());
        totalCapacity = String.valueOf(vehicleDto.getCapacityTotal());
        productionDate = vehicleDto.getProductionDate().toString();
    }

    //Getter and Setter

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCapacityWithoutBaggage() {
        return capacityWithoutBaggage;
    }

    public void setCapacityWithoutBaggage(String capacityWithoutBaggage) {
        this.capacityWithoutBaggage = capacityWithoutBaggage;
    }

    public String getCapacityWithBaggage() {
        return capacityWithBaggage;
    }

    public void setCapacityWithBaggage(String capacityWithBaggage) {
        this.capacityWithBaggage = capacityWithBaggage;
    }

    public String getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(String totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }
}
