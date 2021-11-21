package dto;

import dto.nom.ContractTypeDto;

import java.util.Date;

public class ContractDto {
    protected int id;
    protected ContractTypeDto contractTypeDto;
    protected Date startDate;
    protected Date finishDate;
    protected Date conciliationDate;
    protected String description;

    // Constructors
    public ContractDto(int id, ContractTypeDto contractTypeDto,Date startDate, Date finishDate, Date conciliationDate, String description) {
        this.id = id;
        this.contractTypeDto = contractTypeDto;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.conciliationDate = conciliationDate;
        this.description = description;
    }

    public ContractDto(){};

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public ContractTypeDto getContractTypeDto() {
        return contractTypeDto;
    }

    public void setContractTypeDto(ContractTypeDto contractTypeDto) {
        this.contractTypeDto = contractTypeDto;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Date getConciliationDate() {
        return conciliationDate;
    }

    public void setConciliationDate(Date conciliationDate) {
        this.conciliationDate = conciliationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
