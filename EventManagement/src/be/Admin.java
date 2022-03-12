package be;

import be.Interface.IHuman;

public class Admin implements IHuman {

    private static final String USERNAME_ADMIN = "A";
    private static final String PASSWORD_ADMIN = "1";

    @Override
    public String getUsername() {
        return USERNAME_ADMIN;
    }

    @Override
    public String getPassword() {
        return PASSWORD_ADMIN;
    }
}
