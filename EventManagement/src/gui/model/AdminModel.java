package gui.model;

import be.Admin;
import bll.AdminManager;
import java.io.IOException;
import java.util.List;


public class AdminModel {
    private AdminManager adminManager;

    /**
     * Constructor
     * @throws IOException
     */
    public AdminModel() throws IOException {
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


}



