package dal;

import be.Customer;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.db.DatabaseConnector;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    private final DatabaseConnector connector;

    public CustomerDAO() throws IOException {
        connector = new DatabaseConnector();
    }

    /**
     * Making a customer list, connecting to the database and adding the results to our ArrayList.
     *
     * @return a list of customers or an empty list of customers
     */
    public List<Customer> getCustomers() throws SQLException {

        ArrayList<Customer> allCustomers = new ArrayList<>();

        try (Connection connection = connector.getConnection()) {

            String sql = "SELECT * FROM Customer;";

            Statement statement = connection.createStatement();

            if (statement.execute(sql)) {
                ResultSet resultset = statement.getResultSet();
                while (resultset.next()) {
                    int customerID = resultset.getInt("CustomerID");
                    String firstName = resultset.getString("Fname");
                    String lastName = resultset.getString("Lname");
                    String phoneNumber = resultset.getString("PhoneNumber");
                    String email = resultset.getString("Email");

                    Customer customer = new Customer(customerID, firstName, lastName, phoneNumber, email);
                    allCustomers.add(customer);
                }
            }
        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        }
        return allCustomers;
    }


    /**
     * Creates a customer, by inserting a giving firstName, lastName, phoneNumber and email
     * @param firstName
     * @param lastName
     * @param phoneNumber
     * @param email
     * @return Customer
     * @throws SQLException
     */
    public Customer createCustomer (String firstName, String lastName, String phoneNumber, String email) throws SQLException {
            try (Connection connection = connector.getConnection()) {
                String sql = "INSERT INTO Customer (Fname, Lname, PhoneNumber, Email) values (?,?,?,?)";

                try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    preparedStatement.setString(1, firstName);
                    preparedStatement.setString(2, lastName);
                    preparedStatement.setString(3, phoneNumber);
                    preparedStatement.setString(4, email);
                    preparedStatement.execute();

                    ResultSet resultSet = preparedStatement.getGeneratedKeys();
                    int id = 0;
                    if (resultSet.next()) {
                        id = resultSet.getInt(1);
                    }

                    Customer customer = new Customer(id, firstName, lastName, phoneNumber, email);
                    return customer;

                }

            } catch (SQLServerException throwables) {
                throwables.printStackTrace();
                return null;
            }
    }

    /**
     * @param id
     * Deletes a customer by taken the id
     */
    public void deleteCustomer(int id) {
        try (Connection connection = connector.getConnection()) {
            String sql = "DELETE FROM Customer WHERE CustomerID =?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() != 1) {
                throw new Exception("Could not delete customer");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param customer
     * Edits a customer
     */
    public void editCustomer(Customer customer) {
        try (Connection connection = connector.getConnection()) {
            String sql = "UPDATE Customer SET Fname=?, Lname=?, PhoneNumber=?, Email=? WHERE CustomerID=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getPhoneNumber());
            preparedStatement.setString(4, customer.getEmail());
            preparedStatement.setInt(5, customer.getId());
            if (preparedStatement.executeUpdate() != 1) {
                throw new Exception("Could not edit customer");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for testing the DAO class
     */
    public static void main(String[] args) throws IOException, SQLException {
        CustomerDAO customerDAO = new CustomerDAO();
        //customerDAO.createCustomer("Test", "Person", "+45 12345678", "KrisianTester@gmail.com");
        List<Customer> customers = customerDAO.getCustomers();
        System.out.println(customers);
    }
}
