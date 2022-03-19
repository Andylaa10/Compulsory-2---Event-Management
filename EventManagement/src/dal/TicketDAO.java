package dal;

import be.EventCoordinator;
import be.Ticket;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.db.DatabaseConnector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {
    private final DatabaseConnector connector;

    public TicketDAO() throws IOException {
        connector = new DatabaseConnector();
    }

    /**
     * Making a ticket list, connecting to the database and adding the results to our ArrayList.
     * @return a list of tickets or an empty list of tickets
     */
    public List<Ticket> getCoordinators() {

        ArrayList<Ticket> allTickets = new ArrayList<>();

        try (Connection connection = connector.getConnection()) {

            String sql = "SELECT * FROM Ticket;";

            Statement statement = connection.createStatement();

            if (statement.execute(sql)) {
                ResultSet resultset = statement.getResultSet();
                while (resultset.next()) {
                    int ticketID = resultset.getInt("TicketID");
                    String ticketType = resultset.getString("TicketType");
                    String ticketPicture = resultset.getString("TicketPicture");
                    int eventID = resultset.getInt("EventId");
                    int customerID = resultset.getInt("CustomerId");

                    Ticket ticket = new Ticket(ticketID, ticketType, ticketPicture, eventID, customerID);
                    allTickets.add(ticket);
                }
            }
        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allTickets;
    }
}
