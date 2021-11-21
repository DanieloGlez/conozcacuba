package dto;

import java.util.Date;

public class DailyActivityDto {
    private int id;
    private String name;

    // Constructors
    public DailyActivityDto(int id, Date date, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
