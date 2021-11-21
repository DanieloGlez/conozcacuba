package service.fun;

import dto.fun.ComboBoxDto;
import service.Services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ServicesCombo implements Services<ComboBoxDto> {


    //Este método ejecuta una función creada en el postgres
    public LinkedList<String> getTablesNames() throws SQLException, ClassNotFoundException {
        LinkedList<String> tableNameList = new LinkedList<String>();
        String function = "{?= call get_table_name}"; /*se establece con el primer ? que la función tiene valor de retorno, con el segundo ? se está especificando que es un parámetro que será pasado */
        java.sql.Connection connection = service.ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement preparedFunction = connection.prepareCall(function);
        preparedFunction.registerOutParameter(1, java.sql.Types.OTHER); /*Se especifica que el primer ? es de tipo varchar, es decir que el valor de retorno es un varchar */
        preparedFunction.execute();
        ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
        while (resultSet.next()){
            tableNameList.add(resultSet.getString(1));
        }
        resultSet.close();
        preparedFunction.close();
        connection.close();

        return tableNameList;
    }

    @Override
    public ComboBoxDto load(int id) throws SQLException {
        return null;
    }

    @Override
    public List<ComboBoxDto> loadAll() throws SQLException {
        return null;

    }

    @Override
    public void insert(ComboBoxDto dto) throws SQLException {

    }

    @Override
    public void update(ComboBoxDto dto) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public String getGenericType() {
        return null;
    }
}
