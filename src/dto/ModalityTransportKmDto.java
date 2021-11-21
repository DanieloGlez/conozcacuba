package dto;

public class ModalityTransportKmDto extends ModalityTransportDto {
    private float costKm;
    private float costKmRoundTrip;
    private float costHrWait;
    private ContractDto contractDto;
    private VehicleDto vehicleDto;

    // Constructors
    public ModalityTransportKmDto(int id, float costKm, float costKmRoundTrip, float costHrWait, ContractDto contractDto, VehicleDto vehicleDto) {
        super(id);
        this.costKm = costKm;
        this.costKmRoundTrip = costKmRoundTrip;
        this.costHrWait = costHrWait;
        this.contractDto = contractDto;
        this.vehicleDto = vehicleDto;
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
