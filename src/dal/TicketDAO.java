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

    /**
     * Constructor
     * @throws IOException
     */
    public TicketDAO() throws IOException {
    }


    /**
     * Gets a list of generatedTicketIds
     * @param customerId
     * @return
     * @throws SQLServerException
     */
    public List<Ticket> getGeneratedTicketId(int customerId) throws SQLServerException {

        ArrayList<Ticket> generatedTicketIDList = new ArrayList<>();

        try (Connection connection = connector.getConnection()){
            String sql = "SELECT * FROM Ticket WHERE CustomerId=?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, customerId);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while(resultSet.next()){

                int ticketID = resultSet.getInt("TicketID");
                int eventID = resultSet.getInt("EventId");
                int customerID = resultSet.getInt("CustomerId");
                String generatedTicketID = resultSet.getString("GeneratedTicketID");
                generatedTicketIDList.add(new Ticket(ticketID, eventID, customerID, generatedTicketID));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return generatedTicketIDList;
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
                int eventID = resultset.getInt("EventId");
                int customerID = resultset.getInt("CustomerId");
                String generatedTicketID = resultset.getString("GeneratedTicketID");

                Ticket ticket = new Ticket(ticketID, eventID, customerID, generatedTicketID);
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
     * @param eventId
     * @param customerId
     * @param generatedTicketID
     * @return
     * @throws SQLException
     */
    public Ticket createTicket (int eventId, int customerId, String generatedTicketID) throws SQLException {
        try (Connection connection = connector.getConnection()) {
            String sql = "INSERT INTO Ticket (EventId, CustomerId, GeneratedTicketID) values (?,?,?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, eventId);
                preparedStatement.setInt(2, customerId);
                preparedStatement.setString(3, generatedTicketID);
                preparedStatement.execute();

                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                int id = 0;
                if (resultSet.next()) {
                    id = resultSet.getInt(1);
                }

                Ticket ticket = new Ticket(id, eventId, customerId, generatedTicketID);
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
            String sql = "DELETE FROM Ticket WHERE CustomerId =?;";
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
            String sql = "UPDATE Ticket SET EventId=?, CustomerId=?, GeneratedTicketID=? WHERE TicketID=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, ticket.getEventId());
            preparedStatement.setInt(2, ticket.getCustomerId());
            preparedStatement.setInt(3, ticket.getId());
            preparedStatement.setString(4, ticket.getGeneratedTicketId());
            if (preparedStatement.executeUpdate() != 1) {
                throw new Exception("Could not edit ticket");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to test TicketDAO
     * @param args
     * @throws IOException
     * @throws SQLException
     */
    public static void main(String[] args) throws IOException, SQLException {
        TicketDAO ticketDAO = new TicketDAO();
        List<Ticket> generatedTicketID = ticketDAO.getGeneratedTicketId(26);
        System.out.println(generatedTicketID.get(0).getGeneratedTicketId());
    }
}
