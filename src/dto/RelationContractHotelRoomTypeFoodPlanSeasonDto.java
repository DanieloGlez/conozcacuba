package dto;

public class RelationContractHotelRoomTypeFoodPlanSeasonDto implements Dto{
    private int idContractHotel;
    private int idFoodPlan;
    private int idRoomType;
    private int idSeason;
    private float price;

    public RelationContractHotelRoomTypeFoodPlanSeasonDto(int idContractHotel, int idFoodPlan, int idRoomType, int idSeason, float price) {
        this.idContractHotel = idContractHotel;
        this.idFoodPlan = idFoodPlan;
        this.idRoomType = idRoomType;
        this.idSeason = idSeason;
        this.price = price;
    }

    public int getIdContractHotel() {
        return idContractHotel;
    }

    public void setIdContractHotel(int idContractHotel) {
        this.idContractHotel = idContractHotel;
    }

    public int getIdFoodPlan() {
        return idFoodPlan;
    }

    public void setIdFoodPlan(int idFoodPlan) {
        this.idFoodPlan = idFoodPlan;
    }

    public int getIdRoomType() {
        return idRoomType;
    }

    public void setIdRoomType(int idRoomType) {
        this.idRoomType = idRoomType;
    }

    public int getIdSeason() {
        return idSeason;
    }

    public void setIdSeason(int idSeason) {
        this.idSeason = idSeason;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public int getId() {
        return this.idRoomType;
    }
}
