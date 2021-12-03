package dto.rep;

import dto.TouristicPackageDto;

public class TouristicPackageSalesReportDto {

    private String promotionalName;
    private String paxAmount;
    private String costTotal;
    private String price;

    //Constructors
    public TouristicPackageSalesReportDto(TouristicPackageDto touristicPackageDto){

        promotionalName = touristicPackageDto.getPromotionalName();
        paxAmount = String.valueOf(touristicPackageDto.getPaxAmount());
        costTotal = String.valueOf(touristicPackageDto.getCostTotal());
        price = String.valueOf(touristicPackageDto.getPrice());

    }

    //Getter and Setter
    public String getPromotionalName() {
        return promotionalName;
    }

    public void setPromotionalName(String promotionalName) {
        this.promotionalName = promotionalName;
    }

    public String getPaxAmount() {
        return paxAmount;
    }

    public void setPaxAmount(String paxAmount) {
        this.paxAmount = paxAmount;
    }

    public String getCostTotal() {
        return costTotal;
    }

    public void setCostTotal(String costTotal) {
        this.costTotal = costTotal;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
