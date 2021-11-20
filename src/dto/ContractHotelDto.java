package dto;

import java.util.Date;
import java.util.List;

public class ContractHotelDto extends ContractDto {
    private HotelDto hotel;
    private List<SeasonDto> seasons;

    // Constructors
    public ContractHotelDto(String id, Date startDate, Date finishDate, Date conciliationDate, String description, HotelDto hotel, List<SeasonDto> seasons) {
        super(id, startDate, finishDate, conciliationDate, description);
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