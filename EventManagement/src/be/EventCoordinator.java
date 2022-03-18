package be;

import be.Interface.ILogin;

import java.util.ArrayList;
import java.util.List;

public class EventCoordinator implements ILogin {
    private int id;
    private String usernameEvent = "B";
    private String passwordEvent = "1";
    private List<Customer> customers = new ArrayList<>();

    /**
     * Constructor
     */
    public EventCoordinator() {
    }

    /**
     * @param id
     * @param usernameEvent
     * @param passwordEvent
     * Constructor with id, usernameEvent and passwordEvent
     */
    public EventCoordinator(int id, String usernameEvent, String passwordEvent) {
        this.id = id;
        this.usernameEvent = usernameEvent;
        this.passwordEvent = passwordEvent;
    }

    /**
     * Gets the id
     * @return id
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * @param id
     * Sets the id
     */
    @Override
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the username
     * @return usernameEvent
     */
    @Override
    public String getUsername() {
        return usernameEvent;
    }

    /**
     * @param username
     * Sets the usernameEvent
     */
    @Override
    public void setUsername(String username) {
        this.usernameEvent = username;
    }

    /**
     * Gets the password
     * @return passwordEvent
     */
    @Override
    public String getPassword() {
        return passwordEvent;
    }

    /**
     * @param password
     * Sets the passwordEvent
     */
    @Override
    public void setPassword(String password) {
        this.passwordEvent = password;
    }

    /**
     * Check if this is an admin
     * @return false
     */
    @Override
    public Boolean isAdmin() {
        return false;
    }

    /**
     * Gets a list of all the customers
     * @return customers
     */
    public List<Customer> getCustomers() {
        return customers;
    }

    /**
     * @param customers
     * Sets the list of customers
     */
    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
