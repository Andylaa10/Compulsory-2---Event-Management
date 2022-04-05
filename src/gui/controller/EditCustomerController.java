package gui.controller;

import be.Customer;
import bll.helpers.ErrorHandling;
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
    private TextField txtFieldCustomerFirstName;
    @FXML
    private TextField txtFieldCustomerLastName;
    @FXML
    private TextField txtFieldCustomerPhone;
    @FXML
    private TextField txtFieldCustomerEmail;
    @FXML
    private TextArea txtFieldCustomerNote;
    @FXML
    private TextField txtFieldCustomerID;
    @FXML
    private TextField txtFieldCustomerStudy;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnEditCustomer;

    private CustomerModel customerModel;
    private ErrorHandling errorHandling;

    public EditCustomerController() throws IOException, SQLException {
        this.customerModel = new CustomerModel();
        this.errorHandling = new ErrorHandling();
    }

    @FXML
    private void onActionSaveCustomer() {
        if (!txtFieldCustomerFirstName.getText().isEmpty() && !txtFieldCustomerLastName.getText().isEmpty() && !txtFieldCustomerPhone.getText().isEmpty()
                && !txtFieldCustomerEmail.getText().isEmpty()) {
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
        } else {
            errorHandling.editCustomerWarning();
        }

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

    @FXML
    private void handleBtnBack() {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
    }
}
