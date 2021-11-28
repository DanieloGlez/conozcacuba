package dto;

import dto.nom.ContractTypeDto;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class ContractHotelDto extends ContractDto {
    private List<SeasonDto> seasons;
    private HotelDto hotel;

    // Constructors
    public ContractHotelDto(int id, java.sql.Date startDate, java.sql.Date finishDate, java.sql.Date conciliationDate, String description, ContractTypeDto contractTypeDto, HotelDto hotel) {
        super(id, contractTypeDto, startDate, finishDate, conciliationDate, description);
        this.hotel = hotel;
        this.seasons = new LinkedList<>();
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
        ListIterator<SeasonDto> listIterator = seasons.listIterator();

        while (listIterator.hasNext()) {
            SeasonDto currentSeason = listIterator.next();
            this.seasons.add(currentSeason);
        }
    }

    @Override
    public String toString() {
        return this.getHotel().getName();
    }
}

