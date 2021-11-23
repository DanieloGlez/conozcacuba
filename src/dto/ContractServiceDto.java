package dto;

import dto.nom.ContractTypeDto;
import dto.nom.DailyActivityDto;
import dto.nom.ProvinceDto;

import java.sql.Date;
import java.util.List;

public class ContractServiceDto extends ContractDto {
    private float paxCost;
    private ProvinceDto idProvince;


    // References
    private List<DailyActivityDto> dailyActivities;

    // Constructors
    public ContractServiceDto(int id, java.sql.Date startDate, java.sql.Date finishDate, java.sql.Date conciliationDate, String description, ContractTypeDto contractTypeDto, float paxCost, ProvinceDto idProvince, List<DailyActivityDto> dailyActivities) {
        super(id, contractTypeDto, startDate, finishDate, conciliationDate, description);
        this.idProvince = idProvince;
        this.paxCost = paxCost;
        this.dailyActivities = dailyActivities;
    }

    // Getters & Setters
    public String toString(){
        return this.getContractTypeDto().getName();
    }

    public ProvinceDto getIdProvince() {
        return idProvince;
    }

    public void setIdProvince(ProvinceDto idProvince) {
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
