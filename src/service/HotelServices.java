package service;

import dto.HotelDto;
import dto.nom.*;

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
                hotelDto.getRoomTypes().add(ServicesLocator.getRoomTypeServices().load(idRoomType));
                idContainerRoomType.add(idRoomType);
            }

            if (!idContainerFoodPlan.contains(idFoodPlan)) {
                hotelDto.getFoodPlans().add(ServicesLocator.getFoodPlanServices().load(idFoodPlan));
                idContainerFoodPlan.add(idFoodPlan);
            }

            if (!idContainerModalities.contains(idModalities)) {
                hotelDto.getCommercialModalities().add(ServicesLocator.getModalityCommercialServices().load(idModalities));
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

            if (!idContainerHotel.contains(idHotel)) {//inserto los elementos de la primera aparicion de un identificador
                idContainerHotel.add(idHotel);
                idContainerRoomType.clear();
                idContainerFoodPlan.clear();
                idContainerModalities.clear();
                roomTypeDto = ServicesLocator.getRoomTypeServices().load(idRoomType);
                foodPlanDto = ServicesLocator.getFoodPlanServices().load(idFoodPlan);
                modalityCommercialDto = ServicesLocator.getModalityCommercialServices().load(idModalities);
                List<RoomTypeDto> ListRoomTypeInsert = new LinkedList<>();
                List<FoodPlanDto> ListFoodPlanInsert = new LinkedList<>();
                List<ModalityCommercialDto> ListModalityCommercialInsert = new LinkedList<>();
                ListRoomTypeInsert.add(roomTypeDto);
                ListFoodPlanInsert.add(foodPlanDto);
                ListModalityCommercialInsert.add(modalityCommercialDto);
                idContainerRoomType.add(idRoomType);
                idContainerFoodPlan.add(idFoodPlan);
                idContainerModalities.add(idModalities);

                CallableStatement callableStatementHF = connection.prepareCall("{? = call tpp.n_hotel_franchise_load_by_id(?)}");
                callableStatementHF.registerOutParameter(1, Types.REF_CURSOR);
                int idHotelFranchise = resultSet.getInt("id_hotel_franchise");
                callableStatementHF.setInt(2, idHotelFranchise);
                callableStatementHF.execute();
                ResultSet resultSetHF = (ResultSet) callableStatementHF.getObject(1);
                resultSetHF.next();
                HotelFranchiseDto hotelFranchiseDto = new HotelFranchiseDto(idHotelFranchise, resultSetHF.getString("name"));
                callableStatementHF.close();

                CallableStatement callableStatementPr = connection.prepareCall("{? = call tpp.n_province_load_by_id(?)}");
                callableStatementPr.registerOutParameter(1, Types.REF_CURSOR);
                int idProvince = resultSet.getInt("id_province");
                callableStatementPr.setInt(2, idProvince);
                callableStatementPr.execute();
                ResultSet resultSetPr = (ResultSet) callableStatementPr.getObject(1);
                resultSetPr.next();
                ProvinceDto provinceDto = new ProvinceDto(idProvince, resultSetPr.getString("name"));
                callableStatementPr.close();

                CallableStatement callableStatementLoc = connection.prepareCall("{? = call tpp.n_localization_load_by_id(?)}");
                callableStatementLoc.registerOutParameter(1, Types.REF_CURSOR);
                int idLocalization = resultSet.getInt("id_hotel_franchise");
                callableStatementLoc.setInt(2, idLocalization);
                callableStatementLoc.execute();
                ResultSet resultSetLoc = (ResultSet) callableStatementLoc.getObject(1);
                resultSetLoc.next();
                LocalizationDto localizationDto = new LocalizationDto(idLocalization, resultSetLoc.getString("name"));
                callableStatementLoc.close();

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
            } else {//inserto para el mismo id los tipos de hab, planes alim y modalidades diferentes

                if (!idContainerRoomType.contains(idRoomType)) {
                    CallableStatement callableStatementRoom = connection.prepareCall("{? = call tpp.n_room_type_load_by_id(?)}");
                    callableStatementRoom.registerOutParameter(1, Types.REF_CURSOR);
                    callableStatementRoom.setInt(2, idRoomType);
                    callableStatementRoom.execute();
                    ResultSet resultSetRoom = (ResultSet) callableStatementRoom.getObject(1);
                    resultSetRoom.next();
                    roomTypeDto = new RoomTypeDto(idRoomType, resultSetRoom.getString("name"));
                    hotelDto.getRoomTypes().add(roomTypeDto);
                    idContainerRoomType.add(idRoomType);
                    callableStatementRoom.close();
                }

                if (!idContainerFoodPlan.contains(idFoodPlan)) {
                    CallableStatement callableStatementFood = connection.prepareCall("{? = call tpp.n_food_plan_load_by_id(?)}");
                    callableStatementFood.registerOutParameter(1, Types.REF_CURSOR);
                    callableStatementFood.setInt(2, idFoodPlan);
                    callableStatementFood.execute();
                    ResultSet resultSetFood = (ResultSet) callableStatementFood.getObject(1);
                    resultSetFood.next();
                    foodPlanDto = new FoodPlanDto(idFoodPlan, resultSetFood.getString("name"));
                    hotelDto.getFoodPlans().add(foodPlanDto);
                    idContainerFoodPlan.add(idFoodPlan);
                    callableStatementFood.close();
                }

                if (!idContainerModalities.contains(idModalities)) {
                    CallableStatement callableStatementMod = connection.prepareCall("{? = call tpp.n_modality_hotel_comertial_load_by_id(?)}");
                    callableStatementMod.registerOutParameter(1, Types.REF_CURSOR);
                    callableStatementMod.setInt(2, idModalities);
                    callableStatementMod.execute();
                    ResultSet resultSetMod = (ResultSet) callableStatementMod.getObject(1);
                    resultSetMod.next();
                    modalityCommercialDto = new ModalityCommercialDto(idModalities, resultSetMod.getString("name"));
                    hotelDto.getCommercialModalities().add(modalityCommercialDto);
                    idContainerModalities.add(idModalities);
                    callableStatementMod.close();
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
        callableStatement.setString(2, dto.getAddress());
        callableStatement.setString(3, dto.getCategory());
        callableStatement.setString(4, dto.getTelephoneNumber());
        callableStatement.setString(5, dto.getFax());
        callableStatement.setString(6, dto.getEmail());
        callableStatement.setFloat(7, dto.getDistToCity());
        callableStatement.setFloat(8, dto.getDistToAirport());
        callableStatement.setInt(9, dto.getRoomsAmount());
        callableStatement.setInt(10, dto.getFloorsAmount());
        callableStatement.setInt(11, dto.getHotelFranchise().getId());
        callableStatement.setInt(12, dto.getProvince().getId());
        callableStatement.setInt(13, dto.getLocalization().getId());
        callableStatement.execute();

        connection.close();
    }

    @Override
    public void update(HotelDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.hotel_update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
        callableStatement.setInt(1, dto.getId());
        callableStatement.setString(2, dto.getName());
        callableStatement.setString(3, dto.getAddress());
        callableStatement.setString(4, dto.getCategory());
        callableStatement.setString(5, dto.getTelephoneNumber());
        callableStatement.setString(6, dto.getFax());
        callableStatement.setString(7, dto.getEmail());
        callableStatement.setFloat(8, dto.getDistToCity());
        callableStatement.setFloat(9, dto.getDistToAirport());
        callableStatement.setInt(10, dto.getRoomsAmount());
        callableStatement.setInt(11, dto.getFloorsAmount());
        callableStatement.setInt(12, dto.getHotelFranchise().getId());
        callableStatement.setInt(13, dto.getProvince().getId());
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
