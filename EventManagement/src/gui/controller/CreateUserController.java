package gui.controller;

import gui.model.CustomerModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class CreateUserController {


    @FXML
    private TextField txtFieldCustomerFirstName;
    @FXML
    private TextField txtFieldCustomerLastName;
    @FXML
    private TextField txtFieldCustomerPhoneNumber;
    @FXML
    private TextField txtFieldCustomerEmail;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancelCreateCustomer;


    CustomerModel customerModel;

    public CreateUserController() throws IOException {
        this.customerModel = new CustomerModel();
    }

    public void handleBtnSave() throws SQLException {
        String customerFirstName = txtFieldCustomerFirstName.getText();
        String customerLastName = txtFieldCustomerLastName.getText();
        String customerPhoneNumber = txtFieldCustomerPhoneNumber.getText();
        String customerEmail = txtFieldCustomerEmail.getText();

        customerModel.createCustomer(customerFirstName, customerLastName, customerPhoneNumber, customerEmail);
        Stage stage = (Stage) btnSave.getScene().getWindow();
        stage.close();
    }

    public void onActionCancelCreateCustomer() {
        Stage stage = (Stage) btnCancelCreateCustomer.getScene().getWindow();
        stage.close();
    }
}
