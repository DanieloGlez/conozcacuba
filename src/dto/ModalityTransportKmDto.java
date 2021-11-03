package dto;

public class ModalityTransportKmDto extends ModalityTransportDto {
    private float costKm;
    private float costKmRoundTrip;
    private float costHrWait;

    // Constructors
    public ModalityTransportKmDto(String id, float costKm, float costKmRoundTrip, float costHrWait) {
        super(id);
        this.costKm = costKm;
        this.costKmRoundTrip = costKmRoundTrip;
        this.costHrWait = costHrWait;
    }

    // Getter & Setter
    public float getCostKm() {
        return costKm;
    }

    public void setCostKm(float costKm) {
        this.costKm = costKm;
    }

    public float getCostKmRoundTrip() {
        return costKmRoundTrip;
    }

    public void setCostKmRoundTrip(float costKmRoundTrip) {
        this.costKmRoundTrip = costKmRoundTrip;
    }

    public float getCostHrWait() {
        return costHrWait;
    }

    public void setCostHrWait(float costHrWait) {
        this.costHrWait = costHrWait;
    }
}
