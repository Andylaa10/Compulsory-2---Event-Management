package gui.model;

import be.Ticket;
import bll.TicketManager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TicketModel {

    private TicketManager ticketManager;

    /**
     * Constructor
     * @throws IOException
     */
    public TicketModel() throws IOException {
        ticketManager = new TicketManager();
    }

    /**
     * Gets the list of ticket using the getTicket method in ticketManagerDAO.
     * @return a list of ticket
     */
    public List<Ticket> getTickets() {
        List<Ticket> allTickets = ticketManager.getTickets();
        return allTickets;
    }

    /**
     * Creates a ticket using the createTicket method in ticketManagerDAO
     * @param ticketType
     * @param eventId
     * @param customerId
     * @throws SQLException
     */
    public void createTicket (String ticketType, int eventId, int customerId) throws SQLException {
        ticketManager.createTicket(ticketType, eventId, customerId);
    }

    /**
     * Deletes a ticket using the deleteTicket methods in ticketManagerDAO
     * @param id
     */
    public void deleteTicket(int id) {
        ticketManager.deleteTicket(id);
    }

    /**
     * Edits a ticket using the editTicket from ticketManagerDAO
     * @param ticket
     */
    public void editTicket(Ticket ticket) {
        ticketManager.editTicket(ticket);
    }
}
