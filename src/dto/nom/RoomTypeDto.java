package dto.nom;

import dto.Dto;

public class RoomTypeDto extends NomenclatorDto implements Dto {
    //private float price;
    // Constructors
    public RoomTypeDto(int id, String name) {
        super(id, name);
       // this.price = price;
    }

    /*public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }*/

    @Override
    public String toString() {
        return name;
    }
}
