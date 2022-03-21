package gui.model;

import be.EventCoordinator;
import bll.EventCoordinatorManager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EventCoordinatorModel {

    private EventCoordinatorManager eventCoordinatorManager;

    public EventCoordinatorModel() throws IOException {
        eventCoordinatorManager = new EventCoordinatorManager();
    }

    public List<EventCoordinator> getCoordinator() throws SQLException {
        List<EventCoordinator> allEventCoordinators = eventCoordinatorManager.getCoordinator();
        return allEventCoordinators;
    }

    public void createCoordinator(String username, String password, boolean isAdmin) throws SQLException {
        eventCoordinatorManager.createCoordinator(username, password, isAdmin);
    }

    public void deleteCoordinator(int id, boolean isAdmin) {
        eventCoordinatorManager.deleteCoordinator(id, isAdmin);
    }

    public void editCoordinator(EventCoordinator eventCoordinator) {
        eventCoordinatorManager.editCoordinator(eventCoordinator);
    }

}
