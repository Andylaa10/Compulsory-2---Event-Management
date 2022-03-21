package gui.model;

import be.Admin;
import bll.AdminManager;
import java.io.IOException;
import java.util.List;


public class AdminModel {
    private AdminManager adminManager;

    public AdminModel() throws IOException {
        adminManager = new AdminManager();
    }

    public List<Admin> getAdmins() {
        List<Admin> allAdmins = adminManager.getAdmins();
        return allAdmins;
    }

    public void editAdmin(Admin admin) {
        adminManager.editAdmin(admin);
    }


}



