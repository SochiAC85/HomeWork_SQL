package homework.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDBConnectionManager {

    ResultSet execute(String query) throws SQLException;
    void close() throws SQLException;
    ResultSet executeWithData(String sqlRequest) throws SQLException;
    int executeUpdate(String sqlRequest) throws SQLException;

}
