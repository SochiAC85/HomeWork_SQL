package homework.db;

import homework.settings.DbSettingsReader;

import java.sql.*;
import java.util.Map;

public class SqlConnectionManager implements IDBConnectionManager{

    private static Connection connection = null;
    private static Statement statement = null;

    public SqlConnectionManager() throws SQLException {
        DbSettingsReader dbSettingsReader = new DbSettingsReader();
        Map<String, String> settings = dbSettingsReader.getSettings();

        if(connection == null) {
            connection = DriverManager.getConnection(settings.get("url"),settings.get("user"),settings.get("password"));
        }
        if(statement == null) {
            statement = connection.createStatement();
        }
    }
    public ResultSet execute(String sqlRequest) throws SQLException {
        boolean hasResults = statement.execute(sqlRequest);
        if (hasResults) {
            return statement.getResultSet();
        }
        return null;
    }

    public ResultSet executeWithData(String sqlRequest) throws SQLException {
        return statement.executeQuery(sqlRequest);
    }

    public int executeUpdate(String sqlRequest) throws SQLException {
        return statement.executeUpdate(sqlRequest);
    }


    public void close() throws SQLException {
        if(connection != null){
            connection.close();
        }
        if (statement != null) {
            statement.close();
        }

    }



}
