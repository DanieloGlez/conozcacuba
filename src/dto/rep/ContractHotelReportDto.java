package dto.rep;

import dto.ContractHotelDto;

public class ContractHotelReportDto {
    private String hotelName;
    private String hotelFranchise;
    private String province;
    private String address;
    private String hotelCategory;
    private String startDate;
    private String finishDate;
    private String conciliationDate;
    private String contractDescription;

    // Constructors
    public ContractHotelReportDto(ContractHotelDto contractHotelDto) {
        hotelName = contractHotelDto.getHotel().getName();
        hotelFranchise = contractHotelDto.getHotel().getHotelFranchise().getName();
        province = contractHotelDto.getHotel().getProvince().getName();
        address = contractHotelDto.getHotel().getAddress();
        hotelCategory = contractHotelDto.getHotel().getCategory();
        startDate = contractHotelDto.getStartDate().toString();
        finishDate = contractHotelDto.getFinishDate().toString();
        conciliationDate = contractHotelDto.getConciliationDate().toString();
        contractDescription = contractHotelDto.getDescription();
    }

    // Getters & Setters
    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelFranchise() {
        return hotelFranchise;
    }

    public void setHotelFranchise(String hotelFranchise) {
        this.hotelFranchise = hotelFranchise;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHotelCategory() {
        return hotelCategory;
    }

    public void setHotelCategory(String hotelCategory) {
        this.hotelCategory = hotelCategory;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public String getConciliationDate() {
        return conciliationDate;
    }

    public void setConciliationDate(String conciliationDate) {
        this.conciliationDate = conciliationDate;
    }

    public String getContractDescription() {
        return contractDescription;
    }

    public void setContractDescription(String contractDescription) {
        this.contractDescription = contractDescription;
    }

    @Override
    public String toString() {
        return "ContractHotelReportDto{" +
                "hotelName='" + hotelName + '\'' +
                ", hotelFranchise='" + hotelFranchise + '\'' +
                ", province='" + province + '\'' +
                ", address='" + address + '\'' +
                ", hotelCategory='" + hotelCategory + '\'' +
                ", startDate='" + startDate + '\'' +
                ", finishDate='" + finishDate + '\'' +
                ", conciliationDate='" + conciliationDate + '\'' +
                ", contractDescription='" + contractDescription + '\'' +
                '}';
    }
}
