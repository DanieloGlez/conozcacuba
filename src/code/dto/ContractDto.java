package code.dto;

import java.util.Date;

public abstract class ContractDto {
    protected String id;
    protected Date startDate;
    protected Date finishDate;
    protected Date conciliationDate;
    protected String description;

    // Constructors
    public ContractDto(String id, Date startDate, Date finishDate, Date conciliationDate, String description) {
        this.id = id;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.conciliationDate = conciliationDate;
        this.description = description;
    }

    // Getters & Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
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
