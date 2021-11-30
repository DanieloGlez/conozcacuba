package dto;

import dto.nom.DailyActivityDto;

public class RelationContractServiceDailyActDto{
    private float cost;
    private ContractServiceDto contractServiceDto;
    private DailyActivityDto dailyActivityDto;

    public RelationContractServiceDailyActDto(float cost, ContractServiceDto contractServiceDto, DailyActivityDto dailyActivityDto) {
        this.cost = cost;
        this.contractServiceDto = contractServiceDto;
        this.dailyActivityDto = dailyActivityDto;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public ContractServiceDto getContractServiceDto() {
        return contractServiceDto;
    }

    public void setContractServiceDto(ContractServiceDto contractServiceDto) {
        this.contractServiceDto = contractServiceDto;
    }

    public DailyActivityDto getDailyActivityDto() {
        return dailyActivityDto;
    }

    public void setDailyActivityDto(DailyActivityDto dailyActivityDto) {
        this.dailyActivityDto = dailyActivityDto;
    }
}
