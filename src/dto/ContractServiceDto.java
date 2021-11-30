package dto;

import dto.nom.*;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class ContractServiceDto extends ContractDto implements Dto {
    private float paxCost;
    private ProvinceDto idProvince;

    // References
    private List<DailyActivityDto> dailyActivities;
    private List<CompanyServiceDto> companiesService;
    private List<ServiceTypeDto> serviceType;

    // Constructors
    public ContractServiceDto(int id, java.sql.Date startDate, java.sql.Date finishDate, java.sql.Date conciliationDate, String description, ContractTypeDto contractTypeDto, float paxCost, ProvinceDto idProvince) {
        super(id, contractTypeDto, startDate, finishDate, conciliationDate, description);
        this.idProvince = idProvince;
        this.paxCost = paxCost;
        this.dailyActivities = new LinkedList<>();
        this.companiesService = new LinkedList<>();
        this.serviceType = new LinkedList<>();
    }

    public ContractServiceDto(int id, java.sql.Date startDate, java.sql.Date finishDate, java.sql.Date conciliationDate, String description, ContractTypeDto contractTypeDto, float paxCost, ProvinceDto idProvince, List<DailyActivityDto> dailyActivities, List<CompanyServiceDto> companiesService, List<ServiceTypeDto> serviceType) {
        super(id, contractTypeDto, startDate, finishDate, conciliationDate, description);
        this.idProvince = idProvince;
        this.paxCost = paxCost;
        this.dailyActivities = dailyActivities;
        this.companiesService = companiesService;
        this.serviceType = serviceType;
    }

    // Getters & Setters
    @Override
    public String toString() {
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
        ListIterator<DailyActivityDto> listIterator = dailyActivities.listIterator();

        while (listIterator.hasNext()) {
            DailyActivityDto currentDailyActivityDto = listIterator.next();
            this.dailyActivities.add(currentDailyActivityDto);
        }
    }

    public List<CompanyServiceDto> getCompaniesService() {
        return companiesService;
    }

    public void setCompaniesService(List<CompanyServiceDto> companiesService) {
        ListIterator<CompanyServiceDto> listIterator = companiesService.listIterator();

        while (listIterator.hasNext()) {
            CompanyServiceDto currentCompanyServiceDto = listIterator.next();
            this.companiesService.add(currentCompanyServiceDto);
        }
    }

    /*public void setCompaniesService(List<CompanyServiceDto> companiesService) {
        this.companiesService = companiesService;
    }*/

    public List<ServiceTypeDto> getServiceType() {
        return serviceType;
    }

    public void setServiceType(List<ServiceTypeDto> serviceType) {
        ListIterator<ServiceTypeDto> listIterator = serviceType.listIterator();

        while (listIterator.hasNext()) {
            ServiceTypeDto currentServiceTypeDto = listIterator.next();
            this.serviceType.add(currentServiceTypeDto);
        }
    }

   /* public void setServiceType(List<ServiceTypeDto> serviceType) {
        this.serviceType = serviceType;
    }
    */
}
