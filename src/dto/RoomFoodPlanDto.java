package dto;

import dto.nom.FoodPlanDto;
import dto.nom.RoomTypeDto;

public class RoomFoodPlanDto {
    private RoomTypeDto roomType;
    private FoodPlanDto foodPlan;
    private float price;

    // Constructors
    public RoomFoodPlanDto(RoomTypeDto roomType, FoodPlanDto foodPlan, float price) {
        this.roomType = roomType;
        this.foodPlan = foodPlan;
        this.price = price;
    }

    // Getter & Setter
    public RoomTypeDto getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomTypeDto roomType) {
        this.roomType = roomType;
    }

    public FoodPlanDto getFoodPlan() {
        return foodPlan;
    }

    public void setFoodPlan(FoodPlanDto foodPlan) {
        this.foodPlan = foodPlan;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
