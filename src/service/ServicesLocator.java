package service;

import service.fun.RoleServices;
import service.fun.UserServices;
import service.nom.*;
import service.rep.ContractHotelReportServices;
import service.rep.ContractServiceReportServices;
import service.rep.ContractTransportReportServices;
import service.rep.SeasonContractHotelReportServices;
import util.ConfigurationUtils;
import util.DatabaseUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class ServicesLocator {
    private static CompanyServiceServices companyServiceServices;
    private static CompanyTransportServices companyTransportServices;
    private static ContractTransportServices contractTransportServices;
    private static HotelServices hotelServices;
    private static ContractServices contractServices;
    private static ModalityTransportKmServices modalityTransportKmServices;
    private static ModalityTransportRtServices modalityTransportRtServices;
    private static ModalityTransportHrKmServices modalityTransportHrKmServices;
    private static SeasonServices seasonServices;
    private static DailyActivityServices dailyActivityServices;
    private static ContractTypeServices contractTypeServices;
    private static FoodPlanServices foodPlanServices;
    private static HotelFranchiseServices hotelFranchiseServices;
    private static LocalizationServices localizationServices;
    private static ModalityCommercialServices modalityCommercialServices;
    private static ProvinceServices provinceServices;
    private static RoomTypeServices roomTypeServices;
    private static UserServices userServices;
    private static RoleServices roleServices;
    private static VehicleServices vehicleServices;
    private static VehicleBrandServices vehicleBrandServices;
    private static ServiceTypeServices serviceTypeServices;
    private static ContractServiceServices contractServiceServices;
    private static ContractHotelServices contractHotelServices;
    private static TouristicPackageServices touristicPackageServices;
    private static RelationContractServiceDailyActServices relationContractServiceDailyActServices;
    private static RelationContractServiceServiceTypeServices relationContractServiceServiceTypeServices;
    private static RelationContractServiceCompanyServiceServices relationContractServiceCompanyServiceServices;
    private static RelationContractTransportVehicleServices relationContractTransportVehicleServices;
    private static RelationHotelFoodPlanServices relationHotelFoodPlanServices;
    private static RelationHotelModalityCommercialServices relationHotelModalityCommercialServices;
    private static RelationHotelRoomTypeServices relationHotelRoomTypeServices;
    private static RelationContractHotelSeasonServices relationContractHotelSeasonServices;
    private static RelationContractHotelRoomFoodSeasonServices relationContractHotelRoomFoodSeasonServices;

    // Reports
    private static ContractHotelReportServices contractHotelReportServices;
    private static ContractTransportReportServices contractTransportReportServices;
    private static SeasonContractHotelReportServices seasonContractHotelReportServices;
    private static ContractServiceReportServices contractServiceReportServices;
    private static HotelReportServices hotelReportServices;

    // Connection
    public static Connection getConnection() throws SQLException {
        return new DatabaseUtils(
                ConfigurationUtils.getServer(),
                ConfigurationUtils.getDatabase(),
                ConfigurationUtils.getUser(),
                ConfigurationUtils.getPassword()
        ).getConnection();
    }

    // Services
    public static CompanyServiceServices getCompanyServiceServices() {
        if (companyServiceServices == null)
            companyServiceServices = new CompanyServiceServices();

        return companyServiceServices;
    }

    public static CompanyTransportServices getCompanyTransportServices() {
        if (companyTransportServices == null)
            companyTransportServices = new CompanyTransportServices();

        return companyTransportServices;
    }

    public static ContractServices getContractServices() {
        if (contractServices == null)
            contractServices = new ContractServices();

        return contractServices;
    }

    public static HotelServices getHotelServices() {
        if (hotelServices == null)
            hotelServices = new HotelServices();

        return hotelServices;
    }


    public static ModalityTransportHrKmServices getModalityTransportHrKmServices() {
        if (modalityTransportHrKmServices == null)
            modalityTransportHrKmServices = new ModalityTransportHrKmServices();

        return modalityTransportHrKmServices;
    }

    public static ModalityTransportRtServices getModalityTransportRtServices() {
        if (modalityTransportRtServices == null)
            modalityTransportRtServices = new ModalityTransportRtServices();

        return modalityTransportRtServices;
    }

    public static ModalityTransportKmServices getModalityTransportKmServices() {
        if (modalityTransportKmServices == null)
            modalityTransportKmServices = new ModalityTransportKmServices();

        return modalityTransportKmServices;
    }

    public static ContractTransportServices getContractTransportServices() {
        if (contractTransportServices == null)
            contractTransportServices = new ContractTransportServices();

        return contractTransportServices;
    }

    public static SeasonServices getSeasonServices() {
        if (seasonServices == null)
            seasonServices = new SeasonServices();

        return seasonServices;
    }

    public static DailyActivityServices getDailyActivityServices() {
        if (dailyActivityServices == null)
            dailyActivityServices = new DailyActivityServices();

        return dailyActivityServices;
    }

    public static ContractTypeServices getContractTypeServices() {
        if (contractTypeServices == null)
            contractTypeServices = new ContractTypeServices();

        return contractTypeServices;
    }

    public static FoodPlanServices getFoodPlanServices() {
        if (foodPlanServices == null)
            foodPlanServices = new FoodPlanServices();

        return foodPlanServices;
    }

    public static HotelFranchiseServices getHotelFranchiseServices() {
        if (hotelFranchiseServices == null)
            hotelFranchiseServices = new HotelFranchiseServices();

        return hotelFranchiseServices;
    }

    public static LocalizationServices getLocalizationServices() {
        if (localizationServices == null)
            localizationServices = new LocalizationServices();

        return localizationServices;
    }

    public static ModalityCommercialServices getModalityCommercialServices() {
        if (modalityCommercialServices == null)
            modalityCommercialServices = new ModalityCommercialServices();

        return modalityCommercialServices;
    }

    public static ProvinceServices getProvinceServices() {
        if (provinceServices == null)
            provinceServices = new ProvinceServices();

        return provinceServices;
    }

    public static RoomTypeServices getRoomTypeServices() {
        if (roomTypeServices == null)
            roomTypeServices = new RoomTypeServices();

        return roomTypeServices;
    }

    public static UserServices getUserServices() {
        if (userServices == null)
            userServices = new UserServices();

        return userServices;
    }

    public static RoleServices getRoleServices() {
        if(roleServices == null)
            roleServices = new RoleServices();

        return roleServices;
    }

    public static VehicleServices getVehicleServices() {
        if (vehicleServices == null)
            vehicleServices = new VehicleServices();

        return vehicleServices;
    }

    public static VehicleBrandServices getVehicleBrandServices() {
        if (vehicleBrandServices == null)
            vehicleBrandServices = new VehicleBrandServices();

        return vehicleBrandServices;
    }

    public static ServiceTypeServices getServiceTypeServices() {
        if (serviceTypeServices == null)
            serviceTypeServices = new ServiceTypeServices();

        return serviceTypeServices;
    }


    public static ContractServiceServices getContractServiceServices() {
        if (contractServiceServices == null)
            contractServiceServices = new ContractServiceServices();
        return contractServiceServices;
    }

    public static ContractHotelServices getContractHotelServices() {
        if (contractHotelServices == null)
            contractHotelServices = new ContractHotelServices();
        return contractHotelServices;
    }

    public static TouristicPackageServices getTouristicPackageServices() {
        if (touristicPackageServices == null)
            touristicPackageServices = new TouristicPackageServices();
        return touristicPackageServices;
    }

    public static RelationContractServiceDailyActServices getRelationContractServiceDailyActServices() {
        if (relationContractServiceDailyActServices == null)
            relationContractServiceDailyActServices = new RelationContractServiceDailyActServices();
        return relationContractServiceDailyActServices;
    }

    public static RelationContractServiceServiceTypeServices getRelationContractServiceServiceTypeServices() {
        if (relationContractServiceServiceTypeServices == null)
            relationContractServiceServiceTypeServices = new RelationContractServiceServiceTypeServices();
        return relationContractServiceServiceTypeServices;
    }

    public static RelationContractServiceCompanyServiceServices getRelationContractServiceCompanyServiceServices() {
        if (relationContractServiceCompanyServiceServices == null)
            relationContractServiceCompanyServiceServices = new RelationContractServiceCompanyServiceServices();
        return relationContractServiceCompanyServiceServices;
    }

    public static RelationContractTransportVehicleServices getRelationContractTransportVehicleServices() {
        if (relationContractTransportVehicleServices == null)
            relationContractTransportVehicleServices = new RelationContractTransportVehicleServices();
        return relationContractTransportVehicleServices;
    }

    public static RelationHotelFoodPlanServices getRelationHotelFoodPlanServices() {
        if (relationHotelFoodPlanServices == null)
            relationHotelFoodPlanServices = new RelationHotelFoodPlanServices();
        return relationHotelFoodPlanServices;
    }

    public static RelationHotelModalityCommercialServices getRelationHotelModalityCommercialServices() {
        if (relationHotelModalityCommercialServices == null)
            relationHotelModalityCommercialServices = new RelationHotelModalityCommercialServices();
        return relationHotelModalityCommercialServices;
    }

    public static RelationHotelRoomTypeServices getRelationHotelRoomTypeServices() {
        if (relationHotelRoomTypeServices == null)
            relationHotelRoomTypeServices = new RelationHotelRoomTypeServices();
        return relationHotelRoomTypeServices;
    }

    public static RelationContractHotelSeasonServices getRelationContractHotelSeasonServices() {
        if (relationContractHotelSeasonServices == null)
            relationContractHotelSeasonServices = new RelationContractHotelSeasonServices();
        return relationContractHotelSeasonServices;
    }

    public static RelationContractHotelRoomFoodSeasonServices getRelationContractHotelRoomFoodSeasonServices(){
        if (relationContractHotelRoomFoodSeasonServices == null)
            relationContractHotelRoomFoodSeasonServices = new RelationContractHotelRoomFoodSeasonServices();
        return relationContractHotelRoomFoodSeasonServices;
    }

    // Reports
    public static ContractHotelReportServices getContractHotelReportServices() {
        if(contractHotelReportServices == null)
            contractHotelReportServices = new ContractHotelReportServices();

        return contractHotelReportServices;
    }

    public static ContractTransportReportServices getContractTransportReportServices() {
        if(contractTransportReportServices == null)
            contractTransportReportServices = new ContractTransportReportServices();

        return contractTransportReportServices;
    }

    public static SeasonContractHotelReportServices getSeasonContractHotelReportServices() {
        if(seasonContractHotelReportServices == null)
            seasonContractHotelReportServices = new SeasonContractHotelReportServices();

        return seasonContractHotelReportServices;
    }

    public static ContractServiceReportServices getContractServiceReportServices() {
        if(contractServiceReportServices == null)
            contractServiceReportServices = new ContractServiceReportServices();

        return contractServiceReportServices;
    }

    public static HotelReportServices getHotelReportServices() {
        if(hotelReportServices == null)
            hotelReportServices = new HotelReportServices();

        return hotelReportServices;
    }
}
