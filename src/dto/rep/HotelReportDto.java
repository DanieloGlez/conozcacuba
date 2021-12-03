package dto.rep;

import dto.HotelDto;
import dto.nom.*;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class HotelReportDto {

    private String name;
    private String category;
    private String telephoneNumber;
    private String fax;
    private String email;
    private String distToCity;
    private String distToAirport;
    private String roomsAmount;
    private String floorsAmount;
    private String hotelFranchise;
    private String province;
    private String localization;
    private String commercialModalities;

    // Constructors
    public HotelReportDto(HotelDto hotelDto) {
        name = hotelDto.getName();
        category = hotelDto.getCategory();
        telephoneNumber = hotelDto.getTelephoneNumber();
        fax = hotelDto.getFax();
        email = hotelDto.getEmail();
        distToCity = String.valueOf(hotelDto.getDistToCity());
        distToAirport = String.valueOf(hotelDto.getDistToAirport());
        roomsAmount = String.valueOf(hotelDto.getRoomsAmount());
        floorsAmount = String.valueOf(hotelDto.getFloorsAmount());
        hotelFranchise = hotelDto.getHotelFranchise().getName();
        province = hotelDto.getProvince().getName();
        localization = hotelDto.getLocalization().getName();

        commercialModalities = hotelDto
                .getCommercialModalities()
                .toString()
                .replace("[", "")
                .replace("]", "")
        ;
    }



    // Getters & Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDistToCity() {
        return distToCity;
    }

    public void setDistToCity(String distToCity) {
        this.distToCity = distToCity;
    }

    public String getDistToAirport() {
        return distToAirport;
    }

    public void setDistToAirport(String distToAirport) {
        this.distToAirport = distToAirport;
    }

    public String getRoomsAmount() {
        return roomsAmount;
    }

    public void setRoomsAmount(String roomsAmount) {
        this.roomsAmount = roomsAmount;
    }

    public String getFloorsAmount() {
        return floorsAmount;
    }

    public void setFloorsAmount(String floorsAmount) {
        this.floorsAmount = floorsAmount;
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

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public String getCommercialModalities() {
        return commercialModalities;
    }

    public void setCommercialModalities(String commercialModalities) {
        this.commercialModalities = commercialModalities;
    }
}
