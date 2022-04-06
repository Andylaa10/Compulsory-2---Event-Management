package be;

public class Ticket {
    private int id;
    private int eventId;
    private int customerId;
    private String generatedTicketId;

    public Ticket(int id, int eventId, int customerId, String generatedTicketId) {
        this.id = id;
        this.eventId = eventId;
        this.customerId = customerId;
        this.generatedTicketId = generatedTicketId;
    }

    public Ticket() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getGeneratedTicketId() {
        return generatedTicketId;
    }

    public void setGeneratedTicketId(String generatedTicketId) {
        this.generatedTicketId = generatedTicketId;
    }
}
