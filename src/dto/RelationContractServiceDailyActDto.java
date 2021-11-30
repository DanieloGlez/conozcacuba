package dto;

import dto.nom.DailyActivityDto;

public class RelationContractServiceDailyActDto{
    private float cost;
    private int idContractService;
    private DailyActivityDto dailyActivityDto;

    public RelationContractServiceDailyActDto(float cost, int idContractService, DailyActivityDto dailyActivityDto) {
        this.cost = cost;
        this.idContractService = idContractService;
        this.dailyActivityDto = dailyActivityDto;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getIdContractService() {
        return idContractService;
    }

    public void setIdContractService(int idContractService) {
        this.idContractService = idContractService;
    }

    public DailyActivityDto getDailyActivityDto() {
        return dailyActivityDto;
    }

    public void setDailyActivityDto(DailyActivityDto dailyActivityDto) {
        this.dailyActivityDto = dailyActivityDto;
    }
}
