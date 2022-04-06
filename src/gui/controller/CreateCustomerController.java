package gui.controller;

import be.Customer;
import bll.helpers.ErrorHandling;
import gui.model.CustomerModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
    private TableView<Customer> tvCustomers;
    @FXML
    private TableColumn<Customer, Integer> tcCustomerID;
    @FXML
    private TableColumn<Customer, String> tcFirstName;
    @FXML
    private TableColumn<Customer, String> tcLastName;
    @FXML
    private TableColumn<Customer, String> tcPhoneNumber;
    @FXML
    private TableColumn<Customer, String> tcEmail;
    @FXML
    private TableColumn<Customer, String> tcStudy;
    @FXML
    private TableColumn<Customer, String> tcNote;
    @FXML
    private TextField txtFieldSearch;
    @FXML
    private TextField txtFieldLastName;
    @FXML
    private TextField txtFieldFirstName;
    @FXML
    private TextField txtFieldStudy;
    @FXML
    private TextField txtFieldPhoneNumber;
    @FXML
    private TextField txtFieldEmail;
    @FXML
    private TextField txtFieldNote;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnSearchCustomers;


    private ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    private ObservableList<Customer> searchData = FXCollections.observableArrayList();

    private boolean hasSearched = true;

    private CustomerModel customerModel;
    private ErrorHandling errorHandling;
    private Customer selectedCustomer;
    private EditCustomerController editCustomerController;


    public CreateCustomerController() throws IOException, SQLException {
        customerModel = new CustomerModel();
        errorHandling = new ErrorHandling();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTable();
        selectedCustomer();
    }

    private void initializeTable() {
        tcCustomerID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tcLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tcPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tcStudy.setCellValueFactory(new PropertyValueFactory<>("study"));
        tcNote.setCellValueFactory(new PropertyValueFactory<>("note"));
        try {
            allCustomers = FXCollections.observableList(customerModel.getCustomers());
            tableViewLoadCustomer(allCustomers);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    @FXML
    private void handleBtnAddCustomer() throws SQLException {
        if (!txtFieldFirstName.getText().isEmpty() && !txtFieldLastName.getText().isEmpty() && !txtFieldPhoneNumber.getText().isEmpty() && !txtFieldEmail.getText().isEmpty()){
            String customerFirstName = txtFieldFirstName.getText();
            String customerLastName = txtFieldLastName.getText();
            String customerPhoneNumber = txtFieldPhoneNumber.getText();
            String customerEmail = txtFieldEmail.getText();
            String customerStudy = txtFieldStudy.getText();
            String customerNote = txtFieldNote.getText();

            customerModel.createCustomer(customerFirstName, customerLastName, customerPhoneNumber, customerEmail, customerStudy, customerNote);
            reloadCustomerTable();
        } else {
            errorHandling.addCustomerError();
        }

    }

    @FXML
    private void handleBtnEditCustomer() throws IOException {
        if (selectedCustomer != null) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/gui/view/EditCustomer.fxml"));

            Scene scene = new Scene(fxmlLoader.load());

            Stage stage = new Stage();
            stage.setScene(scene);

            editCustomerController = fxmlLoader.getController();
            editCustomerController.setSelectedCustomer(selectedCustomer);

            stage.show();

            stage.setOnHiding(event ->
            {
                try {
                    allCustomers = FXCollections.observableList(customerModel.getCustomers());
                    tableViewLoadCustomer(allCustomers);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } else {
            errorHandling.noCustomerSelectedWarning();
        }
    }

    @FXML
    private void handleBtnDeleteCustomer(){
        if (selectedCustomer != null) {
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
        } else {
            errorHandling.noCustomerSelectedWarning();
        }
    }

    private void reloadCustomerTable() {
        try {
            int index = tvCustomers.getSelectionModel().getFocusedIndex();
            this.tvCustomers.setItems(FXCollections.observableList(customerModel.getCustomers()));
            tvCustomers.getSelectionModel().select(index);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void selectedCustomer() {
        this.tvCustomers.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                this.selectedCustomer = newValue;
            }
        }));
    }

    @FXML
    private void handleBtnBack() {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onActionSearchCustomers() {
        if (hasSearched == true && !txtFieldSearch.getText().equals("")) {
            btnSearchCustomers.setText("X");
            hasSearched = false;
        } else {
            btnSearchCustomers.setText("🔍");
            hasSearched = true;
            txtFieldSearch.clear();
        }
        try {
            searchData = FXCollections.observableList(customerModel.searchCustomers(txtFieldSearch.getText()));
            searchTableViewLoad(searchData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the tableview for customers, when search is pressed.
     * @param searchData
     */
    private void searchTableViewLoad(ObservableList<Customer> searchData) {
        tvCustomers.setItems(getSearchData());
    }

    /**
     * @return searchData;
     */
    private ObservableList<Customer> getSearchData() {
        return searchData;
    }

}
