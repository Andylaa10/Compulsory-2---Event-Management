package dal;

import be.Customer;
import be.Event;
import bll.helpers.ErrorHandling;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.db.DatabaseConnector;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {
    private final DatabaseConnector connector = DatabaseConnector.getInstance();

    private ErrorHandling errorHandling;
    private EventCoordinatorDAO eventCoordinatorDAO;

    /**
     * Constructor
     * @throws IOException
     */
    public EventDAO() throws IOException {
        errorHandling = new ErrorHandling();
        eventCoordinatorDAO = new EventCoordinatorDAO();
    }
    /**
     * Making an event list, connecting to the database and adding the results to our ArrayList.
     * @return a list of events or an empty list of events
     */
    public List<Event> getEvents() {

        ArrayList<Event> allEvents = new ArrayList<>();

        try (Connection connection = connector.getConnection()) {

            String sql = "SELECT * FROM Event;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                int eventID = resultset.getInt("EventID");
                String eventName = resultset.getString("EventName");
                String eventDate = resultset.getString("EventDate");
                String eventTime = resultset.getString("EventTime");
                String eventTimeEnd = resultset.getString("EventTimeEnd");
                String eventLocation = resultset.getString("EventLocation");
                String eventInfo = resultset.getString("EventInfo");
                String eventPrice = resultset.getString("EventPrice");
                int eventMinimum = resultset.getInt("EventMinimum");
                int eventMaximum = resultset.getInt("EventMaximum");


                Event event = new Event(eventID, eventName, eventDate, eventTime, eventTimeEnd, eventLocation, eventInfo, eventPrice, eventMinimum, eventMaximum);
                allEvents.add(event);
            }
            for (int i = 0; i < allEvents.size(); i++) {
                Event event = allEvents.get(i);
                if (event != null) {
                    List<Customer> totalCustomers = eventCoordinatorDAO.getCustomersOnEvent(event.getId());
                    event.setCurrentCustomersOnEvent(totalCustomers.size());
                }
            }
            return allEvents;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allEvents;
    }

    /**
     * Making an event list where a specific coordinator is assigned, connecting to the database and adding the results to our ArrayList.
     * @return a list of events or an empty list of events
     */
    public List<Event> getEventsCoordinator(int loginID) {

        ArrayList<Event> allEventsCoordinator = new ArrayList<>();

        try (Connection connection = connector.getConnection()) {

            String sql = "SELECT * FROM Event INNER JOIN CoordinatorOnEvent ON Event.EventID = CoordinatorOnEvent.eventId WHERE CoordinatorOnEvent.LoginId =?; ";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, loginID);
            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                int eventID = resultset.getInt("EventID");
                String eventName = resultset.getString("EventName");
                String eventDate = resultset.getString("EventDate");
                String eventTime = resultset.getString("EventTime");
                String eventTimeEnd = resultset.getString("EventTimeEnd");
                String eventLocation = resultset.getString("EventLocation");
                String eventInfo = resultset.getString("EventInfo");
                String eventPrice = resultset.getString("EventPrice");
                int eventMinimum = resultset.getInt("EventMinimum");
                int eventMaximum = resultset.getInt("EventMaximum");
                int loginId = resultset.getInt("LoginId");

                Event event = new Event(eventID, eventName, eventDate, eventTime, eventTimeEnd, eventLocation, eventInfo, eventPrice, eventMinimum, eventMaximum, loginId);
                allEventsCoordinator.add(event);
            }
            for (int i = 0; i < allEventsCoordinator.size(); i++) {
                Event event = allEventsCoordinator.get(i);
                if (event != null) {
                    List<Customer> totalCustomers = eventCoordinatorDAO.getCustomersOnEvent(event.getId());
                    event.setCurrentCustomersOnEvent(totalCustomers.size());
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allEventsCoordinator;
    }

    /**
     * Create a ticket, by inserting a giving eventName, eventDate, eventTime, eventLocation, eventInfo and eventPrice
     * @param eventName
     * @param eventDate
     * @param eventTime
     * @param eventLocation
     * @param eventInfo
     * @param eventPrice
     * @return Event
     * @throws SQLException
     */
    public Event createEvent (String eventName, String eventDate, String eventTime, String eventTimeEnd,
                              String eventLocation, String eventInfo, String eventPrice, int eventMinimum,
                              int eventMaximum) throws SQLException {
        try (Connection connection = connector.getConnection()) {
            String sql = "INSERT INTO Event (EventName, EventDate, eventTime, eventTimeEnd, eventLocation, eventInfo, eventPrice, eventMinimum, eventMaximum) values (?,?,?,?,?,?,?,?,?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, eventName);
                preparedStatement.setString(2, eventDate);
                preparedStatement.setString(3, eventTime);
                preparedStatement.setString(4, eventTimeEnd);
                preparedStatement.setString(5, eventLocation);
                preparedStatement.setString(6, eventInfo);
                preparedStatement.setString(7, eventPrice);
                preparedStatement.setInt(8, eventMinimum);
                preparedStatement.setInt(9, eventMaximum);

                preparedStatement.execute();

                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                int id = 0;
                if (resultSet.next()) {
                    id = resultSet.getInt(1);
                }

                Event event = new Event(id, eventName, eventDate, eventTime, eventTimeEnd, eventLocation, eventInfo,
                        eventPrice, eventMinimum, eventMaximum);
                return event;
            }

        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    /**
     * Deletes an event by taken the id
     * @param eventId
     */
    public void deleteEvent(int eventId) {
        try (Connection connection = connector.getConnection()) {
            String sql = "DELETE FROM Event WHERE EventID =?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, eventId);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            errorHandling.deleteEventDAOError();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a coordinator from event using the eventId and loginId
     * @param eventId
     * @param loginId
     */
    public void deleteCoordinatorFromEvent(int eventId, int loginId){
        try(Connection connection = connector.getConnection()){
            String sqlDelete = "DELETE FROM CoordinatorOnEvent WHERE EventId = ? AND LoginId = ?;";
            PreparedStatement st = connection.prepareStatement(sqlDelete, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, eventId);
            st.setInt(2, loginId);
            st.execute();
        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }

    /**
     * @param event
     * Edits an event
     */
    public void editEvent(Event event) {
        try (Connection connection = connector.getConnection()) {
            String sql = "UPDATE Event SET EventName=?, EventDate=?, EventTime=?, EventTimeEnd=?,  EventLocation=?, EventPrice=?, Eventinfo=?, EventMinimum=?, EventMaximum=?  WHERE EventID=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, event.getEventName());
            preparedStatement.setString(2, event.getEventDate());
            preparedStatement.setString(3, event.getEventTime());
            preparedStatement.setString(4, event.getEventTimeEnd());
            preparedStatement.setString(5, event.getEventLocation());
            preparedStatement.setString(6, event.getEventPrice());
            preparedStatement.setString(7, event.getEventInfo());
            preparedStatement.setString(8, event.getEventMinimum());
            preparedStatement.setString(9, event.getEventMaximum());
            preparedStatement.setInt(10, event.getId());

            if (preparedStatement.executeUpdate() != 1) {
                throw new Exception("Could not edit event");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Creates and assigning coordinator to an event when created
     * @param eventName
     * @param eventDate
     * @param eventTime
     * @param eventTimeEnd
     * @param eventLocation
     * @param eventInfo
     * @param eventPrice
     * @param eventMinimum
     * @param eventMaximum
     * @param LoginID
     * @return
     * @throws SQLException
     */
    public Event createAndAssignCoordinator (String eventName, String eventDate, String eventTime, String eventTimeEnd,
                              String eventLocation, String eventInfo, String eventPrice, int eventMinimum,
                              int eventMaximum, int LoginID) throws SQLException {
        try (Connection connection = connector.getConnection()) {
            String sql = "INSERT INTO Event (EventName, EventDate, eventTime, eventTimeEnd, eventLocation, eventInfo, eventPrice, eventMinimum, eventMaximum) values (?,?,?,?,?,?,?,?,?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, eventName);
                preparedStatement.setString(2, eventDate);
                preparedStatement.setString(3, eventTime);
                preparedStatement.setString(4, eventTimeEnd);
                preparedStatement.setString(5, eventLocation);
                preparedStatement.setString(6, eventInfo);
                preparedStatement.setString(7, eventPrice);
                preparedStatement.setInt(8, eventMinimum);
                preparedStatement.setInt(9, eventMaximum);
                preparedStatement.execute();

                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                int id = 0;
                if (resultSet.next()) {
                    id = resultSet.getInt(1);
                }

                String sqlInsertCoordinator = "INSERT INTO CoordinatorOnEvent (EventId, LoginId) VALUES (?,?);";
                PreparedStatement statement = connection.prepareStatement(sqlInsertCoordinator);
                statement.setInt(1, id);
                statement.setInt(2, LoginID);
                statement.execute();


                Event event = new Event(id, eventName, eventDate, eventTime, eventTimeEnd, eventLocation, eventInfo,
                        eventPrice, eventMinimum, eventMaximum);
                return event;


            }

        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    /**
     * Method to test eventDAO
     * @param args
     * @throws IOException
     * @throws SQLException
     */
    public static void main(String[] args) throws IOException, SQLException {
        EventDAO eventDAO = new EventDAO();
        List<Event> events = eventDAO.getEvents();
        List<Event> events1 = eventDAO.getEventsCoordinator(3);
        eventDAO.createAndAssignCoordinator("GG", "23:12:00", "13:00", "13:10", "EASV", "Donkey", "2", 1, 20, 3);

        System.out.println(events1);
    }
}
