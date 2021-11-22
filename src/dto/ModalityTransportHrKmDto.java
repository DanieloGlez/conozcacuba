package dto;

public class ModalityTransportHrKmDto extends ModalityTransportDto implements Dto {
    private float costTraveledKm;
    private float costHr;
    private float costKmExtras;
    private float costHrExtras;
    private ContractTransportDto contractTransportDto;
    private VehicleDto vehicleDto;

    // Constructors
    public ModalityTransportHrKmDto(int id, float costTraveledKm, float costHr, float costKmExtras, float costHrExtras, ContractTransportDto contractTransportDto, VehicleDto vehicleDto) {
        super(id);
        this.costTraveledKm = costTraveledKm;
        this.costHr = costHr;
        this.costKmExtras = costKmExtras;
        this.costHrExtras = costHrExtras;
        this.contractTransportDto = contractTransportDto;
        this.vehicleDto = vehicleDto;
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

    public ContractTransportDto getContractTransportDto() {
        return contractTransportDto;
    }

    public void setContractTransportDto(ContractTransportDto contractTransportDto) {
        this.contractTransportDto = contractTransportDto;
    }

    public VehicleDto getVehicleDto() {
        return vehicleDto;
    }

    public void setVehicleDto(VehicleDto vehicleDto) {
        this.vehicleDto = vehicleDto;
    }
}