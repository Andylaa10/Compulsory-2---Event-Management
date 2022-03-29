package bll;

import be.Event;
import bll.helpers.EventSearcher;
import dal.EventDAO;
import java.io.IOException;
import java.sql.Date;
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
     * Creates an event using the createEvent method in eventDAO
     * @param eventName
     * @param eventDate
     * @param eventTime
     * @param eventLocation
     * @param eventInfo
     * @param eventPrice
     * @throws SQLException
     */
    public void createEvent (String eventName, String eventDate, String eventTime, String eventLocation, String eventInfo, String eventPrice) throws SQLException {
        eventDAO.createEvent(eventName, eventDate, eventTime, eventLocation, eventInfo, eventPrice);
    }

    /**
     * Deletes a event using the deleteEvent methods in eventDAO
     * @param id
     */
    public void deleteEvent(int id) {
        eventDAO.deleteEvent(id);
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

}
