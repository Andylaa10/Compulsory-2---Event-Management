package be;

import java.sql.Date;

public class Event {
    private int id;
    private String eventName;
    private String eventDate;
    private String eventTime;
    private String eventLocation;
    private String eventInfo;

    /**
     * Constructor
     */
    public Event() {
    }

    /**
     * @param id
     * @param eventName
     * @param eventDate
     * @param eventTime
     * @param eventLocation
     * @param eventInfo
     * Constructor with id, eventName, eventDate, eventTime, eventLocation and eventInfo
     */
    public Event(int id, String eventName, String eventDate, String eventTime, String eventLocation, String eventInfo) {
        this.id = id;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventLocation = eventLocation;
        this.eventInfo = eventInfo;
    }

    /**
     * Gets the id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     * Sets the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the eventName
     * @return eventName
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * @param eventName
     * Set the eventName
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * Gets the eventDate
     * @return eventDate
     */
    public String getEventDate() {
        return eventDate;
    }

    /**
     * @param eventDate
     * Sets the eventDate
     */
    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    /**
     * Gets the eventTime
     * @return eventTime
     */
    public String getEventTime() {
        return eventTime;
    }

    /**
     * @param eventTime
     * Sets the eventTime
     */
    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    /**
     * Gets the eventLocation
     * @return eventLocation
     */
    public String getEventLocation() {
        return eventLocation;
    }

    /**
     * @param eventLocation
     * Sets the eventLocation
     */
    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    /**
     * Gets the eventInfo
     * @return eventInfo
     */
    public String getEventInfo() {
        return eventInfo;
    }

    /**
     * @param eventInfo
     * Sets the eventInfo
     */
    public void setEventInfo(String eventInfo) {
        this.eventInfo = eventInfo;
    }
}
