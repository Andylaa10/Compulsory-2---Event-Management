package gui.model;

import be.Admin;

import be.EventCoordinator;
import bll.AdminManager;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class AdminModel {

    private AdminManager adminManager;


    /**
     * Constructor
     * @throws IOException
     */
    public AdminModel() throws IOException, SQLException {
        adminManager = new AdminManager();
    }

    /**
     * Gets the list of admin using the getAdmins method in adminManager
     * @return a list of admin
     */
    public List<Admin> getAdmins() {
        List<Admin> allAdmins = adminManager.getAdmins();
        return allAdmins;
    }

    /**
     * Edits admin using the editAdmin from adminManager
     * @param admin
     */
    public void editAdmin(Admin admin) {
        adminManager.editAdmin(admin);
    }



    /**
     * Gets the list of coordinator using the getCoordinator method in eventCoordinatorManager
     * @return a list of coordinator
     */
    public List<EventCoordinator> getCoordinator() throws SQLException {
        List<EventCoordinator> allEventCoordinators = adminManager.getCoordinator();
        return allEventCoordinators;
    }

    /**
     * Creates a coordinator using the createCoordinator method in eventCoordinatorManager
     * @param username
     * @param password
     * @param isAdmin
     * @throws SQLException
     */
    public void createCoordinator(String username, String password, boolean isAdmin) throws SQLException {
        adminManager.createCoordinator(username, password, isAdmin);
    }

    /**
     * Deletes a coordinator using the deleteCoordinator methods in eventCoordinatorManager
     * @param id
     */
    public void deleteCoordinator(int id) {
        adminManager.deleteCoordinator(id);
    }

    /**
     * Edits a coordinator using the editCoordinator from eventCoordinatorManager
     * @param eventCoordinator
     */
    public void editCoordinator(EventCoordinator eventCoordinator) {
        adminManager.editCoordinator(eventCoordinator);
    }

    /**
     * Gets the coordinator on event using getCoordinatorsOnEvent from adminManager
     * @param eventId
     * @return a list of coordinators on event or an empty list of coordinator on event
     * @throws SQLException
     */
    public List<EventCoordinator> getCoordinatorsOnEvent(int eventId) throws SQLException {
        return adminManager.getCoordinatorsOnEvent(eventId);
    }

    /**
     * Adds a coordinator to event using addCoordinatorToEvent method in adminManager
     * @param loginId
     * @param eventId
     */
    public void addCoordinatorToEvent(int eventId, int loginId){
        adminManager.addCoordinatorToEvent(eventId, loginId);
    }

    /**
     * Deletes coordinator from event using deleteFromEvent from adminManager
     * @param loginId
     * @param eventId
     */
    public void deleteFromEvent(int loginId, int eventId){
        adminManager.deleteFromEvent(loginId, eventId);
    }

    /**
     * Gets the admin username and the password using login from adminManager
     * @param username
     * @param password
     * @return
     * @throws SQLServerException
     */
    public Admin login(String username, String password) throws SQLServerException {
        return adminManager.login(username, password);
    }
}



