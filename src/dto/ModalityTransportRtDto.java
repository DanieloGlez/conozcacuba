package dto;

public class ModalityTransportRtDto extends ModalityTransportDto {
    private String rtDescription;
    private float costRt;
    private float costRoundTrip;
    private int idContract;
    private String idVehicle;

    // Constructors
    public ModalityTransportRtDto(String id, String rtDescription, float costRt, float costRoundTrip, int idContract, String idVehicle) {
        super(id);
        this.rtDescription = rtDescription;
        this.costRt = costRt;
        this.costRoundTrip = costRoundTrip;
        this.idContract = idContract;
        this.idVehicle = idVehicle;
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

    public int getIdContract() {
        return idContract;
    }

    public void setIdContract(int idContract) {
        this.idContract = idContract;
    }

    public String getIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(String idVehicle) {
        this.idVehicle = idVehicle;
    }
}
