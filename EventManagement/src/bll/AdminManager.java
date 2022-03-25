package bll;

import be.Admin;
import be.EventCoordinator;
import dal.AdminDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AdminManager {

    private AdminDAO adminDAO;

    /**
     * Constructor
     * @throws IOException
     */
    public AdminManager() throws IOException {
        adminDAO = new AdminDAO();
    }

    /**
     * Gets the list of admin using the getAdmins method in adminDAO.
     * @return a list of admin
     */
    public List<Admin> getAdmins() {
        List<Admin> allAdmins = adminDAO.getAdmins();
        return allAdmins;
    }

    /**
     * Edits admin using the editAdmin from adminDAO
     * @param admin
     */
    public void editAdmin(Admin admin) {
        adminDAO.editAdmin(admin);
    }


    /**
     * Gets the list of coordinator using the getCoordinator method in eventCoordinatorDAO.
     * @return a list of coordinator
     */
    public List<EventCoordinator> getCoordinator() throws SQLException {
        List<EventCoordinator> allEventCoordinators = adminDAO.getCoordinator();
        return allEventCoordinators;
    }

    /**
     * Creates a coordinator using the createCoordinator method in eventCoordinatorDAO
     * @param username
     * @param password
     * @param isAdmin
     * @throws SQLException
     */
    public void createCoordinator(String username, String password, boolean isAdmin) throws SQLException {
        adminDAO.createCoordinator(username, password, isAdmin);
    }

    /**
     * Deletes a coordinator using the deleteCoordinator methods in eventCoordinatorDAO
     * @param id
     */
    public void deleteCoordinator(int id) {
        adminDAO.deleteCoordinator(id);
    }

    /**
     * Edits a coordinator using the editCoordinator from eventCoordinatorDAO
     * @param eventCoordinator
     */
    public void editCoordinator(EventCoordinator eventCoordinator) {
        adminDAO.editCoordinator(eventCoordinator);
    }


}
