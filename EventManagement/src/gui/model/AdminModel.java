package gui.model;

import be.Admin;
import be.Customer;
import be.Event;
import be.EventCoordinator;
import bll.AdminManager;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
     * Gets the list of admin using the getAdmins method in adminManagerDAO.
     * @return a list of admin
     */
    public List<Admin> getAdmins() {
        List<Admin> allAdmins = adminManager.getAdmins();
        return allAdmins;
    }

    /**
     * Edits admin using the editAdmin from adminManagerDAO
     * @param admin
     */
    public void editAdmin(Admin admin) {
        adminManager.editAdmin(admin);
    }



    /**
     * Gets the list of coordinator using the getCoordinator method in eventCoordinatorManagerDAO.
     * @return a list of coordinator
     */
    public List<EventCoordinator> getCoordinator() throws SQLException {
        List<EventCoordinator> allEventCoordinators = adminManager.getCoordinator();
        return allEventCoordinators;
    }

    /**
     * Creates a coordinator using the createCoordinator method in eventCoordinatorManagerDAO
     * @param username
     * @param password
     * @param isAdmin
     * @throws SQLException
     */
    public void createCoordinator(String username, String password, boolean isAdmin) throws SQLException {
        adminManager.createCoordinator(username, password, isAdmin);
    }

    /**
     * Deletes a coordinator using the deleteCoordinator methods in eventCoordinatorManagerDAO
     * @param id
     */
    public void deleteCoordinator(int id) {
        adminManager.deleteCoordinator(id);
    }

    /**
     * Edits a coordinator using the editCoordinator from eventCoordinatorManagerDAO
     * @param eventCoordinator
     */
    public void editCoordinator(EventCoordinator eventCoordinator) {
        adminManager.editCoordinator(eventCoordinator);
    }


    public Admin login(String username, String password) throws SQLServerException {
        return adminManager.login(username, password);
    }
}



