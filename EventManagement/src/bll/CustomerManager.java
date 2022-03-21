package bll;

import be.Customer;
import dal.CustomerDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CustomerManager {

    private CustomerDAO customerDAO;

    /**
     * Constructor
     */
    public CustomerManager() throws IOException {
        customerDAO = new CustomerDAO();
    }

    /**
     * Gets the list of customer using the getCustomer method in customerDAO.
     *
     * @return a list of customer
     */
    public List<Customer> getCustomers() throws SQLException {
        List<Customer> allCustomers = customerDAO.getCustomers();
        return allCustomers;
    }

    /**
     * Creates a customer using the createCustomer method in customerDAO
     *
     * @param firstName
     * @param lastName
     * @param phoneNumber
     * @param email
     * @throws SQLException
     */
    public void createCustomer(String firstName, String lastName, String phoneNumber, String email) throws SQLException {
        customerDAO.createCustomer(firstName, lastName, phoneNumber, email);
    }

    /**
     * Deletes a customer using the deleteCustomer methods in customerDAO
     *
     * @param id
     */
    public void deleteCustomer(int id) {
        customerDAO.deleteCustomer(id);
    }

    /**
     * Edits a customer using the editCustomer from customerDAO
     *
     * @param customer
     */
    public void editCustomer(Customer customer) {
        customerDAO.editCustomer(customer);
    }
}
