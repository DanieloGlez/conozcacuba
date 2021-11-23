package dto;

public class ModalityTransportRtDto extends ModalityTransportDto implements Dto {
    private String rtDescription;
    private float costRt;
    private float costRoundTrip;
    private ContractTransportDto contractTransport;
    private VehicleDto vehicle;

    // Constructors

    public ModalityTransportRtDto(int id, String rtDescription, float costRt, float costRoundTrip, ContractTransportDto contractTransport, VehicleDto vehicle) {
        super(id);
        this.rtDescription = rtDescription;
        this.costRt = costRt;
        this.costRoundTrip = costRoundTrip;
        this.contractTransport = contractTransport;
        this.vehicle = vehicle;
    }

    // Getters and Setters

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
