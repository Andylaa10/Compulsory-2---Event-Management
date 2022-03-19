package dal;

import be.Customer;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.db.DatabaseConnector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    private final DatabaseConnector connector;

    public CustomerDAO() throws IOException {
        connector = new DatabaseConnector();
    }

    /**
     * Making a customer list, connecting to the database and adding the results to our ArrayList.
     * @return a list of customers or an empty list of customers
     */
    public List<Customer> getCustomers() {

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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allCustomers;
    }

}
