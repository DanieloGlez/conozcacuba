package dto.nom;

import dto.Dto;
import dto.nom.NomenclatorDto;

import java.util.Date;

public class DailyActivityDto extends NomenclatorDto implements Dto {
    //private float price;

    // Constructors
    public DailyActivityDto(int id, String name) {
        super(id, name);
        //setPrice(cost);
    }

    /*public float getPrice() {
        return price;
    }

    public void setPrice(float cost) {
        this.price =  cost * (float) 1.1;
    }*/
}
