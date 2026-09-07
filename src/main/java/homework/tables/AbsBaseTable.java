package homework.tables;

import homework.db.IDBConnectionManager;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbsBaseTable {

    protected final IDBConnectionManager idbConnectionManager;
    protected final String tableName;
    protected final Map<String, String> columns;

    public AbsBaseTable(String tableName, IDBConnectionManager idbConnectionManager) {
        this.idbConnectionManager = idbConnectionManager;
        this.tableName = tableName;
        this.columns = new HashMap<>();

    }

    public void create() {
        String column = convertMapColumnsToString();

        if (column.isEmpty()) {
            throw new IllegalStateException();
        }

        String sqlRequest = String.format("CREATE TABLE IF NOT EXISTS %s (%s)", this.tableName, column);

        try {
            idbConnectionManager.executeUpdate(sqlRequest);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public void delete() throws SQLException {
        String sql = String.format("DROP TABLE IF EXISTS %s", tableName);
        idbConnectionManager.execute(sql);
    }

    protected String convertMapColumnsToString() {
        if (columns.isEmpty()) {
            return "";
        }
        return columns.entrySet().stream()
                .map(entry -> entry.getKey() + " " + entry.getValue())
                .collect(Collectors.joining(", "));
    }

    protected Map<String, String> getColumns() {
        return columns;
    }

}
