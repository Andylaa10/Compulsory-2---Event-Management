package bll;

import be.Admin;
import dal.AdminDAO;
import java.io.IOException;
import java.util.List;

public class AdminManager {

    private AdminDAO adminDAO;

    public AdminManager() throws IOException {
        adminDAO = new AdminDAO();
    }

    public List<Admin> getAdmins() {
        List<Admin> allAdmins = adminDAO.getAdmins();
        return allAdmins;
    }

    public void editAdmin(Admin admin) {
        adminDAO.editAdmin(admin);
    }

}
