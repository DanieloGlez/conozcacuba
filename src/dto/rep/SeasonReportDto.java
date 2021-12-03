package dto.rep;

import dto.SeasonDto;

public class SeasonReportDto {

    private String startDate;
    private String finishDate;
    private String name;
    private String description;

    // Constructors
    public SeasonReportDto(SeasonDto seasonDto) {
        startDate = seasonDto.getStartDate().toString();
        finishDate = seasonDto.getFinishDate().toString();
        name = seasonDto.getName();
        description = seasonDto.getDescription();

    }

    //Getter and Setter
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
