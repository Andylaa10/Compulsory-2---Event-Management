package be;

import be.Interface.ILogin;

import java.util.ArrayList;
import java.util.List;

public class EventCoordinator implements ILogin {
    private int id;
    private String usernameEvent;
    private String passwordEvent;
    private boolean isAdmin;
    private List<Customer> customers = new ArrayList<>();
    private List<Ticket> tickets = new ArrayList<>();

    /**
     * Constructor
     */
    public EventCoordinator() {
    }

    /**
     * @param id
     * @param usernameEvent
     * @param passwordEvent
     * @param isAdmin
     * Constructor with id, usernameEvent, passwordEvent and isAdmin
     */
    public EventCoordinator(int id, String usernameEvent, String passwordEvent, boolean isAdmin) {
        this.id = id;
        this.usernameEvent = usernameEvent;
        this.passwordEvent = passwordEvent;
        this.isAdmin = isAdmin;
    }

    public EventCoordinator(String username, String password) {
        this.usernameEvent = username;
        this.passwordEvent = password;
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

    /**
     * Gets a list of the events
     * @return
     */
    public List<Ticket> getTickets() {
        return tickets;
    }

    /**
     * @param tickets
     * Sets the list of tickets
     */
    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
