package homework.factory;

import homework.db.IDBConnectionManager;
import homework.db.SqlConnectionManager;
import homework.exceptions.DbNotSupported;

import java.sql.SQLException;

public class DBFactory {
    public IDBConnectionManager getConnectionManager (String dbType) throws SQLException {
        switch (dbType){
            case "SQL_DB" ->{
                return new SqlConnectionManager();
            }
        }
        throw new DbNotSupported(dbType);
    }
}
