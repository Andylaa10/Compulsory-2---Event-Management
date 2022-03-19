package dal;

import be.Event;
import be.EventCoordinator;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.db.DatabaseConnector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {
    private final DatabaseConnector connector;

    public EventDAO() throws IOException {
        connector = new DatabaseConnector();
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
                    String eventData = resultset.getString("EventDate");
                    String eventTime = resultset.getString("EventTime");
                    String eventLocation = resultset.getString("EventLocation");
                    String eventInfo = resultset.getString("EventInfo");


                    Event event = new Event(eventID, eventName, eventData, eventTime, eventLocation, eventInfo);
                    allEvents.add(event);
                }
            }
        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allEvents;
    }
}
