package gui.controller;

import be.Customer;
import be.ErrorHandling;
import be.EventCoordinator;
import bll.EventCoordinatorManager;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import gui.model.CustomerModel;
import gui.model.EventCoordinatorModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class CreateCustomerController implements Initializable {

    @FXML
    public TableView<Customer> tvCustomers;
    @FXML
    public TableColumn<Customer, Integer> tcCustomerID;
    @FXML
    public TableColumn<Customer, String> tcFirstName;
    @FXML
    public TableColumn<Customer, String> tcLastName;
    @FXML
    public TableColumn<Customer, String> tcPhoneNumber;
    @FXML
    public TableColumn<Customer, String> tcEmail;
    @FXML
    public Button btnAddCustomer;
    @FXML
    public TextField txtFieldSearch;
    @FXML
    public TextField txtFieldLastName;
    @FXML
    public TextField txtFieldFirstName;
    @FXML
    public Button btnEditCustomer;
    @FXML
    public Button btnDeleteCustomer;
    @FXML
    public TextField txtFieldPhoneNumber;
    @FXML
    public TextField txtFieldEmail;
    @FXML
    public Button btnBack;


    private ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

    private CustomerModel customerModel;
    private ErrorHandling errorHandling;
    private Customer selectedCustomer;


    public CreateCustomerController() throws IOException, SQLException {
        customerModel = new CustomerModel();
        errorHandling = new ErrorHandling();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTable();
        selectedCustomer();
    }

    public void initializeTable() {
        tcCustomerID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tcLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tcPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        try {
            allCustomers = FXCollections.observableList(customerModel.getCustomers());
            tableViewLoadCustomer(allCustomers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void selectedCustomer(){
        this.tvCustomers.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if ((Customer) newValue != null) {
                this.selectedCustomer = (Customer) newValue;
            }
        }));
    }

    /**
     * Loading table view customer
     *
     * @param allCustomers
     */
    private void tableViewLoadCustomer(ObservableList<Customer> allCustomers) {
        tvCustomers.setItems(getCustomerData());
    }

    /**
     * returns the allCustomer list
     *
     * @return
     */
    private ObservableList<Customer> getCustomerData() {
        return allCustomers;
    }

    public void handleBtnAddCustomer() throws SQLException {
        if (!txtFieldFirstName.getText().isEmpty() && !txtFieldLastName.getText().isEmpty() && !txtFieldPhoneNumber.getText().isEmpty() && !txtFieldEmail.getText().isEmpty()){
            String customerFirstName = txtFieldFirstName.getText();
            String customerLastName = txtFieldLastName.getText();
            String customerPhoneNumber = txtFieldPhoneNumber.getText();
            String customerEmail = txtFieldEmail.getText();

            customerModel.createCustomer(customerFirstName, customerLastName, customerPhoneNumber, customerEmail);
            reloadCustomerTable();
        }else {
            errorHandling.addCustomerError();
        }

    }

    public void handleBtnEditCustomer(){
        //TODO Implement metode her til at edit
    }

    public void handleBtnDeleteCustomer(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("WARNING MESSAGE");
        alert.setHeaderText("Warning before you delete a customer");
        alert.setContentText(" To delete a customer, remove it from all events and tickets first!! \n Are you sure you want " +
                "to delete this customer?");
        if (selectedCustomer != null) {
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                selectedCustomer();
                customerModel.deleteCustomer(selectedCustomer.getId());
            } else {
                return;
            }
            try {
                allCustomers = FXCollections.observableList(customerModel.getCustomers());
                tableViewLoadCustomer(allCustomers);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void reloadCustomerTable() {
        try {
            int index = tvCustomers.getSelectionModel().getFocusedIndex();
            this.tvCustomers.setItems(FXCollections.observableList(customerModel.getCustomers()));
            tvCustomers.getSelectionModel().select(index);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


    /**
     * public void onActionCancelCreateCustomer() {
     * Stage stage = (Stage) btnCancelCreateCustomer.getScene().getWindow();
     * stage.close();
     * }
     */

    public void handleBtnBack() throws IOException {
        Stage switcher = (Stage) btnBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/AdminView.fxml"));
        switcher.setTitle("Admin Management");
        Scene scene = new Scene(root);
        switcher.setScene(scene);
    }
}
