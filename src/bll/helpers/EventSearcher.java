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
            } else{
                throw new IllegalArgumentException("Nothing is found");
            }
        }
        return searchResult;
    }

    /**
     * Compare event title
     * @param query
     * @param event
     * @return
     */
    private boolean compareToEventTitle(String query, Event event) {
        return event.getEventName().toLowerCase().contains(query.toLowerCase());
    }

    /**
     * Compare event date
     * @param query
     * @param event
     * @return
     */
    private boolean compareToEventDate(String query, Event event) {
        return event.getEventDate().contains(query);
    }

    /**
     * Compare event info
     * @param query
     * @param event
     * @return
     */
    private boolean compareToEventInfo(String query, Event event) {
        return event.getEventInfo().toLowerCase().contains(query.toLowerCase());
    }

    /**
     * Compare event time
     * @param query
     * @param event
     * @return
     */
    private boolean compareToEventTime(String query, Event event) {
        return event.getEventTime().contains(query);
    }

    /**
     * Compare event location
     * @param query
     * @param event
     * @return
     */
    private boolean compareToEventLocation(String query, Event event) {
        return event.getEventLocation().toLowerCase().contains(query.toLowerCase());
    }

}
