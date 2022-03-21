package gui.model;

import be.Customer;
import bll.CustomerManager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CustomerModel {


    private CustomerManager customerManager;

    public CustomerModel() throws IOException {
        customerManager = new CustomerManager();
    }

    public List<Customer> getCustomers() throws SQLException {
        List<Customer> allCustomers = customerManager.getCustomers();
        return allCustomers;
    }

    public void createCustomer (String firstName, String lastName, String phoneNumber, String email) throws SQLException {
        customerManager.createCustomer(firstName, lastName, phoneNumber, email);
    }

    public void deleteCustomer(int id) {
        customerManager.deleteCustomer(id);
    }

    public void editCustomer(Customer customer) {
        customerManager.editCustomer(customer);
    }
}
