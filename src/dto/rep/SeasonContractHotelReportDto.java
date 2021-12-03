package dto.rep;

import dto.ContractHotelDto;
import dto.SeasonDto;
import dto.VehicleDto;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.LinkedList;
import java.util.List;

public class SeasonContractHotelReportDto {
    private String hotelName;
    private String startDate;
    private String finishDate;
    private String conciliationDate;
    private JRBeanCollectionDataSource seasonDataSource;
    private List<SeasonReportDto> seasons;

    public SeasonContractHotelReportDto(ContractHotelDto contractHotelDto) {
        hotelName = contractHotelDto.getHotel().getName();
        startDate = contractHotelDto.getStartDate().toString();
        finishDate = contractHotelDto.getFinishDate().toString();
        conciliationDate = contractHotelDto.getConciliationDate().toString();
        seasons = contractHotelDto.getSeasonsReport((LinkedList<SeasonDto>) contractHotelDto.getSeasons());
    }

    //Getter and Setter
    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

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

    public String getConciliationDate() {
        return conciliationDate;
    }

    public void setConciliationDate(String conciliationDate) {
        this.conciliationDate = conciliationDate;
    }

    public JRBeanCollectionDataSource getSeasonDataSource() {
        return seasonDataSource;
    }

    public void setSeasonDataSource(JRBeanCollectionDataSource seasonDataSource) {
        this.seasonDataSource = seasonDataSource;
    }

    public JRBeanCollectionDataSource getSeasonsDataSource() {

        return new JRBeanCollectionDataSource(seasons, false);
    }
}
