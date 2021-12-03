package dto;

public class ModalityTransportKmDto extends ModalityTransportDto implements Dto {
    private float costKm;
    private float costKmRoundTrip;
    private float costHrWait;
    private ContractTransportDto contractTransport;
    private VehicleDto vehicle;

    // Constructors
    public ModalityTransportKmDto(int id, float costKm, float costKmRoundTrip, float costHrWait, ContractTransportDto contractTransport, VehicleDto vehicle) {
        super(id);
        this.costKm = costKm;
        this.costKmRoundTrip = costKmRoundTrip;
        this.costHrWait = costHrWait;
        this.contractTransport = contractTransport;
        this.vehicle = vehicle;
    }

    // Getters and Setters

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

    @Override
    public float getTotalCost() {
        return costHrWait+costKm+costKmRoundTrip;
    }
}
