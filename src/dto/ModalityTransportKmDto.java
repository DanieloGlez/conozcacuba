package dto;

public class ModalityTransportKmDto extends ModalityTransportDto {
    private float costKm;
    private float costKmRoundTrip;
    private float costHrWait;
    private int idContract;
    private String idVehicle;

    // Constructors
    public ModalityTransportKmDto(String id, float costKm, float costKmRoundTrip, float costHrWait, int idContract, String idVehicle) {
        super(id);
        this.costKm = costKm;
        this.costKmRoundTrip = costKmRoundTrip;
        this.costHrWait = costHrWait;
        this.idContract = idContract;
        this.idVehicle = idVehicle;
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
