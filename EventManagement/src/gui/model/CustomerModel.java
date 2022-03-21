package gui.model;

import be.Customer;
import bll.CustomerManager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CustomerModel {

    private CustomerManager customerManager;

    /**
     * Constructor
     */
    public CustomerModel() throws IOException {
        customerManager = new CustomerManager();
    }

    /**
     * Gets the list of customer using the getCustomer method in customerManagerDAO.
     * @return a list of customer
     */
    public List<Customer> getCustomers() throws SQLException {
        List<Customer> allCustomers = customerManager.getCustomers();
        return allCustomers;
    }

    /**
     * Creates a customer using the createCustomer method in customerManagerDAO
     * @param firstName
     * @param lastName
     * @param phoneNumber
     * @param email
     * @throws SQLException
     */
    public void createCustomer (String firstName, String lastName, String phoneNumber, String email) throws SQLException {
        customerManager.createCustomer(firstName, lastName, phoneNumber, email);
    }

    /**
     * Deletes a customer using the deleteCustomer methods in customerManagerDAO
     * @param id
     */
    public void deleteCustomer(int id) {
        customerManager.deleteCustomer(id);
    }

    /**
     * Edits a customer using the editCustomer from customerManagerDAO
     * @param customer
     */
    public void editCustomer(Customer customer) {
        customerManager.editCustomer(customer);
    }
}
