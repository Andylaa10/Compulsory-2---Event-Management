package bll;

import be.Ticket;
import dal.TicketDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TicketManager {

    private TicketDAO ticketDAO;

    public TicketManager() throws IOException {
        ticketDAO = new TicketDAO();
    }

    public List<Ticket> getTickets() {
        List<Ticket> allTickets = ticketDAO.getTickets();
        return allTickets;
    }

    public void createTicket (String ticketType, String ticketPicture, int eventId, int customerId) throws SQLException {
        ticketDAO.createTicket(ticketType, ticketPicture, eventId, customerId);
    }

    public void deleteTicket(int id) {
        ticketDAO.deleteTicket(id);
    }

    public void editTicket(Ticket ticket) {
        ticketDAO.editTicket(ticket);
    }
}
