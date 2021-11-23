package dto;

import dto.nom.ContractTypeDto;

import java.util.Date;

public class ContractDto {
    protected int id;
    protected ContractTypeDto contractTypeDto;
    protected java.sql.Date startDate;
    protected java.sql.Date finishDate;
    protected java.sql.Date conciliationDate;
    protected String description;

    // Constructors
    public ContractDto(int id, ContractTypeDto contractTypeDto,java.sql.Date startDate, java.sql.Date finishDate, java.sql.Date conciliationDate, String description) {
        this.id = id;
        this.contractTypeDto = contractTypeDto;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.conciliationDate = conciliationDate;
        this.description = description;
    }

    public ContractDto(){};

    public String toString(){
        return this.getContractTypeDto().getName();
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public java.sql.Date getStartDate() {
        return startDate;
    }

    public ContractTypeDto getContractTypeDto() {
        return contractTypeDto;
    }

    public void setContractTypeDto(ContractTypeDto contractTypeDto) {
        this.contractTypeDto = contractTypeDto;
    }

    public void setStartDate(java.sql.Date startDate) {
        this.startDate = startDate;
    }

    public java.sql.Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(java.sql.Date finishDate) {
        this.finishDate = finishDate;
    }

    public java.sql.Date getConciliationDate() {
        return conciliationDate;
    }

    public void setConciliationDate(java.sql.Date conciliationDate) {
        this.conciliationDate = conciliationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
