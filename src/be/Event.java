package be;

public class Event {
    private int id;
    private String eventName;
    private String eventDate;
    private String eventTime;
    private String eventTimeEnd;
    private String eventLocation;
    private String eventInfo;
    private String eventPrice;
    private int eventMinimum;
    private int eventMaximum;
    private int loginID;
    private int currentCustomersOnEvent;

    /**
     * Constructor
     */
    public Event() {
    }

    /**
     * Constructor with how many participants
     * @param currentCustomersOnEvent
     */
    public Event(int currentCustomersOnEvent) {
        this.currentCustomersOnEvent = currentCustomersOnEvent;
    }

    /**
     * Constructor with id, eventName, eventDate, eventTime, eventLocation, eventInfo, eventPrice, eventMinimum and eventMaximum
     * @param id
     * @param eventName
     * @param eventDate
     * @param eventTime
     * @param eventTimeEnd
     * @param eventLocation
     * @param eventInfo
     * @param eventPrice
     * @param eventMinimum
     * @param eventMaximum
     */
    public Event(int id, String eventName, String eventDate, String eventTime, String eventTimeEnd, String eventLocation,
                 String eventInfo, String eventPrice, int eventMinimum, int eventMaximum) {
        this.id = id;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventTimeEnd = eventTimeEnd;
        this.eventLocation = eventLocation;
        this.eventInfo = eventInfo;
        this.eventPrice = eventPrice;
        this.eventMinimum = eventMinimum;
        this.eventMaximum = eventMaximum;
    }

    /**
     * Constructor with id, eventName, eventDate, eventTime, eventLocation, eventInfo, eventPrice, eventMinimum, eventMaximum and loginID
     * @param eventID
     * @param eventName
     * @param eventDate
     * @param eventTime
     * @param eventTimeEnd
     * @param eventLocation
     * @param eventInfo
     * @param eventPrice
     * @param eventMinimum
     * @param eventMaximum
     * @param loginID
     */
    public Event(int eventID, String eventName, String eventDate, String eventTime, String eventTimeEnd, String eventLocation, String eventInfo, String eventPrice, int eventMinimum, int eventMaximum, int loginID) {
        this.id = eventID;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventTimeEnd = eventTimeEnd;
        this.eventLocation = eventLocation;
        this.eventInfo = eventInfo;
        this.eventPrice = eventPrice;
        this.eventMinimum = eventMinimum;
        this.eventMaximum = eventMaximum;
        this.loginID = loginID;
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

    /**
     * Gets the eventPrice
     * @return eventPrice
     */
    public String getEventPrice(){return eventPrice;}

    /**
     * @param eventPrice
     * Sets the eventPrice
     */
    public void setEventPrice(String eventPrice){this.eventPrice = eventPrice;}

    /**
     * Gets the end time of event
     * @return
     */
    public String getEventTimeEnd() {
        return eventTimeEnd;
    }

    /**
     * Sets the end time of the event
     * @param eventTimeEnd
     */
    public void setEventTimeEnd(String eventTimeEnd) {
        this.eventTimeEnd = eventTimeEnd;
    }

    /**
     * Gets the minimum amount of participants
     * @return
     */
    public String getEventMinimum() {
        return String.valueOf(eventMinimum);
    }

    /**
     * Sets the maximum amount of participants
     * @param eventMinimum
     */
    public void setEventMinimum(int eventMinimum) {
        this.eventMinimum = eventMinimum;
    }

    /**
     * Gets the maximum amount of participants
     * @return
     */
    public String getEventMaximum() {
        return String.valueOf(eventMaximum);
    }

    /**
     * Sets the maximum amount of participants
     * @param eventMaximum
     */
    public void setEventMaximum(int eventMaximum) {
        this.eventMaximum = eventMaximum;
    }

    /**
     * Gets the current amount of customer on event
     * @return
     */
    public int getCurrentCustomersOnEvent() {
        return currentCustomersOnEvent;
    }

    /**
     * Sets the current amount of customer on event
     * @param currentCustomersOnEvent
     */
    public void setCurrentCustomersOnEvent(int currentCustomersOnEvent) {
        this.currentCustomersOnEvent = currentCustomersOnEvent;
    }
}
