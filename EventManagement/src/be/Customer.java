package be;

import be.Interface.IHuman;

public class Customer implements IHuman {
    private static final String USERNAME_CUSTOMER = "A";
    private static final String PASSWORD_CUSTOMER = "1";

    @Override
    public String getUsername() {
        return USERNAME_CUSTOMER;
    }

    @Override
    public String getPassword() {
        return PASSWORD_CUSTOMER;
    }
}
