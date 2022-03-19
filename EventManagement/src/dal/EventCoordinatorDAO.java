package dal;

import be.Customer;
import be.EventCoordinator;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.db.DatabaseConnector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EventCoordinatorDAO {

    private final DatabaseConnector connector;

    public EventCoordinatorDAO() throws IOException {
        connector = new DatabaseConnector();
    }

    /**
     * Making a coordinator list, connecting to the database and adding the results to our ArrayList.
     * @return a list of coordinators or an empty list of coordinators
     */
    public List<EventCoordinator> getCoordinator() {

        ArrayList<EventCoordinator> allCoordinators = new ArrayList<>();

        try (Connection connection = connector.getConnection()) {

            String sql = "SELECT * FROM Login;";

            Statement statement = connection.createStatement();

            if (statement.execute(sql)) {
                ResultSet resultset = statement.getResultSet();
                while (resultset.next()) {
                    int loginID = resultset.getInt("LoginID");
                    String username = resultset.getString("username");
                    String password = resultset.getString("password");
                    boolean isAdmin = resultset.getBoolean("isAdmin");


                    EventCoordinator coordinator = new EventCoordinator(loginID, username, password, isAdmin);
                    allCoordinators.add(coordinator);
                }
            }
        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allCoordinators;
    }
}