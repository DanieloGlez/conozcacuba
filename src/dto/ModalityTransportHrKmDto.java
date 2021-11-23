package dto;

public class ModalityTransportHrKmDto extends ModalityTransportDto implements Dto {
    private float costTraveledKm;
    private float costHr;
    private float costKmExtras;
    private float costHrExtras;
    private ContractTransportDto contractTransport;
    private VehicleDto vehicle;

    // Constructors
    public ModalityTransportHrKmDto(int id, float costTraveledKm, float costHr, float costKmExtras, float costHrExtras, ContractTransportDto contractTransport, VehicleDto vehicle) {
        super(id);
        this.costTraveledKm = costTraveledKm;
        this.costHr = costHr;
        this.costKmExtras = costKmExtras;
        this.costHrExtras = costHrExtras;
        this.contractTransport = contractTransport;
        this.vehicle = vehicle;
    }

    // Getters and Setters
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

    public ContractTransportDto getContractTransport() {
        return contractTransport;
    }

    public void setContractTransport(ContractTransportDto contractTransport) {
        this.contractTransport = contractTransport;
    }

    public VehicleDto getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleDto vehicle) {
        this.vehicle = vehicle;
    }
}
