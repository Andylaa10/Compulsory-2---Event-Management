package gui.model;

import be.Ticket;
import bll.TicketManager;
import com.microsoft.sqlserver.jdbc.SQLServerException;

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
     * Gets the list of generatedTicketId using the getGeneratedTicketID from ticketManager
     * @param customerId
     * @return
     * @throws SQLServerException
     */
    public List<Ticket> getGeneratedTicketID(int customerId, int eventId) throws SQLServerException {
        return ticketManager.getGeneratedTicketID(customerId, eventId);
    }


    /**
     * Creates a ticket using the createTicket method in ticketManagerDAO
     * @param generatedTicketID
     * @param eventId
     * @param customerId
     * @throws SQLException
     */
    public void createTicket ( int eventId, int customerId, String generatedTicketID) throws SQLException {
        ticketManager.createTicket(eventId, customerId, generatedTicketID);
    }

    /**
     * Deletes a ticket using the deleteTicket methods in ticketManagerDAO
     * @param id
     */
    public void deleteTicket(int id) {
        ticketManager.deleteTicket(id);
    }

}
