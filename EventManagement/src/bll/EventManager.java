package bll;

import be.Event;
import dal.EventDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EventManager {

    private EventDAO eventDAO;

    public EventManager() throws IOException {
        eventDAO = new EventDAO();
    }

    public List<Event> getEvents() {
        List<Event> allEvents = eventDAO.getEvents();
        return allEvents;
    }

    public void createEvent (String eventName, String eventDate, String eventTime, String eventLocation, String eventInfo) throws SQLException {
        eventDAO.createEvent(eventName, eventDate, eventTime, eventLocation, eventInfo);
    }

    public void deleteEvent(int id) {
        eventDAO.deleteEvent(id);
    }

    public void editEvent(Event event) {
        eventDAO.editEvent(event);
    }

}
