package dto;

import dto.nom.ContractTypeDto;
import dto.nom.DailyActivityDto;

import java.util.Date;
import java.util.List;

public class ContractServiceDto extends ContractDto {
    private int idProvince;
    private float paxCost;

    // References
    private List<DailyActivityDto> dailyActivities;

    // Constructors
    public ContractServiceDto(int id, ContractTypeDto contractTypeDto, java.sql.Date startDate, java.sql.Date finishDate, java.sql.Date conciliationDate, String description, int idProvince, float paxCost, List<DailyActivityDto> dailyActivities) {
        super(id, contractTypeDto, startDate, finishDate, conciliationDate, description);
        this.idProvince = idProvince;
        this.paxCost = paxCost;
        this.dailyActivities = dailyActivities;
    }

    // Getters & Setters

    public int getIdProvince() {
        return idProvince;
    }

    public void setIdProvince(int idProvince) {
        this.idProvince = idProvince;
    }

    public float getPaxCost() {
        return paxCost;
    }

    public void setPaxCost(float paxCost) {
        this.paxCost = paxCost;
    }

    public List<DailyActivityDto> getDailyActivities() {
        return dailyActivities;
    }

    public void setDailyActivities(List<DailyActivityDto> dailyActivities) {
        this.dailyActivities = dailyActivities;
    }
}
