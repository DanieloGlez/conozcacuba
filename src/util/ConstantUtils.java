package util;

import dto.*;
import dto.fun.*;
import dto.nom.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;

import javax.swing.text.html.ListView;
import java.util.*;

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

    public static final ArrayList<String> categories = new ArrayList<>(Arrays.asList("Contracts Tables","Hotels Tables","Transport Tables", "Services Tables","Touristic Packages"));




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

    /*public static ArrayList<String> getCategories(){
        return categories;

    }*/



}
