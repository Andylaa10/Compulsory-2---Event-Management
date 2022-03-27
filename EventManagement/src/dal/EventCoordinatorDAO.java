package dal;

import be.Admin;
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

    public EventCoordinator Login(String username, String password) throws SQLServerException {
        String sql = "SELECT * FROM Login WHERE username =? AND password =?;";
        try(Connection connection = connector.getConnection()){
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            if (st.execute()) {
                return new EventCoordinator(username, password);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    /**
     * Method used for testing the DAO class
     */
    public static void main(String[] args) throws IOException, SQLException {
        EventCoordinatorDAO eventCoordinatorDAO = new EventCoordinatorDAO();
        //eventCoordinatorDAO.addCustomerToEvent(1, 4);
        List<Customer> customerList = eventCoordinatorDAO.getCustomersOnEvent(4);
        System.out.println(customerList);
    }
}