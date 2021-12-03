package dto.rep;

import dto.*;
import dto.nom.DailyActivityDto;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class TouristicPackageReportDto {

    private String promotionalName;
    private String daysAmount;
    private String nightsAmount;
    private String paxAmount;
    private String hotelName;
    private String costHotel;
    private String costTransport;
    private String costTransportHA;
    private String price;
    private String activities;
    List<ContractDto> contractsR;

    //Constructors
    public TouristicPackageReportDto (TouristicPackageDto touristicPackageDto){

        promotionalName = touristicPackageDto.getPromotionalName();
        daysAmount = String.valueOf(touristicPackageDto.getDaysAmount());
        nightsAmount = String.valueOf(touristicPackageDto.getNightsAmount());
        paxAmount = String.valueOf(touristicPackageDto.getPaxAmount());
        costHotel = String.valueOf(touristicPackageDto.getCostHotel());
        costTransport = String.valueOf(touristicPackageDto.getCostTransport());
        price = String.valueOf(touristicPackageDto.getPrice());

        contractsR = touristicPackageDto.getContracts();
        ListIterator<ContractDto> listIterator = contractsR.listIterator();

        List<String> hotelesR = new LinkedList<>();
        List<String> activitiesR = new LinkedList<>();

        while (listIterator.hasNext()) {
            if(listIterator.next() instanceof ContractHotelDto) {
                hotelesR.add(((ContractHotelDto) listIterator.next()).getHotel().getName());
            }else if(listIterator.next() instanceof ContractServiceDto){
                List<DailyActivityDto> dailyA = ((ContractServiceDto) listIterator.next()).getDailyActivities();
                ListIterator<DailyActivityDto> listDaily = dailyA.listIterator();

                while (listDaily.hasNext()){
                    activitiesR.add(listDaily.next().getName());
                }
            }
        }

        hotelName = hotelesR
                .toString()
                .replace("[", "")
                .replace("]", "")
        ;

        activities = activitiesR
                .toString()
                .replace("[", "")
                .replace("]", "")
        ;

    }

    //Getter and Setter
    public String getPromotionalName() {
        return promotionalName;
    }

    public void setPromotionalName(String promotionalName) {
        this.promotionalName = promotionalName;
    }

    public String getDaysAmount() {
        return daysAmount;
    }

    public void setDaysAmount(String daysAmount) {
        this.daysAmount = daysAmount;
    }

    public String getNightsAmount() {
        return nightsAmount;
    }

    public void setNightsAmount(String nightsAmount) {
        this.nightsAmount = nightsAmount;
    }

    public String getPaxAmount() {
        return paxAmount;
    }

    public void setPaxAmount(String paxAmount) {
        this.paxAmount = paxAmount;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getCostHotel() {
        return costHotel;
    }

    public void setCostHotel(String costHotel) {
        this.costHotel = costHotel;
    }

    public String getCostTransport() {
        return costTransport;
    }

    public void setCostTransport(String costTransport) {
        this.costTransport = costTransport;
    }

    public String getCostTransportHA() {
        return costTransportHA;
    }

    public void setCostTransportHA(String costTransportHA) {
        this.costTransportHA = costTransportHA;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }
}
