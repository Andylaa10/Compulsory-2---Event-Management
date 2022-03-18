package be;

public class Ticket {
    private int id;
    private String ticketType;
    private String ticketPicture;
    private int eventId;
    private int customerId;

    /**
     * @param id
     * @param ticketType
     * @param ticketPicture
     * @param eventId
     * @param customerId
     * Constructor with id, ticketType, ticketPicture, eventId and customerId
     */
    public Ticket(int id, String ticketType, String ticketPicture, int eventId, int customerId) {
        this.id = id;
        this.ticketType = ticketType;
        this.ticketPicture = ticketPicture;
        this.eventId = eventId;
        this.customerId = customerId;
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
     * Gets the ticketType
     * @return ticketType
     */
    public String getTicketType() {
        return ticketType;
    }

    /**
     * @param ticketType
     * Sets the ticketType
     */
    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    /**
     * Gets the ticketPicture
     * @return
     */
    public String getTicketPicture() {
        return ticketPicture;
    }

    /**
     * @param ticketPicture
     * Sets the ticketPicture
     */
    public void setTicketPicture(String ticketPicture) {
        this.ticketPicture = ticketPicture;
    }

    /**
     * Gets the eventId
     * @return eventId
     */
    public int getEventId() {
        return eventId;
    }

    /**
     * @param eventId
     * Sets the eventId
     */
    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    /**
     * Gets the customerId
     * @return customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId
     * Sets the customerId
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
