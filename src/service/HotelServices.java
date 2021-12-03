package service;

import dto.HotelDto;
import dto.nom.*;
import service.nom.*;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class HotelServices implements Services<HotelDto> {
    @Override
    public HotelDto load(int id) throws SQLException {
        HotelDto hotelDto;
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        int idRoomType;
        int idFoodPlan;
        int idModalities;
        List<Integer> idContainerRoomType = new LinkedList<>();
        List<Integer> idContainerFoodPlan = new LinkedList<>();
        List<Integer> idContainerModalities = new LinkedList<>();
        RoomTypeServices roomTypeServices = ServicesLocator.getRoomTypeServices();
        FoodPlanServices foodPlanServices = ServicesLocator.getFoodPlanServices();
        ModalityCommercialServices modalityCommercialServices = ServicesLocator.getModalityCommercialServices();
        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.hotel_load_by_id(?)}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.setInt(2, id);
        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
        resultSet.next();

        hotelDto = new HotelDto(
                id,
                resultSet.getString("name"),
                resultSet.getString("address"),
                resultSet.getString("category"),
                resultSet.getString("telephone_number"),
                resultSet.getString("fax"),
                resultSet.getString("email"),
                resultSet.getFloat("dist_to_city"),
                resultSet.getFloat("dist_to_airport"),
                resultSet.getInt("rooms_amount"),
                resultSet.getInt("floors_amount"),
                ServicesLocator.getHotelFranchiseServices().load(resultSet.getInt("id_hotel_franchise")),
                ServicesLocator.getProvinceServices().load(resultSet.getInt("id_province")),
                ServicesLocator.getLocalizationServices().load(resultSet.getInt("id_localization")));

        while (resultSet.next()) {
            idRoomType = resultSet.getInt("id_room_type");
            idFoodPlan = resultSet.getInt("id_food_plan");
            idModalities = resultSet.getInt("id_modality_hotel_comertial");

            if (!idContainerRoomType.contains(idRoomType)) {
                hotelDto.getRoomTypes().add(roomTypeServices.load(idRoomType));
                idContainerRoomType.add(idRoomType);
            }

            if (!idContainerFoodPlan.contains(idFoodPlan)) {
                hotelDto.getFoodPlans().add(foodPlanServices.load(idFoodPlan));
                idContainerFoodPlan.add(idFoodPlan);
            }

            if (!idContainerModalities.contains(idModalities)) {
                hotelDto.getCommercialModalities().add(modalityCommercialServices.load(idModalities));
                idContainerModalities.add(idModalities);
            }
        }

        callableStatement.close();
        connection.close();
        return hotelDto;
    }

    @Override
    public List<HotelDto> loadAll() throws SQLException {
        List<HotelDto> ListHotelDtos = new LinkedList<>();
        HotelDto hotelDto = null;
        RoomTypeDto roomTypeDto;
        FoodPlanDto foodPlanDto;
        ModalityCommercialDto modalityCommercialDto;
        Connection connection = ServicesLocator.getConnection();
        int idHotel;
        int idRoomType;
        int idFoodPlan;
        int idModalities;
        List<Integer> idContainerHotel = new LinkedList<>();
        List<Integer> idContainerRoomType = new LinkedList<>();
        List<Integer> idContainerFoodPlan = new LinkedList<>();
        List<Integer> idContainerModalities = new LinkedList<>();
        RoomTypeServices roomTypeServices = ServicesLocator.getRoomTypeServices();
        FoodPlanServices foodPlanServices = ServicesLocator.getFoodPlanServices();
        ModalityCommercialServices modalityCommercialServices = ServicesLocator.getModalityCommercialServices();
        HotelFranchiseServices hotelFranchiseServices = ServicesLocator.getHotelFranchiseServices();
        ProvinceServices provinceServices = ServicesLocator.getProvinceServices();
        LocalizationServices localizationServices = ServicesLocator.getLocalizationServices();

        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.hotel_load()}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

        while (resultSet.next()) {
            idHotel = resultSet.getInt("id_hotel");
            idRoomType = resultSet.getInt("id_room_type");
            idFoodPlan = resultSet.getInt("id_food_plan");
            idModalities = resultSet.getInt("id_modality_hotel_comertial");

            if (!idContainerHotel.contains(idHotel)) {
                idContainerHotel.add(idHotel);
                idContainerRoomType.clear();
                idContainerFoodPlan.clear();
                idContainerModalities.clear();

                roomTypeDto = roomTypeServices.load(idRoomType);
                foodPlanDto = foodPlanServices.load(idFoodPlan);
                modalityCommercialDto = modalityCommercialServices.load(idModalities);

                List<RoomTypeDto> ListRoomTypeInsert = new LinkedList<>();
                List<FoodPlanDto> ListFoodPlanInsert = new LinkedList<>();
                List<ModalityCommercialDto> ListModalityCommercialInsert = new LinkedList<>();

                ListRoomTypeInsert.add(roomTypeDto);
                ListFoodPlanInsert.add(foodPlanDto);
                ListModalityCommercialInsert.add(modalityCommercialDto);

                idContainerRoomType.add(idRoomType);
                idContainerFoodPlan.add(idFoodPlan);
                idContainerModalities.add(idModalities);

                HotelFranchiseDto hotelFranchiseDto = hotelFranchiseServices.load(resultSet.getInt("id_hotel_franchise"));
                ProvinceDto provinceDto = provinceServices.load(resultSet.getInt("id_province"));
                LocalizationDto localizationDto = localizationServices.load(resultSet.getInt("id_hotel_franchise"));

                hotelDto = new HotelDto(
                        idHotel,
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("category"),
                        resultSet.getString("telephone_number"),
                        resultSet.getString("fax"),
                        resultSet.getString("email"),
                        resultSet.getFloat("dist_to_city"),
                        resultSet.getFloat("dist_to_airport"),
                        resultSet.getInt("rooms_amount"),
                        resultSet.getInt("floors_amount"),
                        hotelFranchiseDto,
                        provinceDto,
                        localizationDto);

                hotelDto.setRoomTypes(ListRoomTypeInsert);
                hotelDto.setFoodPlans(ListFoodPlanInsert);
                hotelDto.setCommercialModalities(ListModalityCommercialInsert);
                ListHotelDtos.add(hotelDto);
            } else {
                if (!idContainerRoomType.contains(idRoomType)) {
                    hotelDto.getRoomTypes().add(roomTypeServices.load(idRoomType));
                    idContainerRoomType.add(idRoomType);
                }

                if (!idContainerFoodPlan.contains(idFoodPlan)) {
                    hotelDto.getFoodPlans().add(foodPlanServices.load(idFoodPlan));
                    idContainerFoodPlan.add(idFoodPlan);
                }

                if (!idContainerModalities.contains(idModalities)) {
                    hotelDto.getCommercialModalities().add(modalityCommercialServices.load(idModalities));
                    idContainerModalities.add(idModalities);
                }
            }
        }

        callableStatement.close();
        connection.close();
        return ListHotelDtos;
    }

    @Override
    public void insert(HotelDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.hotel_insert(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
        callableStatement.setString(1, dto.getName());
        callableStatement.setInt(2, dto.getHotelFranchise().getId());
        callableStatement.setInt(3, dto.getProvince().getId());
        callableStatement.setString(4, dto.getAddress());
        callableStatement.setString(5, dto.getCategory());
        callableStatement.setString(6, dto.getTelephoneNumber());
        callableStatement.setString(7, dto.getFax());
        callableStatement.setString(8, dto.getEmail());
        callableStatement.setDouble(9, dto.getDistToCity());
        callableStatement.setDouble(10, dto.getDistToAirport());
        callableStatement.setInt(11, dto.getRoomsAmount());
        callableStatement.setInt(12, dto.getFloorsAmount());
        callableStatement.setInt(13, dto.getLocalization().getId());
        callableStatement.execute();

        connection.setAutoCommit(false);
        callableStatement = connection.prepareCall("{ ? = call tpp.hotel_return_id_max()}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
        resultSet.next();
        dto.setId(resultSet.getInt(1));

        connection.close();
    }

    @Override
    public void update(HotelDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.hotel_update(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
        callableStatement.setInt(1, dto.getId());
        callableStatement.setString(2, dto.getName());
        callableStatement.setInt(3, dto.getHotelFranchise().getId());
        callableStatement.setInt(4, dto.getProvince().getId());
        callableStatement.setString(5, dto.getAddress());
        callableStatement.setString(6, dto.getCategory());
        callableStatement.setString(7, dto.getTelephoneNumber());
        callableStatement.setString(8, dto.getFax());
        callableStatement.setString(9, dto.getEmail());
        callableStatement.setDouble(10, dto.getDistToCity());
        callableStatement.setDouble(11, dto.getDistToAirport());
        callableStatement.setInt(12, dto.getRoomsAmount());
        callableStatement.setInt(13, dto.getFloorsAmount());
        callableStatement.setInt(14, dto.getLocalization().getId());
        callableStatement.execute();

        callableStatement.close();
        connection.close();
    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.hotel_delete(?)}");
        callableStatement.setInt(1, id);
        callableStatement.execute();

        callableStatement.close();
        connection.close();
    }
}
