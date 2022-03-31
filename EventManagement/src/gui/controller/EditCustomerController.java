package gui.controller;

import be.Customer;
import gui.model.CustomerModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class EditCustomerController {


    @FXML
    public TextField txtFieldCustomerFirstName;
    @FXML
    public TextField txtFieldCustomerLastName;
    @FXML
    public TextField txtFieldCustomerPhone;
    @FXML
    public TextField txtFieldCustomerEmail;
    @FXML
    public TextArea txtFieldCustomerNote;
    @FXML
    public TextField txtFieldCustomerID;
    @FXML
    public TextField txtFieldCustomerStudy;
    @FXML
    public Button btnBack;
    @FXML
    public Button btnEditCustomer;

    CustomerModel customerModel;

    public EditCustomerController() throws IOException, SQLException {
        customerModel = new CustomerModel();
    }


    public void onActionSaveCustomer() {
        int customerID = Integer.parseInt(txtFieldCustomerID.getText());
        String customerFirstName = txtFieldCustomerFirstName.getText();
        String customerLastName = txtFieldCustomerLastName.getText();
        String customerPhoneNumber = txtFieldCustomerPhone.getText();
        String customerEmail = txtFieldCustomerEmail.getText();
        String customerStudy = txtFieldCustomerStudy.getText();
        String customerNotes = txtFieldCustomerNote.getText();


        Customer customer = new Customer(customerID, customerFirstName, customerLastName, customerPhoneNumber, customerEmail, customerStudy, customerNotes);
        customerModel.editCustomer(customer);

        Stage stage = (Stage) btnEditCustomer.getScene().getWindow();
        stage.close();
    }

    public void setSelectedCustomer(Customer customer) {
        txtFieldCustomerID.setText(String.valueOf(customer.getId()));
        txtFieldCustomerFirstName.setText(customer.getFirstName());
        txtFieldCustomerLastName.setText(customer.getLastName());
        txtFieldCustomerPhone.setText(customer.getPhoneNumber());
        txtFieldCustomerEmail.setText(customer.getEmail());
        txtFieldCustomerStudy.setText(customer.getStudy());
        txtFieldCustomerNote.setText(customer.getNote());

    }

    public void handleBtnBack() {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
    }
}
