package gui.model;

import be.Ticket;
import bll.TicketManager;
import dal.TicketDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TicketModel {

    private TicketManager ticketManager;

    public TicketModel() throws IOException {
        ticketManager = new TicketManager();
    }

    public List<Ticket> getCoordinators() {
        List<Ticket> allTickets = ticketManager.getTickets();
        return allTickets;
    }

    public void createTicket (String ticketType, String ticketPicture, int eventId, int customerId) throws SQLException {
        ticketManager.createTicket(ticketType, ticketPicture, eventId, customerId);
    }

    public void deleteTicket(int id) {
        ticketManager.deleteTicket(id);
    }

    public void editTicket(Ticket ticket) {
        ticketManager.editTicket(ticket);
    }
}
