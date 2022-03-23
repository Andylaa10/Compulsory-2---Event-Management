package bll;

import be.EventCoordinator;
import dal.EventCoordinatorDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EventCoordinatorManager {

    private EventCoordinatorDAO eventCoordinatorDAO;

    /**
     * Constructor
     * @throws IOException
     */
    public EventCoordinatorManager() throws IOException {
        eventCoordinatorDAO = new EventCoordinatorDAO();
    }



    /**
     * Gets the customer on event using getCustomersOnEvent from eventCoordinatorDAO
     * @param eventId
     * @return a list of customer on event or an empty list of customer on event
     * @throws SQLException
     */
    public void getCustomersOnEvent(int eventId) throws SQLException {
        eventCoordinatorDAO.getCustomersOnEvent(eventId);
    }

    /**
     * Adds a customer to event using addCustomerToEvent method in eventCoordinatorDAO
     * @param customerId
     * @param eventId
     */
    public void addCustomerToEvent(int customerId, int eventId){
        eventCoordinatorDAO.addCustomerToEvent(customerId, eventId);
    }

    /**
     * Deletes customer from event using deleteFromEvent from eventCoordinatorDAO
     * @param customerId
     * @param eventId
     */
    public void deleteFromEvent(int customerId, int eventId){
        eventCoordinatorDAO.deleteFromEvent(customerId, eventId);
    }
}
