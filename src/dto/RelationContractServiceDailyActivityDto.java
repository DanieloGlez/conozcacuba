package dto;

import dto.nom.DailyActivityDto;

public class RelationContractServiceDailyActivityDto implements Dto{
    private float price;
    private int idContractService;
    private DailyActivityDto dailyActivityDto;

    public RelationContractServiceDailyActivityDto(float cost, int idContractService, DailyActivityDto dailyActivityDto) {
        setPrice(cost);
        this.idContractService = idContractService;
        this.dailyActivityDto = dailyActivityDto;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float cost) {
        this.price = cost * (float) 1.1;
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

    @Override
    public int getId() {
        return this.dailyActivityDto.getId();
    }
}
