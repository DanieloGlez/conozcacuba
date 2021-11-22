package dto;

public class ModalityTransportRtDto extends ModalityTransportDto implements Dto {
    private String rtDescription;
    private float costRt;
    private float costRoundTrip;
    private ContractDto contractDto;
    private VehicleDto vehicleDto;

    // Constructors
    public ModalityTransportRtDto(int id, String rtDescription, float costRt, float costRoundTrip, ContractDto contractDto, VehicleDto vehicleDto) {
        super(id);
        this.rtDescription = rtDescription;
        this.costRt = costRt;
        this.costRoundTrip = costRoundTrip;
        this.contractDto = contractDto;
        this.vehicleDto = vehicleDto;
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

    public ContractDto getContractDto() {
        return contractDto;
    }

    public void setContractDto(ContractDto contractDto) {
        this.contractDto = contractDto;
    }

    public VehicleDto getVehicleDto() {
        return vehicleDto;
    }

    public void setVehicleDto(VehicleDto vehicleDto) {
        this.vehicleDto = vehicleDto;
    }
}
