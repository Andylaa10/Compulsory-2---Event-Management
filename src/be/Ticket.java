package be;

public class Ticket {
    private int id;
    private int eventId;
    private int customerId;
    private String generatedTicketID;

    public Ticket(int id, int eventId, int customerId, String generatedTicketID) {
        this.id = id;
        this.eventId = eventId;
        this.customerId = customerId;
        this.generatedTicketID = generatedTicketID;
    }

    public Ticket() {

    }

    public Ticket(String generatedTicketID) {
        this.generatedTicketID = generatedTicketID;
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
        return generatedTicketID;
    }

    public void setGeneratedTicketId(String generatedTicketId) {
        this.generatedTicketID = generatedTicketID;
    }
}
