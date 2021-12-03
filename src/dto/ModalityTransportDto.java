package dto;

public abstract class ModalityTransportDto {
    private int id;

    // Constructors
    public ModalityTransportDto(int id) {
        this.id = id;
    }

    // Getter & Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public abstract float getTotalCost();
}
