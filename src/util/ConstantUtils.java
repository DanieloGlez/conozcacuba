package util;

import sun.plugin.javascript.navig.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ConstantUtils {
    private static List<String>tableName= Arrays.asList(   "CompanyService",
            "CompanyTransport",
            "ContractHotel",
            "ContractService",
            "ContractTransport",
            "DailyActivity",
            "FoodPlan",
            "Hotel",
            "HotelFranchise",
            "Localization",
            "ModalityCommercial",
            "ModalityTransport",
            "ModalityTransportHrKm",
            "ModalityTransportKm",
            "ModalityTransportRt",
            "RoomPlan",
            "Season",
            "TouristcPackage",
            "User",
            "Vehicle");

    public static List<String> getTableName() {
        return tableName;
    }
}
