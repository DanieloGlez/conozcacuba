package code.dto;

import java.util.Date;

public class DailyActivityDto {
    private String id;
    private Date date;
    private String description;
    private float cost;

    // Constructors
    public DailyActivityDto(String id, Date date, String description, float cost) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.cost = cost;
    }

    // Getters & Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}
