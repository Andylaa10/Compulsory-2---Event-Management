package gui.model;

import be.Event;
import bll.EventManager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EventModel {

    private EventManager eventManager;

    public EventModel() throws IOException {
        eventManager = new EventManager();
    }

    public List<Event> getEvents() {
        List<Event> allEvents = eventManager.getEvents();
        return allEvents;
    }

    public void createEvent (String eventName, String eventDate, String eventTime, String eventLocation, String eventInfo) throws SQLException {
        eventManager.createEvent(eventName, eventDate, eventTime, eventLocation, eventInfo);
    }

    public void deleteEvent(int id) {
        eventManager.deleteEvent(id);
    }

    public void editEvent(Event event) {
        eventManager.editEvent(event);
    }

}
