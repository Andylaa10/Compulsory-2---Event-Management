package bll;

import be.Admin;
import dal.AdminDAO;
import java.io.IOException;
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


}
