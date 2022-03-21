package bll;

import be.EventCoordinator;
import dal.EventCoordinatorDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EventCoordinatorManager {

    private EventCoordinatorDAO eventCoordinatorDAO;

    public EventCoordinatorManager() throws IOException {
        eventCoordinatorDAO = new EventCoordinatorDAO();
    }

    public List<EventCoordinator> getCoordinator() {
        List<EventCoordinator> allEventCoordinators = eventCoordinatorDAO.getCoordinator();
        return allEventCoordinators;
    }

    public void createCoordinator(String username, String password, boolean isAdmin) throws SQLException {
        eventCoordinatorDAO.createCoordinator(username, password, isAdmin);
    }

    public void deleteCoordinator(int id, boolean isAdmin) {
        eventCoordinatorDAO.deleteCoordinator(id, isAdmin);
    }

    public void editCoordinator(EventCoordinator eventCoordinator) {
        eventCoordinatorDAO.editCoordinator(eventCoordinator);
    }
}
