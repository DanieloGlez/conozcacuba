package dto.nom;

import dto.Dto;

public class RoomTypeDto extends NomenclatorDto implements Dto {
    // Constructors
    public RoomTypeDto(int id, String name) {
        super(id, name);
    }


    @Override
    public String toString() {
        return name;
    }


}
