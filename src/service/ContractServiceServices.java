package service;

import dto.*;
import dto.nom.*;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class ContractServiceServices implements Services<ContractServiceDto> {
    @Override
    public ContractServiceDto load(int id) throws SQLException {
        ContractServiceDto contractServiceDto;
        ContractDto contractDto;
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        int idContract;
        int idDailyActivity;
        int idCompany;
        int idServiceType;
        List<Integer> idContainerDailyActivity = new LinkedList<>();
        List<Integer> idContainerCompany = new LinkedList<>();
        List<Integer> idContainerServiceType = new LinkedList<>();
        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.contract_service_load_by_id(?)}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.setInt(2, id);
        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
        resultSet.next();

        idContract = resultSet.getInt("id_contract");
        contractDto = ServicesLocator.getContractServices().load(idContract);

        contractServiceDto = new ContractServiceDto(
                idContract,
                contractDto.getStartDate(),
                contractDto.getFinishDate(),
                contractDto.getConciliationDate(),
                contractDto.getDescription(),
                contractDto.getContractTypeDto(),
                resultSet.getFloat("pax_cost"),
                ServicesLocator.getProvinceServices().load(resultSet.getInt("id_province")));

        while (resultSet.next()) {
            idDailyActivity = resultSet.getInt("id_daily_activity");
            idCompany = resultSet.getInt("id_company_service");
            idServiceType = resultSet.getInt("id_service_type");

            if (!idContainerDailyActivity.contains(idDailyActivity)) {
                contractServiceDto.getDailyActivities().add(ServicesLocator.getDailyActivityServices().load(idDailyActivity));
                idContainerDailyActivity.add(idDailyActivity);
            }

            if (!idContainerCompany.contains(idCompany)) {
                contractServiceDto.getCompaniesService().add(ServicesLocator.getCompanyServiceServices().load(idCompany));
                idContainerCompany.add(idCompany);
            }

            if (!idContainerServiceType.contains(idServiceType)) {
                contractServiceDto.getServiceType().add(ServicesLocator.getServiceTypeServices().load(idServiceType));
                idContainerServiceType.add(idServiceType);
            }
        }

        callableStatement.close();
        connection.close();
        return contractServiceDto;
    }

   /* @Override
    public ContractServiceDto load(int id) throws SQLException {
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.contract_service_load_by_id(?)}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.setInt(2, id);
        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
        resultSet.next();

        connection.close();
        return new ContractServiceDto(
                id,
                resultSet.getDate("start_date"),
                resultSet.getDate("finish_date"),
                resultSet.getDate("conciliation_date"),
                resultSet.getString("description"),
                ServicesLocator.getContractServices().load(id).getContractTypeDto(),
                resultSet.getFloat("pax_cost"),
                ServicesLocator.getProvinceServices().load(resultSet.getInt("id_province")),
                ServicesLocator.getDailyActivityServices().loadRelated(id),
                ServicesLocator.getCompanyServiceServices().loadRelated(id),
                ServicesLocator.getServiceTypeServices().loadRelated(id)
        );
    }*/

    /*@Override
    public List<ContractServiceDto> loadAll() throws SQLException {
        List<ContractServiceDto> contractServiceDto = new LinkedList<>();
        Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{ ? = call tpp.contract_service_load()}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

        while (resultSet.next()) {
            ContractDto contractDto = ServicesLocator.getContractServices().load(resultSet.getInt("id_contract"));
            contractServiceDto.add(new ContractServiceDto(
                    contractDto.getId(),
                    contractDto.getStartDate(),
                    contractDto.getFinishDate(),
                    contractDto.getConciliationDate(),
                    contractDto.getDescription(),
                    contractDto.getContractTypeDto(),
                    resultSet.getFloat("pax_cost"),
                    ServicesLocator.getProvinceServices().load(resultSet.getInt("id_province")),
                    ServicesLocator.getDailyActivityServices().loadRelated(contractDto.getId()),
                    ServicesLocator.getCompanyServiceServices().loadRelated(contractDto.getId()),
                    ServicesLocator.getServiceTypeServices().loadRelated(contractDto.getId())
            ));
        }

        callableStatement.close();
        connection.close();
        return contractServiceDto;
    }*/

    @Override
    public List<ContractServiceDto> loadAll() throws SQLException {
        List<ContractServiceDto> ListContractService = new LinkedList<>();
        ContractServiceDto contractServiceDto = null;
        DailyActivityDto dailyActivityDto;
        CompanyServiceDto companyServiceDto;
        ServiceTypeDto serviceTypeDto;
        Connection connection = ServicesLocator.getConnection();
        int idContractService;
        int idDailyActivity;
        int idCompany;
        int idServiceType;
        List<Integer> idContainerContractService = new LinkedList<>();
        List<Integer> idContainerDailyActivity = new LinkedList<>();
        List<Integer> idContainerCompany = new LinkedList<>();
        List<Integer> idContainerServiceType = new LinkedList<>();

        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall("{? = call tpp.contract_service_load()}");
        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.execute();
        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

        while (resultSet.next()) {
            idContractService = resultSet.getInt("id_contract");
            idDailyActivity = resultSet.getInt("id_daily_activity");
            idCompany = resultSet.getInt("id_company_service");
            idServiceType = resultSet.getInt("id_service_type");

            if (!idContainerContractService.contains(idContractService)) {//inserto los elementos de la primera aparicion de un identificador
                ContractDto contractDto = ServicesLocator.getContractServices().load(idContractService);
                idContainerContractService.add(idContractService);
                idContainerDailyActivity.clear();
                idContainerCompany.clear();
                idContainerServiceType.clear();

                dailyActivityDto = ServicesLocator.getDailyActivityServices().load(idDailyActivity);
                companyServiceDto = ServicesLocator.getCompanyServiceServices().load(idCompany);
                serviceTypeDto = ServicesLocator.getServiceTypeServices().load(idServiceType);

                List<DailyActivityDto> ListDailyActivityInsert = new LinkedList<>();
                List<CompanyServiceDto> ListCompanyInsert = new LinkedList<>();
                List<ServiceTypeDto> ListServiceTypeInsert = new LinkedList<>();

                ListDailyActivityInsert.add(dailyActivityDto);
                ListCompanyInsert.add(companyServiceDto);
                ListServiceTypeInsert.add(serviceTypeDto);

                idContainerDailyActivity.add(idDailyActivity);
                idContainerCompany.add(idCompany);
                idContainerServiceType.add(idServiceType);

                CallableStatement callableStatementPr = connection.prepareCall("{? = call tpp.n_province_load_by_id(?)}");
                callableStatementPr.registerOutParameter(1, Types.REF_CURSOR);
                int idProvince = resultSet.getInt("id_province");
                callableStatementPr.setInt(2, idProvince);
                callableStatementPr.execute();
                ResultSet resultSetPr = (ResultSet) callableStatementPr.getObject(1);
                resultSetPr.next();
                ProvinceDto provinceDto = new ProvinceDto(idProvince, resultSetPr.getString("name"));
                callableStatementPr.close();

                contractServiceDto = new ContractServiceDto(
                        idContractService,
                        contractDto.getStartDate(),
                        contractDto.getFinishDate(),
                        contractDto.getConciliationDate(),
                        contractDto.getDescription(),
                        contractDto.getContractTypeDto(),
                        resultSet.getFloat("pax_cost"),
                        provinceDto);

                contractServiceDto.setDailyActivities(ListDailyActivityInsert);
                contractServiceDto.setCompaniesService(ListCompanyInsert);
                contractServiceDto.setServiceType(ListServiceTypeInsert);
                ListContractService.add(contractServiceDto);
            } else {//inserto para el mismo id los tipos de hab, planes alim y modalidades diferentes
                if (!idContainerDailyActivity.contains(idDailyActivity)) {
                    CallableStatement callableStatementDailyAct = connection.prepareCall("{? = call tpp.n_daily_activity_load_by_id(?)}");
                    callableStatementDailyAct.registerOutParameter(1, Types.REF_CURSOR);
                    callableStatementDailyAct.setInt(2, idDailyActivity);
                    callableStatementDailyAct.execute();
                    ResultSet resultSetDailyAct = (ResultSet) callableStatementDailyAct.getObject(1);
                    resultSetDailyAct.next();
                    dailyActivityDto = new DailyActivityDto(idDailyActivity, resultSetDailyAct.getString("name"));
                    contractServiceDto.getDailyActivities().add(dailyActivityDto);
                    idContainerDailyActivity.add(idDailyActivity);
                    callableStatementDailyAct.close();
                }

                if (!idContainerCompany.contains(idCompany)) {
                    CallableStatement callableStatementCompany = connection.prepareCall("{? = call tpp.n_company_service_load_by_id(?)}");
                    callableStatementCompany.registerOutParameter(1, Types.REF_CURSOR);
                    callableStatementCompany.setInt(2, idCompany);
                    callableStatementCompany.execute();
                    ResultSet resultSetCompany = (ResultSet) callableStatementCompany.getObject(1);
                    resultSetCompany.next();
                    companyServiceDto = new CompanyServiceDto(idCompany, resultSetCompany.getString("name"));
                    contractServiceDto.getCompaniesService().add(companyServiceDto);
                    idContainerCompany.add(idCompany);
                    callableStatementCompany.close();
                }

                if (!idContainerServiceType.contains(idServiceType)) {
                    CallableStatement callableStatementServiceType = connection.prepareCall("{? = call tpp.n_service_type_load_by_id(?)}");
                    callableStatementServiceType.registerOutParameter(1, Types.REF_CURSOR);
                    callableStatementServiceType.setInt(2, idServiceType);
                    callableStatementServiceType.execute();
                    ResultSet resultSetServiceType = (ResultSet) callableStatementServiceType.getObject(1);
                    resultSetServiceType.next();
                    serviceTypeDto = new ServiceTypeDto(idServiceType, resultSetServiceType.getString("name"));
                    contractServiceDto.getServiceType().add(serviceTypeDto);
                    idContainerServiceType.add(idServiceType);
                    callableStatementServiceType.close();
                }
            }
        }

        callableStatement.close();
        connection.close();
        return ListContractService;
    }

    @Override
    public void insert(ContractServiceDto dto) throws SQLException {
        ContractDto contractDto = new ContractDto(0,
                dto.getContractTypeDto(),
                dto.getStartDate(),
                dto.getFinishDate(),
                dto.getConciliationDate(),
                dto.getDescription());
        ServicesLocator.getContractServices().insert(contractDto);
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.contract_service_insert(?,?,?)}");
        callableStatement.setDouble(1, dto.getPaxCost());
        callableStatement.setInt(2, dto.getIdProvince().getId());
        callableStatement.setInt(3, contractDto.getId());
        callableStatement.execute();
        dto.setId(contractDto.getId());

        List<DailyActivityDto> dailyActivityDtoList = dto.getDailyActivities();
        ListIterator<DailyActivityDto> listIterator = dailyActivityDtoList.listIterator();

        callableStatement = connection.prepareCall("{ call tpp.r_contract_service_n_daily_activity_insert(?,?) }");

        while (listIterator.hasNext()){
            DailyActivityDto currentDaily = listIterator.next();
            callableStatement.setInt(1, dto.getId());
            callableStatement.setInt(1, currentDaily.getId());
        }

        callableStatement.close();
        connection.close();
    }

    @Override
    public void update(ContractServiceDto dto) throws SQLException {
        ContractDto contractDto = new ContractDto(dto.getId(),
                dto.getContractTypeDto(),
                dto.getStartDate(),
                dto.getFinishDate(),
                dto.getConciliationDate(),
                dto.getDescription());
        ServicesLocator.getContractServices().update(contractDto);
        Connection connection = ServicesLocator.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call tpp.contract_service_update(?,?,?)}");
        callableStatement.setDouble(1, dto.getPaxCost());
        callableStatement.setInt(2, dto.getIdProvince().getId());
        callableStatement.setInt(3, dto.getId());
        callableStatement.execute();

        callableStatement.close();
        connection.close();
    }

    @Override
    public void delete(int id) throws SQLException {
        ServicesLocator.getContractServices().delete(id);
    }
}

