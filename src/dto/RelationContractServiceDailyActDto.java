package dto;

import dto.nom.DailyActivityDto;

public class RelationContractServiceDailyActDto{
    private float price;
    private int idContractService;
    private DailyActivityDto dailyActivityDto;

    public RelationContractServiceDailyActDto(float cost, int idContractService, DailyActivityDto dailyActivityDto) {
        setPrice(cost);
        this.idContractService = idContractService;
        this.dailyActivityDto = dailyActivityDto;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float cost) {
        this.price *= (float) 1.1;
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
