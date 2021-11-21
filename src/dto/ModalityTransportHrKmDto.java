package dto;

public class ModalityTransportHrKmDto extends ModalityTransportDto {
    private float costTraveledKm;
    private float costHr;
    private float costKmExtras;
    private float costHrExtras;
    private int idContract;
    private String idVehicle;

    // Constructors
    public ModalityTransportHrKmDto(String id, float costTraveledKm, float costHr, float costKmExtras, float costHrExtras, int idContract, String idVehicle) {
        super(id);
        this.costTraveledKm = costTraveledKm;
        this.costHr = costHr;
        this.costKmExtras = costKmExtras;
        this.costHrExtras = costHrExtras;
        this.idContract = idContract;
        this.idVehicle = idVehicle;
    }

    // Getter & Setter
    public float getCostTraveledKm() {
        return costTraveledKm;
    }

    public void setCostTraveledKm(float costTraveledKm) {
        this.costTraveledKm = costTraveledKm;
    }

    public float getCostHr() {
        return costHr;
    }

    public void setCostHr(float costHr) {
        this.costHr = costHr;
    }

    public float getCostKmExtras() {
        return costKmExtras;
    }

    public void setCostKmExtras(float costKmExtras) {
        this.costKmExtras = costKmExtras;
    }

    public float getCostHrExtras() {
        return costHrExtras;
    }

    public void setCostHrExtras(float costHrExtras) {
        this.costHrExtras = costHrExtras;
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
