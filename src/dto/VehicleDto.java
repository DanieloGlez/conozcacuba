package dto;

import dto.nom.VehicleBrandDto;

import java.time.LocalDate;

public class VehicleDto {
    private String id;
    private VehicleBrandDto brand;
    private int capacityWithoutBaggage;
    private int capacityWithBaggage;
    private int capacityTotal;
    private LocalDate productionDate;

    // Constructors
    public VehicleDto(String id, VehicleBrandDto brand, int capacityWithoutBaggage, int capacityWithBaggage, LocalDate productionDate) {
        this.id = id;
        this.brand = brand;
        this.capacityWithoutBaggage = capacityWithoutBaggage;
        this.capacityWithBaggage = capacityWithBaggage;
        this.capacityTotal = capacityWithBaggage+capacityWithoutBaggage;
        this.productionDate = productionDate;
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
        return getCapacityWithBaggage()+getCapacityWithoutBaggage();
    }

    public void setCapacityTotal(int capacityTotal) {
        this.capacityTotal = capacityTotal;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    public java.sql.Date toDate(){
        java.sql.Date dateBD=java.sql.Date.valueOf(this.getProductionDate());
        return dateBD;
    }
}
