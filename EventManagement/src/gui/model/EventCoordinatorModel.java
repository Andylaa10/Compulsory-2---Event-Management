package gui.model;

import be.EventCoordinator;
import bll.EventCoordinatorManager;
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
     * Gets the list of coordinator using the getCoordinator method in eventCoordinatorManagerDAO.
     * @return a list of coordinator
     */
    public List<EventCoordinator> getCoordinator() throws SQLException {
        List<EventCoordinator> allEventCoordinators = eventCoordinatorManager.getCoordinator();
        return allEventCoordinators;
    }

    /**
     * Creates a coordinator using the createCoordinator method in eventCoordinatorManagerDAO
     * @param username
     * @param password
     * @param isAdmin
     * @throws SQLException
     */
    public void createCoordinator(String username, String password, boolean isAdmin) throws SQLException {
        eventCoordinatorManager.createCoordinator(username, password, isAdmin);
    }

    /**
     * Deletes a coordinator using the deleteCoordinator methods in eventCoordinatorManagerDAO
     * @param id
     * @param isAdmin
     */
    public void deleteCoordinator(int id, boolean isAdmin) {
        eventCoordinatorManager.deleteCoordinator(id, isAdmin);
    }

    /**
     * Edits a coordinator using the editCoordinator from eventCoordinatorManagerDAO
     * @param eventCoordinator
     */
    public void editCoordinator(EventCoordinator eventCoordinator) {
        eventCoordinatorManager.editCoordinator(eventCoordinator);
    }

    /**
     * Gets the customer on event using getCustomersOnEvent from eventCoordinatorManagerDAO
     * @param eventId
     * @return a list of customer on event or an empty list of customer on event
     * @throws SQLException
     */
    public void getCustomersOnEvent(int eventId) throws SQLException {
        eventCoordinatorManager.getCustomersOnEvent(eventId);
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

}
