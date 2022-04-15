package be;

public class Ticket {

    private int id;
    private int eventId;
    private int customerId;
    private String generatedTicketID;

    /**
     * Constructor with id, eventId, customerId and generatedId
     * @param id
     * @param eventId
     * @param customerId
     * @param generatedTicketID
     */
    public Ticket(int id, int eventId, int customerId, String generatedTicketID) {
        this.id = id;
        this.eventId = eventId;
        this.customerId = customerId;
        this.generatedTicketID = generatedTicketID;
    }


    /**
     * Constructor with generatedId and customerId
     * @param generatedTicketID
     * @param customerId
     */
    public Ticket(String generatedTicketID, int customerId) {
        this.customerId = customerId;
        this.generatedTicketID = generatedTicketID;
    }

    /**
     * Constructor
     */
    public Ticket() {
    }

    /**
     * Constructor with generatedId
     * @param generatedTicketID
     */
    public Ticket(String generatedTicketID) {
        this.generatedTicketID = generatedTicketID;
    }

    /**
     * Gets the id
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the eventId
     * @return
     */
    public int getEventId() {
        return eventId;
    }

    /**
     * Sets the eventId
     * @param eventId
     */
    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    /**
     * Gets the customerId
     * @return
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets the customerId
     * @param customerId
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Gets the generatedId
     * @return
     */
    public String getGeneratedTicketId() {
        return generatedTicketID;
    }

    /**
     * Sets the generatedId
     * @param generatedTicketId
     */
    public void setGeneratedTicketId(String generatedTicketId) {
        this.generatedTicketID = generatedTicketID;
    }

    /**
     * ToString method to print the generated ticketID. Used on tickets
     * @return
     */
    @Override
    public String toString() {
        return generatedTicketID;
    }
}
