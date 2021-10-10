package code.dto;

import java.util.Date;

public class ContractServiceDto extends ContractDto {
    private String idServiceType;
    private String idProvince;
    private float paxCost;

    // Constructors
    public ContractServiceDto(String id, Date startDate, Date finishDate, Date conciliationDate, String description, String idServiceType, String idProvince, float paxCost) {
        super(id, startDate, finishDate, conciliationDate, description);
        this.idServiceType = idServiceType;
        this.idProvince = idProvince;
        this.paxCost = paxCost;
    }

    // Getters & Setters
    public String getIdServiceType() {
        return idServiceType;
    }

    public void setIdServiceType(String idServiceType) {
        this.idServiceType = idServiceType;
    }

    public String getIdProvince() {
        return idProvince;
    }

    public void setIdProvince(String idProvince) {
        this.idProvince = idProvince;
    }

    public float getPaxCost() {
        return paxCost;
    }

    public void setPaxCost(float paxCost) {
        this.paxCost = paxCost;
    }
}
