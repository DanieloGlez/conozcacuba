package service;

import dto.*;
import dto.nom.*;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

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

                contractServiceDto = new ContractServiceDto(
                        idContractService,
                        contractDto.getStartDate(),
                        contractDto.getFinishDate(),
                        contractDto.getConciliationDate(),
                        contractDto.getDescription(),
                        contractDto.getContractTypeDto(),
                        resultSet.getFloat("pax_cost"),
                        ServicesLocator.getProvinceServices().load(resultSet.getInt("id_province")));

                contractServiceDto.setDailyActivities(ListDailyActivityInsert);
                contractServiceDto.setCompaniesService(ListCompanyInsert);
                contractServiceDto.setServiceType(ListServiceTypeInsert);
                ListContractService.add(contractServiceDto);
            } else {//inserto para el mismo id los tipos de hab, planes alim y modalidades diferentes
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

