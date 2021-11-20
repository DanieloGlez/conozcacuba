package dto;

public class VehicleBrandDto {
    private String idBrand;
    private String nameBrand;

    public VehicleBrandDto(String idBrand, String nameBrand) {
        this.idBrand = idBrand;
        this.nameBrand = nameBrand;
    }

    public String getIdBrand() {
        return idBrand;
    }

    public String getNameBrand() {
        return nameBrand;
    }
}
