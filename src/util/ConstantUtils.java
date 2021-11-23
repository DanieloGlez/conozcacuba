package util;

import dto.*;
import dto.fun.*;
import dto.nom.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConstantUtils {
    private static final List<Class> tableClasses = Arrays.asList(
            // Entities
            ContractDto.class,
            ContractHotelDto.class,
            ContractServiceDto.class,
            ContractTransportDto.class,
            DailyActivityDto.class,
            HotelDto.class,
            ModalityTransportHrKmDto.class,
            ModalityTransportKmDto.class,
            ModalityTransportRtDto.class,
            SeasonDto.class,
            TouristicPackageDto.class,
            VehicleDto.class,

            // Functionality
            UserDto.class,

            // Nomenclators
            CompanyServiceDto.class,
            CompanyTransportDto.class,
            FoodPlanDto.class,
            HotelFranchiseDto.class,
            LocalizationDto.class,
            ModalityCommercialDto.class,
            RoomTypeDto.class,
            ServiceTypeDto.class,
            VehicleBrandDto.class
    );

    private static Map<String, Class> tableNames = null;

    public static Map<String, Class> getTableNames() {
        if(tableNames == null) {
            tableNames = new HashMap<>();

            tableClasses.forEach(tableClass -> {
                tableNames.put(tableClass.getSimpleName().replace("Dto", ""), tableClass);
            });
        }

        return tableNames;
    }
}
