package service;

import dto.ContractDto;
import dto.TouristicPackageDto;
import dto.nom.FoodPlanDto;
import dto.nom.ModalityCommercialDto;
import dto.nom.RoomTypeDto;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class TouristicPackageServices implements Services<TouristicPackageDto>{
    @Override
    public TouristicPackageDto load(int id) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.touristic_package_load_by_id(?)}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.setInt(2, id);
        callableStatement.execute();

        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
        resultSet.next();

        callableStatement.close();
        connection.close();

        return new TouristicPackageDto(
                resultSet.getInt("id_touristic_package"),
                resultSet.getString("promotionla_name"),
                resultSet.getInt("days_amount"),
                resultSet.getInt("nights_amount"),
                resultSet.getInt("pax_amount"),
                resultSet.getFloat("cost_hotel"),
                resultSet.getFloat("cost_transport"),
                resultSet.getFloat("cost_transport_hotel_airport"),
                resultSet.getFloat("cost_total"),
                resultSet.getFloat("price"),
                ServicesLocator.getContractServices().loadRelated(id)
        );
    }

    @Override
    public List<TouristicPackageDto> loadAll() throws SQLException {
        List<TouristicPackageDto> touristicPackageDtos = new LinkedList<>();
        TouristicPackageDto touristicPackageDto;
        ContractDto contractDto;
        Connection connection = ServicesLocator.getConnection();
        int idTouristicPackage;
        int idContract;
        List<Integer> idContainerTouristicPackage = new LinkedList<>();
        List<Integer> idContainerContract = new LinkedList<>();

        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.touristic_package_load()}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);


        while (resultSet.next()) {
            idTouristicPackage = resultSet.getInt("id_touristic_package");
            idContract = resultSet.getInt("id_contract");

            if (!idContainerTouristicPackage.contains(idTouristicPackage)) {
                idContainerTouristicPackage.add(idTouristicPackage);
                idContainerContract.clear();
                contractDto = ServicesLocator.getContractServices().load(idContract);
                List<ContractDto> ListContractInsert = new LinkedList<>();
                ListContractInsert.add(contractDto);
                idContainerContract.add(idContract);

                touristicPackageDtos.add(new TouristicPackageDto(
                        idTouristicPackage,
                        resultSet.getString("promotional_name"),
                        resultSet.getInt("days_amount"),
                        resultSet.getInt("nights_amount"),
                        resultSet.getInt("pax_amount"),
                        resultSet.getFloat("cost_hotel"),
                        resultSet.getFloat("cost_transport"),
                        resultSet.getFloat("cost_transport_hotel_airport"),
                        resultSet.getFloat("cost_total"),
                        resultSet.getFloat("price"),
                        ListContractInsert
                ));
            } else {
                int size = touristicPackageDtos.size();
                touristicPackageDto = touristicPackageDtos.get(size - 1);

                if (!idContainerContract.contains(idContract)) {
                    touristicPackageDto.getContracts().add(ServicesLocator.getContractServices().load(idContract));
                    idContainerContract.add(idContract);
                }
            }


        callableStatement.close();
        connection.close();
        return touristicPackageDtos;
    }

    @Override
    public void insert(TouristicPackageDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.touristic_package_insert(?,?,?,?,?,?,?,?,?)}");
        callableStatement.setString(1, dto.getPromotionalName());
        callableStatement.setInt(2, dto.getDaysAmount());
        callableStatement.setInt(3, dto.getNightsAmount());
        callableStatement.setInt(4, dto.getPaxAmount());
        callableStatement.setFloat(5, dto.getCostHotel());
        callableStatement.setFloat(6, dto.getCostTransport());
        callableStatement.setFloat(7, dto.getCostTransportHA());
        callableStatement.setFloat(8, dto.getCostTotal());
        callableStatement.setFloat(9, dto.getPrice());
        callableStatement.execute();
        callableStatement.close();
        connection.close();
    }

    @Override
    public void update(TouristicPackageDto dto) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.touristic_package_update(?,?,?,?,?,?,?,?,?,?)}");
        callableStatement.setInt(1, dto.getId());
        callableStatement.setString(2, dto.getPromotionalName());
        callableStatement.setInt(3, dto.getDaysAmount());
        callableStatement.setInt(4, dto.getNightsAmount());
        callableStatement.setInt(5, dto.getPaxAmount());
        callableStatement.setFloat(6, dto.getCostHotel());
        callableStatement.setFloat(7, dto.getCostTransport());
        callableStatement.setFloat(8, dto.getCostTransportHA());
        callableStatement.setFloat(9, dto.getCostTotal());
        callableStatement.setFloat(10, dto.getPrice());
        callableStatement.execute();
        callableStatement.close();
        connection.close();
    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{ call tpp.touristic_package_delete(?)}");
        callableStatement.setInt(1, id);
        callableStatement.execute();
        callableStatement.close();
        connection.close();
    }
}
