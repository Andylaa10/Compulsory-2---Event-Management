package bll;

import be.Event;
import bll.helpers.EventSearcher;
import dal.EventDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EventManager {

    private EventDAO eventDAO;
    private EventSearcher eventSearcher;

    /**
     * Constructor
     * @throws IOException
     */
    public EventManager() throws IOException {
        eventDAO = new EventDAO();
        eventSearcher = new EventSearcher();
    }

    /**
     * Gets the list of event using the getEvents method in eventDAO.
     * @return a list of event
     */
    public List<Event> getEvents() {
        List<Event> allEvents = eventDAO.getEvents();
        return allEvents;
    }

    /**
     * Gets the list of event assigned to a coordinator using the getEvents method in eventDAO.
     * @return a list of event
     */
    public List<Event> getEventsCoordinator(int loginID) {
        List<Event> allEventsCoordinator = eventDAO.getEventsCoordinator(loginID);
        return allEventsCoordinator;
    }

    /**
     * Creates an event using the createEvent method in eventDAO
     * @param eventName
     * @param eventDate
     * @param eventTime
     * @param eventTimeEnd
     * @param eventLocation
     * @param eventInfo
     * @param eventPrice
     * @param eventMinimum
     * @param eventMaximum
     * @throws SQLException
     */
    public void createEvent (String eventName, String eventDate, String eventTime, String eventTimeEnd,
                             String eventLocation, String eventInfo, String eventPrice,
                             int eventMinimum, int eventMaximum) throws SQLException {
        eventDAO.createEvent(eventName, eventDate, eventTime, eventTimeEnd, eventLocation, eventInfo, eventPrice, eventMinimum, eventMaximum);
    }

    /**
     * Deletes an event using the deleteEvent methods in eventDAO
     * @param eventId
     */
    public void deleteEvent(int eventId) {
        eventDAO.deleteEvent(eventId);
    }

    /**
     * Deletes a coordinator from event
     * @param eventId
     * @param loginId
     */
    public void deleteCoordinatorFromEvent(int eventId, int loginId){
        eventDAO.deleteCoordinatorFromEvent(eventId, loginId);
    }

    /**
     * Edits an event using the editEvent from eventDAO
     * @param event
     */
    public void editEvent(Event event) {
        eventDAO.editEvent(event);
    }

    /**
     * Searching through a list of eventCoordinators.
     * @param query
     * @return
     */
    public List<Event> searchEvent(String query){
        List<Event> allEvents = getEvents();
        List<Event> searchResult = eventSearcher.search(allEvents, query);
        return searchResult;
    }

    /**
     * Searching through a list of assigned eventCoordinators.
     * @param query
     * @param loginID
     * @return
     */
    public List<Event> searchAssignedEvent(String query, int loginID) {
        List<Event> allEvents = getEventsCoordinator(loginID);
        List<Event> searchResult = eventSearcher.search(allEvents, query);
        return searchResult;
    }

    /**
     * Creates an assigned an event to a coordinator automatically
     * @param eventName
     * @param eventDate
     * @param eventTime
     * @param eventTimeEnd
     * @param eventLocation
     * @param eventInfo
     * @param eventPrice
     * @param eventMinimum
     * @param eventMaximum
     * @param LoginID
     * @throws SQLException
     */
    public void createAndAssignCoordinator(String eventName, String eventDate, String eventTime, String eventTimeEnd,
                                            String eventLocation, String eventInfo, String eventPrice,
                                            int eventMinimum, int eventMaximum, int LoginID) throws SQLException {
        eventDAO.createAndAssignCoordinator(eventName, eventDate, eventTime, eventTimeEnd, eventLocation, eventInfo, eventPrice, eventMinimum, eventMaximum, LoginID);
    }

}
