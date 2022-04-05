package gui.model;

import be.Customer;
import bll.CustomerManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CustomerModel {

    private CustomerManager customerManager;
    private ObservableList<Customer> customers;


    /**
     * Constructor
     */
    public CustomerModel() throws IOException, SQLException {
        customerManager = new CustomerManager();
        customers = FXCollections.observableArrayList();
        customers.addAll(customerManager.getCustomers());
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
    public void createCustomer (String firstName, String lastName, String phoneNumber, String email, String study, String note) throws SQLException {
        customerManager.createCustomer(firstName, lastName, phoneNumber, email, study, note);
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

    /**
     * Search method for the model, searches for events by text.
     * @param query
     * @return searchResults
     * @throws Exception
     */
    public List<Customer> searchCustomers(String query) throws SQLException {
        List<Customer> searchResults = null;

        searchResults = customerManager.searchCustomer(query);
        customers.clear();
        customers.addAll(searchResults);

        return searchResults;
    }

}
