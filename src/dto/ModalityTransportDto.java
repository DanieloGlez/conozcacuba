package dto;

public abstract class ModalityTransportDto {
    private String id;

    // Constructors
    public ModalityTransportDto(String id) {
        this.id = id;
    }

    // Getter & Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
