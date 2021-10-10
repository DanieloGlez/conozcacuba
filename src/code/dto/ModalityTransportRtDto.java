package code.dto;

public class ModalityTransportRtDto extends ModalityTransportDto {
    private String rtDescription;
    private float costRt;
    private float costRoundTrip;

    // Constructors
    public ModalityTransportRtDto(String id, String rtDescription, float costRt, float costRoundTrip) {
        super(id);
        this.rtDescription = rtDescription;
        this.costRt = costRt;
        this.costRoundTrip = costRoundTrip;
    }

    // Getter & Setter
    public String getRtDescription() {
        return rtDescription;
    }

    public void setRtDescription(String rtDescription) {
        this.rtDescription = rtDescription;
    }

    public float getCostRt() {
        return costRt;
    }

    public void setCostRt(float costRt) {
        this.costRt = costRt;
    }

    public float getCostRoundTrip() {
        return costRoundTrip;
    }

    public void setCostRoundTrip(float costRoundTrip) {
        this.costRoundTrip = costRoundTrip;
    }
}
