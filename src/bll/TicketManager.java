package bll;

import be.Ticket;
import dal.TicketDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TicketManager {

    private TicketDAO ticketDAO;

    /**
     * Constructor
     * @throws IOException
     */
    public TicketManager() throws IOException {
        ticketDAO = new TicketDAO();
    }

    /**
     * Gets the list of ticket using the getTicket method in ticketDAO.
     * @return a list of ticket
     */
    public List<Ticket> getTickets() {
        List<Ticket> allTickets = ticketDAO.getTickets();
        return allTickets;
    }

    /**
     * Creates a ticket using the createTicket method in ticketDAO
     * @param ticketType
     * * @param eventId
     * @param customerId
     * @throws SQLException
     */
    public void createTicket (String ticketType, int eventId, int customerId) throws SQLException {
        ticketDAO.createTicket(ticketType, eventId, customerId);
    }

    /**
     * Deletes a ticket using the deleteTicket methods in ticketDAO
     * @param id
     */
    public void deleteTicket(int id) {
        ticketDAO.deleteTicket(id);
    }

    /**
     * Edits a ticket using the editTicket from ticketDAO
     * @param ticket
     */
    public void editTicket(Ticket ticket) {
        ticketDAO.editTicket(ticket);
    }
}
