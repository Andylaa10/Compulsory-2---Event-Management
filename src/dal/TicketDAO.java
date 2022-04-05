package dal;

import be.Ticket;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.db.DatabaseConnector;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {
    private final DatabaseConnector connector = DatabaseConnector.getInstance();

    public TicketDAO() throws IOException {
    }

    /**
     * Making a ticket list, connecting to the database and adding the results to our ArrayList.
     * @return a list of tickets or an empty list of tickets
     */
    public List<Ticket> getTickets() {

        ArrayList<Ticket> allTickets = new ArrayList<>();

        try (Connection connection = connector.getConnection()) {

            String sql = "SELECT * FROM Ticket;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                int ticketID = resultset.getInt("TicketID");
                String ticketType = resultset.getString("TicketType");
                String ticketPicture = resultset.getString("TicketPicture");
                int eventID = resultset.getInt("EventId");
                int customerID = resultset.getInt("CustomerId");

                Ticket ticket = new Ticket(ticketID, ticketType, ticketPicture, eventID, customerID);
                allTickets.add(ticket);


            }
        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allTickets;
    }

    /**
     * Creates a ticket, by inserting a giving ticketType, ticketPicture, eventId and customerId
     * @param ticketType
     * @param ticketPicture
     * @param eventId
     * @param customerId
     * @return
     * @throws SQLException
     */
    public Ticket createTicket (String ticketType, String ticketPicture, int eventId, int customerId) throws SQLException {
        try (Connection connection = connector.getConnection()) {
            String sql = "INSERT INTO Ticket (TicketType, TicketPicture, EventId, CustomerId) values (?,?,?,?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, ticketType);
                preparedStatement.setString(2, ticketPicture);
                preparedStatement.setInt(3, eventId);
                preparedStatement.setInt(4, customerId);
                preparedStatement.execute();

                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                int id = 0;
                if (resultSet.next()) {
                    id = resultSet.getInt(1);
                }

                Ticket ticket = new Ticket(id, ticketType, ticketPicture, eventId, customerId);
                return ticket;

            }

        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    /**
     * @param id
     * Deletes a ticket by taken the id
     */
    public void deleteTicket(int id) {
        try (Connection connection = connector.getConnection()) {
            String sql = "DELETE FROM Ticket WHERE TicketID =?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() != 1) {
                throw new Exception("Could not delete ticket");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param ticket
     * Edits a ticket
     */
    public void editTicket(Ticket ticket) {
        try (Connection connection = connector.getConnection()) {
            String sql = "UPDATE Ticket SET TicketType=?, TicketPicture=?, EventId=?, CustomerId=? WHERE TicketID=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ticket.getTicketType());
            preparedStatement.setString(2, ticket.getTicketPicture());
            preparedStatement.setInt(3, ticket.getEventId());
            preparedStatement.setInt(4, ticket.getCustomerId());
            preparedStatement.setInt(5, ticket.getId());
            if (preparedStatement.executeUpdate() != 1) {
                throw new Exception("Could not edit ticket");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, SQLException {
        TicketDAO ticketDAO = new TicketDAO();
        //ticketDAO.createTicket("VIP", "src/gui/image/placeholder-image.png", 4, 1);
        ticketDAO.deleteTicket(3);
        List<Ticket> tickets = ticketDAO.getTickets();
        System.out.println(tickets);
    }
}
