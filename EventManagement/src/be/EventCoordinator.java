package be;

import be.Interface.IHuman;

public class EventCoordinator implements IHuman {
    private static final String USERNAME_EVENT = "A";
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
