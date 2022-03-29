package bll.helpers;

import be.Event;

import java.util.ArrayList;
import java.util.List;

public class EventSearcher {

    /**
     * Method for searching an event with the search button.
     *
     * @param searchBase
     * @param query
     * @return
     */
    public List<Event> search(List<Event> searchBase, String query) {
        List<Event> searchResult = new ArrayList<>();

        for (Event event : searchBase) {
            if (compareToEventTitle(query, event) | compareToEventDate(query, event) | compareToEventInfo(query, event) | compareToEventTime(query, event) | compareToEventLocation(query, event)) {
                searchResult.add(event);
            }
        }
        return searchResult;
    }


    private boolean compareToEventTitle(String query, Event event) {
        return event.getEventName().toLowerCase().contains(query.toLowerCase());
    }

    private boolean compareToEventDate(String query, Event event) {
        return event.getEventDate().contains(query);
    }

    private boolean compareToEventInfo(String query, Event event) {
        return event.getEventInfo().toLowerCase().contains(query.toLowerCase());
    }

    private boolean compareToEventTime(String query, Event event) {
        return event.getEventTime().contains(query);
    }

    private boolean compareToEventLocation(String query, Event event) {
        return event.getEventLocation().toLowerCase().contains(query.toLowerCase());
    }

}
