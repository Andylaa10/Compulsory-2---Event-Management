package dal;

import be.Event;
import be.ErrorHandling;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.db.DatabaseConnector;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {
    private final DatabaseConnector connector;

    private ErrorHandling errorHandling;

    public EventDAO() throws IOException {
        connector = new DatabaseConnector();
        errorHandling = new ErrorHandling();
    }
    /**
     * Making an event list, connecting to the database and adding the results to our ArrayList.
     * @return a list of events or an empty list of events
     */
    public List<Event> getEvents() {

        ArrayList<Event> allEvents = new ArrayList<>();

        try (Connection connection = connector.getConnection()) {

            String sql = "SELECT * FROM Event;";

            Statement statement = connection.createStatement();

            if (statement.execute(sql)) {
                ResultSet resultset = statement.getResultSet();
                while (resultset.next()) {
                    int eventID = resultset.getInt("EventID");
                    String eventName = resultset.getString("EventName");
                    String eventDate = resultset.getString("EventDate");
                    String eventTime = resultset.getString("EventTime");
                    String eventLocation = resultset.getString("EventLocation");
                    String eventInfo = resultset.getString("EventInfo");


                    Event event = new Event(eventID, eventName, eventDate, eventTime, eventLocation, eventInfo);
                    allEvents.add(event);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allEvents;
    }

    /**
     * Create a ticket, by inserting a giving eventName, eventDate, eventTime, eventLocation and eventInfo
     * @param eventName
     * @param eventDate
     * @param eventTime
     * @param eventLocation
     * @param eventInfo
     * @return Event
     * @throws SQLException
     */
    public Event createEvent (String eventName, String eventDate, String eventTime, String eventLocation, String eventInfo) throws SQLException {
        try (Connection connection = connector.getConnection()) {
            String sql = "INSERT INTO Event (EventName, EventDate, eventTime, eventLocation, eventInfo) values (?,?,?,?,?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, eventName);
                preparedStatement.setString(2, eventDate);
                preparedStatement.setString(3, eventTime);
                preparedStatement.setString(4, eventLocation);
                preparedStatement.setString(5, eventInfo);
                preparedStatement.execute();

                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                int id = 0;
                if (resultSet.next()) {
                    id = resultSet.getInt(1);
                }

                Event event = new Event(id, eventName, eventDate, eventTime, eventLocation, eventInfo);
                return event;

            }

        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    /**
     * @param id
     * Deletes an event by taken the id
     */
    public void deleteEvent(int id) {
        try (Connection connection = connector.getConnection()) {
            String sql = "DELETE FROM Event WHERE EventID =?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            errorHandling.deleteEventDAOError();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param event
     * Edits an event
     */
    public void editEvent(Event event) {
        try (Connection connection = connector.getConnection()) {
            String sql = "UPDATE Event SET EventName=?, EventDate=?, EventTime=?, EventLocation=?, EventInfo=? WHERE EventID=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, event.getEventName());
            preparedStatement.setString(2, event.getEventDate());
            preparedStatement.setString(3, event.getEventTime());
            preparedStatement.setString(4, event.getEventLocation());
            preparedStatement.setString(5, event.getEventInfo());
            preparedStatement.setInt(6, event.getId());
            if (preparedStatement.executeUpdate() != 1) {
                throw new Exception("Could not edit event");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException, SQLException {
        EventDAO eventDAO = new EventDAO();
        eventDAO.createEvent("Test event", "22-03-2022", "13:30", "EASV Sønderborg", "Dette er en test af eventDAO");
        eventDAO.createEvent("event", "10-03-2022", "12:30", "EASV Danmark", "Dette er en test af eventDAO");
        eventDAO.createEvent("Test", "02-12-2022", "13:30", "EASV Esbjerg", "Dette er en test af eventDAO");
        List<Event> events = eventDAO.getEvents();

        System.out.println(events);
    }
}
