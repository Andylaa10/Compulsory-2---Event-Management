package dal.db;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnector {

    private SQLServerDataSource dataSource;

    /**
     * Constructor for the database, plugging in the details of our SQL server / login
     */
    public DatabaseConnector() {
        dataSource = new SQLServerDataSource();
        dataSource.setServerName("10.176.111.31");
        dataSource.setDatabaseName("EventManagement2022");
        dataSource.setUser("CSe21A_5");
        dataSource.setPassword("123And123");
        dataSource.setPortNumber(1433);
    }

    /**
     * connects to the database using .getConnection();
     *
     * @return
     * @throws SQLServerException
     */
    public Connection getConnection() throws SQLServerException {
        return dataSource.getConnection();
    }

    //Check if there is a connection
    public static void main(String[] args) throws SQLException {
        DatabaseConnector databaseConnector = new DatabaseConnector();

        try (Connection connection = databaseConnector.getConnection()) {

            System.out.println("Is it open? " + !connection.isClosed());

        }
    }
}
