package gui.model;

import be.Event;
import bll.EventManager;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class EventModel {

    private EventManager eventManager;

    /**
     * Constructor
     * @throws IOException
     */
    public EventModel() throws IOException {
        eventManager = new EventManager();
    }

    /**
     * Gets the list of event using the getEvents method in eventManagerDAO.
     * @return a list of event
     */
    public List<Event> getEvents() {
        List<Event> allEvents = eventManager.getEvents();
        return allEvents;
    }

    /**
     * Creates an event using the createEvent method in eventManagerDAO
     * @param eventName
     * @param eventDate
     * @param eventTime
     * @param eventLocation
     * @param eventInfo
     * @param eventPrice
     * @throws SQLException
     */
    public void createEvent (String eventName, String eventDate, String eventTime, String eventLocation, String eventInfo, String eventPrice) throws SQLException {
        eventManager.createEvent(eventName, eventDate, eventTime, eventLocation, eventInfo, eventPrice);
    }

    /**
     * Deletes a event using the deleteEvent methods in eventManagerDAO
     * @param id
     */
    public void deleteEvent(int id) {
        eventManager.deleteEvent(id);
    }

    /**
     * Edits an event using the editEvent from eventManagerDAO
     * @param event
     */
    public void editEvent(Event event) {
        eventManager.editEvent(event);
    }

}
