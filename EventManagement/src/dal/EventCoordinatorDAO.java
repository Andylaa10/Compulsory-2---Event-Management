package dal;

import be.Customer;
import be.EventCoordinator;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.db.DatabaseConnector;
import java.io.IOException;
import java.sql.*;
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allCoordinators;
    }

    /**
     * Creates an eventCoordinator, by inserting a giving username, password and a boolean saying if this is an admin
     * @param username
     * @param password
     * @param isAdmin
     * @return EventCoordinator
     * @throws SQLException
     */
    public EventCoordinator createCoordinator (String username, String password, boolean isAdmin) throws SQLException {
        try (Connection connection = connector.getConnection()) {
            String sql = "INSERT INTO Login (username, password, isAdmin) values (?,?,?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.setBoolean(3, isAdmin);
                preparedStatement.execute();

                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                int id = 0;
                if (resultSet.next()) {
                    id = resultSet.getInt(1);
                }

                EventCoordinator coordinator = new EventCoordinator(id, username, password, isAdmin);
                return coordinator;

            }

        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    /**
     * @param id
     * @param isAdmin
     * Deletes a coordinator by taken the id and if isAdmin is false
     */
    public void deleteCoordinator(int id, boolean isAdmin) {
        try (Connection connection = connector.getConnection()) {
            String sql = "DELETE FROM Login WHERE LoginID =? AND WHERE isAdmin =?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setBoolean(2,isAdmin);
            if (preparedStatement.executeUpdate() != 1) {
                throw new Exception("Could not delete coordinator");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param eventCoordinator
     * Edits a coordinator
     */
    public void editCoordinator(EventCoordinator eventCoordinator) {
        try (Connection connection = connector.getConnection()) {
            String sql = "UPDATE Login SET username=?, password=?, isAdmin=? WHERE LoginID=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, eventCoordinator.getUsername());
            preparedStatement.setString(2, eventCoordinator.getPassword());
            preparedStatement.setBoolean(3, eventCoordinator.isAdmin());
            preparedStatement.setInt(4, eventCoordinator.getId());
            if (preparedStatement.executeUpdate() != 1) {
                throw new Exception("Could not edit coordinator");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param customerId
     * @param eventId
     * Adds a selected customer to an event
     */
    public void addCustomerToEvent(int customerId, int eventId){
        String sql = "INSERT INTO CusOnEvent (CustomerId, EventId) VALUES (?,?);";
        try (Connection con = connector.getConnection();
             PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setInt(1, customerId);
            st.setInt(2, eventId);
            st.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * @param customerId
     * @param eventId
     * Deletes a customer from event
     */
    public void deleteFromEvent(int customerId, int eventId){
        String sql = "DELETE FROM CusOnEvent WHERE CustomerId = ? AND EventId = ?;";
        try (Connection connection = connector.getConnection()) {
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, customerId);
            st.setInt(2, eventId);
            st.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    /**
     * Gets a list of customer on event
     * @param eventId
     * @return List of customers
     * @throws SQLException
     */
    public List<Customer> getCustomersOnEvent(int eventId) throws SQLException {
        ArrayList<Customer> allCustomerOnEvent = new ArrayList<>();

        try (Connection connection = connector.getConnection()) {
            String sql = "SELECT * FROM Customer INNER JOIN CusOnEvent ON CusOnEvent.customerId = Customer.CustomerID WHERE CusOnEvent.eventId = ?;";
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, eventId);
            st.execute();
            ResultSet rs = st.getResultSet();
            while(rs.next()) {
                int id = rs.getInt("CustomerID");
                String firstName = rs.getString("Fname");
                String lastName = rs.getString("Lname");
                String phoneNumber = rs.getString("PhoneNumber");
                String email = rs.getString("Email");
                allCustomerOnEvent.add(new Customer(id, firstName, lastName, phoneNumber, email));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return allCustomerOnEvent;
    }


    /**
     * Method used for testing
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

    }
}