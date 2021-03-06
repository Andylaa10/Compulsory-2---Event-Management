package gui.model;


import be.Customer;
import be.EventCoordinator;
import bll.EventCoordinatorManager;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EventCoordinatorModel {

    private EventCoordinatorManager eventCoordinatorManager;

    /**
     * Constructor
     * @throws IOException
     */
    public EventCoordinatorModel() throws IOException {
        eventCoordinatorManager = new EventCoordinatorManager();
    }

    /**
     * Gets the customer on event using getCustomersOnEvent from eventCoordinatorManagerDAO
     * @param eventId
     * @return a list of customer on event or an empty list of customer on event
     * @throws SQLException
     */
    public List<Customer> getCustomersOnEvent(int eventId) throws SQLException {
        return eventCoordinatorManager.getCustomersOnEvent(eventId);
    }

    /**
     * Adds a customer to event using addCustomerToEvent method in eventCoordinatorManagerDAO
     * @param customerId
     * @param eventId
     */
    public void addCustomerToEvent(int customerId, int eventId){
        eventCoordinatorManager.addCustomerToEvent(customerId, eventId);
    }

    /**
     * Deletes customer from event using deleteFromEvent from eventCoordinatorManagerDAO
     * @param customerId
     * @param eventId
     */
    public void deleteFromEvent(int customerId, int eventId){
        eventCoordinatorManager.deleteFromEvent(customerId, eventId);
    }

    /**
     * Gets the coordinator username and the password using login from eventCoordinatorManager
     * @param username
     * @param password
     * @return
     * @throws SQLServerException
     */
    public EventCoordinator login(String username, String password) throws SQLServerException {
        return eventCoordinatorManager.login(username, password);
    }
}
