package gui.model;

import be.Event;
import bll.EventManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EventModel {

    private ObservableList<Event> eventsToBeViewed;
    private EventManager eventManager;

    /**
     * Constructor
     * @throws IOException
     */
    public EventModel() throws IOException {
        eventManager = new EventManager();
        eventsToBeViewed = FXCollections.observableArrayList();
        eventsToBeViewed.addAll(eventManager.getEvents());
    }

    /**
     * Gets the list of event using the getEvents method in eventManager.
     * @return a list of event
     */
    public List<Event> getEvents() {
        List<Event> allEvents = eventManager.getEvents();
        return allEvents;
    }

    /**
     * Gets the list of event assigned to a coordinator using the getEvents method in eventManager.
     * @return a list of event
     */
    public List<Event> getEventsCoordinator(int loginID) {
        List<Event> allEventsCoordinator = eventManager.getEventsCoordinator(loginID);
        return allEventsCoordinator;
    }

    /**
     * Creates an event using the createEvent method in eventManager
     * @param eventName
     * @param eventDate
     * @param eventTime
     * @param eventLocation
     * @param eventInfo
     * @param eventPrice
     * @throws SQLException
     */
    public void createEvent (String eventName, String eventDate, String eventTime, String eventTimeEnd, String eventLocation, String eventInfo, String eventPrice, int eventMinimum, int eventMaximum) throws SQLException {
        eventManager.createEvent(eventName, eventDate, eventTime, eventTimeEnd, eventLocation, eventInfo, eventPrice, eventMinimum, eventMaximum);
    }

    /**
     * Deletes a event using the deleteEvent methods in eventManager
     * @param id
     */
    public void deleteEvent(int id) {
        eventManager.deleteEvent(id);
    }

    /**
     * Edits an event using the editEvent from eventManager
     * @param event
     */
    public void editEvent(Event event) {
        eventManager.editEvent(event);
    }

    /**
     * Search method for the model, searches for events by text.
     * @param query
     * @return searchResults
     * @throws Exception
     */
    public List<Event> searchEvent(String query){
        List<Event> searchResults = null;

        searchResults = eventManager.searchEvent(query);
        eventsToBeViewed.clear();
        eventsToBeViewed.addAll(searchResults);

        return searchResults;
    }

    public void createAndAssignCoordinator (String eventName, String eventDate, String eventTime, String eventTimeEnd, String eventLocation, String eventInfo, String eventPrice, int eventMinimum, int eventMaximum, int LoginID) throws SQLException {
        eventManager.createAndAssignCoordinator(eventName, eventDate, eventTime, eventTimeEnd, eventLocation, eventInfo, eventPrice, eventMinimum, eventMaximum, LoginID);
    }
}
