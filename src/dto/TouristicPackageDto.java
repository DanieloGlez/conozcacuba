package dto;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class TouristicPackageDto implements Dto{
    private int id;
    private String promotionalName;
    private int daysAmount;
    private int nightsAmount;
    private int paxAmount;
    private float costHotel;
    private float costTransport;
    private float costTransportHA;
    private float costTotal;
    private float price;

    // References
    private List<ContractDto> contracts;

    // Constructors
    public TouristicPackageDto(int id, String promotionalName, int daysAmount, int nightsAmount, int paxAmount, float costHotel, float costTransport, float costTransportHA, float costTotal, float price) {
        this.id = id;
        this.promotionalName = promotionalName;
        this.daysAmount = daysAmount;
        this.nightsAmount = nightsAmount;
        this.paxAmount = paxAmount;
        this.costHotel = costHotel;
        this.costTransport = costTransport;
        this.costTransportHA = costTransportHA;
        this.costTotal = costTotal;
        this.price = price;
        this.contracts = new LinkedList<>();
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPromotionalName() {
        return promotionalName;
    }

    public void setPromotionalName(String promotionalName) {
        this.promotionalName = promotionalName;
    }

    public int getDaysAmount() {
        return daysAmount;
    }

    public void setDaysAmount(int daysAmount) {
        this.daysAmount = daysAmount;
    }

    public int getNightsAmount() {
        return nightsAmount;
    }

    public void setNightsAmount(int nightsAmount) {
        this.nightsAmount = nightsAmount;
    }

    public int getPaxAmount() {
        return paxAmount;
    }

    public void setPaxAmount(int paxAmount) {
        this.paxAmount = paxAmount;
    }

    public float getCostHotel() {
        return costHotel;
    }

    public void setCostHotel(float costHotel) {
        this.costHotel = costHotel;
    }

    public float getCostTransport() {
        return costTransport;
    }

    public void setCostTransport(float costTransport) {
        this.costTransport = costTransport;
    }

    public float getCostTransportHA() {
        return costTransportHA;
    }

    public void setCostTransportHA(float costTransportHA) {
        this.costTransportHA = costTransportHA;
    }

    public float getCostTotal() {
        return costTotal;
    }

    public void setCostTotal(float costTotal) {
        this.costTotal = costTotal;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<ContractDto> getContracts() {
        return contracts;
    }

    public void setContracts(List<ContractDto> contracts) {
        ListIterator<ContractDto> listIterator = contracts.listIterator();

        while (listIterator.hasNext()) {
            ContractDto currentContractDto = listIterator.next();
            this.contracts.add(currentContractDto);
        }
    }

    public String toString() {
        return this.getPromotionalName();
    }
}
