package dto;

import dto.nom.VehicleBrandDto;

import java.time.LocalDate;

public class VehicleDto {
    private int id;
    private String chapa;
    private VehicleBrandDto brand;
    private int capacityWithoutBaggage;
    private int capacityWithBaggage;
    private int capacityTotal;
    private LocalDate productionDate;

    // Constructors
    public VehicleDto(int id, String chapa, VehicleBrandDto brand, int capacityWithoutBaggage, int capacityWithBaggage, LocalDate productionDate) {
        this.id = id;
        this.chapa = chapa;
        this.brand = brand;
        this.capacityWithoutBaggage = capacityWithoutBaggage;
        this.capacityWithBaggage = capacityWithBaggage;
        this.capacityTotal = capacityWithBaggage+capacityWithoutBaggage;
        this.productionDate = productionDate;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChapa() {
        return chapa;
    }

    public void setChapa(String chapa) {
        this.chapa = chapa;
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
