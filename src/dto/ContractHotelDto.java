package dto;

import dto.nom.ContractTypeDto;

import java.sql.Date;
import java.util.List;

public class ContractHotelDto extends ContractDto {
    private List<SeasonDto> seasons;
    private HotelDto hotel;

    // Constructors
    public ContractHotelDto(int id, java.sql.Date startDate, java.sql.Date finishDate, java.sql.Date conciliationDate, String description, ContractTypeDto contractTypeDto, List<SeasonDto> seasons,  HotelDto hotel) {
        super(id, contractTypeDto, startDate, finishDate, conciliationDate, description);
        this.hotel = hotel;
        this.seasons = seasons;
    }

    // Getters & Setters
    public HotelDto getHotel() {
        return hotel;
    }

    public void setHotel(HotelDto hotel) {
        this.hotel = hotel;
    }

    public List<SeasonDto> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<SeasonDto> seasons) {
        this.seasons = seasons;
    }
}
