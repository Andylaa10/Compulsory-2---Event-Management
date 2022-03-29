package bll.helpers;

import be.Customer;
import java.util.ArrayList;
import java.util.List;

public class UserSearcher {

    /**
     * Method for searching an event with the search button.
     *
     * @param searchBase
     * @param query
     * @return
     */
    public List<Customer> search(List<Customer> searchBase, String query) {
        List<Customer> searchResult = new ArrayList<>();

        for (Customer customer : searchBase) {
            if (compareToCustomerFirstName(query, customer) | compareToCustomerLastName(query, customer) | compareToCustomerEmail(query, customer) | compareToCustomerPhoneNumber(query, customer)) {
                searchResult.add(customer);
            }
        }
        return searchResult;
    }


    private boolean compareToCustomerFirstName(String query, Customer customer) {
        return customer.getFirstName().toLowerCase().contains(query.toLowerCase());
    }

    private boolean compareToCustomerLastName(String query, Customer customer) {
        return customer.getLastName().toLowerCase().contains(query.toLowerCase());
    }

    private boolean compareToCustomerEmail(String query, Customer customer) {
        return customer.getEmail().toLowerCase().contains(query.toLowerCase());
    }

    private boolean compareToCustomerPhoneNumber(String query, Customer customer) {
        return customer.getPhoneNumber().contains(query);
    }

}
