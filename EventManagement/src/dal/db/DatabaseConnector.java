package dal.db;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnector {

    private final SQLServerDataSource dataSource;

    private static final String PROP_FILE = "EventManagement/config.properties";

    /**
     * Constructor for the database, plugging in the details of our SQL server / login
     */
    public DatabaseConnector() throws IOException {

        Properties settings = new Properties();
        settings.load(new FileInputStream(PROP_FILE));

        dataSource = new SQLServerDataSource();
        dataSource.setServerName(settings.getProperty("DatabaseServer"));
        dataSource.setDatabaseName(settings.getProperty("Database"));
        dataSource.setUser(settings.getProperty("Username"));
        dataSource.setPassword(settings.getProperty("Password"));
        dataSource.setPortNumber(Integer.parseInt(settings.getProperty("PortNumber")));
    }

    /**
     * connects to the database using .getConnection();
     */
    public Connection getConnection() throws SQLServerException {
        return dataSource.getConnection();
    }

    //Check if there is a connection
    public static void main(String[] args) throws SQLException, IOException {
        DatabaseConnector databaseConnector = new DatabaseConnector();

        try (Connection connection = databaseConnector.getConnection()) {
            System.out.println("Is it open? " + !connection.isClosed());
        }
    }
}
