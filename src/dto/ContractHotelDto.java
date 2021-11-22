package dto;

import dto.nom.ContractTypeDto;

import java.sql.Date;
import java.util.List;

public class ContractHotelDto extends ContractDto {
    private HotelDto hotel;
    private List<SeasonDto> seasons;

    // Constructors
    public ContractHotelDto(int id, ContractTypeDto contractTypeDto, java.sql.Date startDate, java.sql.Date finishDate, java.sql.Date conciliationDate, String description, HotelDto hotel, List<SeasonDto> seasons) {
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
