package dto;

import java.util.Date;
import java.util.List;

public class SeasonDto {
    private String id;
    private String name;
    private Date startDate;
    private Date finishDate;
    private String description;

    //References
    private List<RoomPlanDto> roomPlans;

    // Constructors
    public SeasonDto(String id, String name, Date startDate, Date finishDate, String description, List<RoomPlanDto> roomPlans) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.description = description;
        this.roomPlans = roomPlans;
    }

    // Getters & Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<RoomPlanDto> getRoomPlans() {
        return roomPlans;
    }

    public void setRoomPlans(List<RoomPlanDto> roomPlans) {
        this.roomPlans = roomPlans;
    }
}
