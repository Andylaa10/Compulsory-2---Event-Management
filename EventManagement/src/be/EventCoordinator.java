package be;

import be.Interface.IUser;

public class EventCoordinator implements IUser {
    private static final String USERNAME_EVENT = "C";
    private static final String PASSWORD_EVENT = "1";

    @Override
    public String getUsername() {
        return USERNAME_EVENT;
    }

    @Override
    public String getPassword() {
        return PASSWORD_EVENT;
    }
}
