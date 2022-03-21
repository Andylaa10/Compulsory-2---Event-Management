package bll;

import be.Customer;
import dal.CustomerDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CustomerManager {

    private CustomerDAO customerDAO;

    public CustomerManager() throws IOException {
        customerDAO = new CustomerDAO();
    }


    public List<Customer> getCustomers() throws SQLException {
        List<Customer> allCustomers = customerDAO.getCustomers();
        return allCustomers;
    }

    public void createCustomer (String firstName, String lastName, String phoneNumber, String email) throws SQLException {
        customerDAO.createCustomer(firstName, lastName, phoneNumber, email);
    }

    public void deleteCustomer(int id) {
        customerDAO.deleteCustomer(id);
    }

    public void editCustomer(Customer customer) {
        customerDAO.editCustomer(customer);
    }
}
