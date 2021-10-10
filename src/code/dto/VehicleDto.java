package code.dto;

import code.dto.nomenclators.VehicleBrandDto;

import java.time.Year;

public class VehicleDto {
    private String id;
    private VehicleBrandDto brand;
    private int capacityWithoutBaggage;
    private int capacityWithBaggage;
    private int capacityTotal;
    private Year productionYear;

    // Constructors
    public VehicleDto(String id, VehicleBrandDto brand, int capacityWithoutBaggage, int capacityWithBaggage, int capacityTotal, Year productionYear) {
        this.id = id;
        this.brand = brand;
        this.capacityWithoutBaggage = capacityWithoutBaggage;
        this.capacityWithBaggage = capacityWithBaggage;
        this.capacityTotal = capacityTotal;
        this.productionYear = productionYear;
    }

    // Getters & Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public VehicleBrandDto getBrand() {
        return brand;
    }

    public void setBrand(VehicleBrandDto brand) {
        this.brand = brand;
    }

    public int getCapacityWithoutBaggage() {
        return capacityWithoutBaggage;
    }

    public void setCapacityWithoutBaggage(int capacityWithoutBaggage) {
        this.capacityWithoutBaggage = capacityWithoutBaggage;
    }

    public int getCapacityWithBaggage() {
        return capacityWithBaggage;
    }

    public void setCapacityWithBaggage(int capacityWithBaggage) {
        this.capacityWithBaggage = capacityWithBaggage;
    }

    public int getCapacityTotal() {
        return capacityTotal;
    }

    public void setCapacityTotal(int capacityTotal) {
        this.capacityTotal = capacityTotal;
    }

    public Year getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Year productionYear) {
        this.productionYear = productionYear;
    }
}
