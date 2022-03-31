package gui.controller;

import be.Customer;
import be.Event;
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
import java.util.ResourceBundle;

public class ViewEventController implements Initializable {

    public Button btnTest;
    @FXML
    private TextField txtFieldEventID;
    @FXML
    private Button btnCreateCustomer;
    @FXML
    private Button btnDeleteSelectedFromEvent;
    @FXML
    private Button btnAddSelectedToEvent;
    @FXML
    private Button btnClose;
    @FXML
    private Label lblCustomerOnEvent;
    @FXML
    private Label lblCustomerList;
    @FXML
    private TableView<Customer> tvCustomers;
    @FXML
    private TableView<Customer> tvCustomersOnEvent;
    @FXML
    private TableColumn tcCustomerIDOnEvent;
    @FXML
    private TableColumn tcFirstNameOnEvent;
    @FXML
    private TableColumn tcLastNameOnEvent;
    @FXML
    private TableColumn tcPhoneNumberOnEvent;
    @FXML
    private TableColumn tcEmailOnEvent;
    @FXML
    private TableColumn tcCustomerID;
    @FXML
    private TableColumn tcFirstName;
    @FXML
    private TableColumn tcLastName;
    @FXML
    private TableColumn tcPhoneNumber;
    @FXML
    private TableColumn tcEmail;

    private ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    private ObservableList<Customer> allCustomersOnEvent = FXCollections.observableArrayList();

    private EventCoordinatorModel eventCoordinatorModel;
    private CustomerModel customerModel;
    private Customer selectedCustomer;
    private Customer selectedCustomerOnEvent;

    public ViewEventController() throws IOException, SQLException {
        this.eventCoordinatorModel = new EventCoordinatorModel();
        this.customerModel = new CustomerModel();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTable();
        selectedCustomer();
        selectedCustomerOnEvent();
    }

    private void initializeTable() {
        tcCustomerIDOnEvent.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcFirstNameOnEvent.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tcLastNameOnEvent.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tcPhoneNumberOnEvent.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        tcEmailOnEvent.setCellValueFactory(new PropertyValueFactory<>("email"));

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

    private ObservableList<Customer> getCustomerData() {
        return allCustomers;
    }

    private void tableViewLoadCustomer(ObservableList<Customer> allCustomers) {
        tvCustomers.setItems(getCustomerData());
    }

    private ObservableList<Customer> getCustomerOnEventData() {
        return allCustomersOnEvent;
    }

    private void tableViewCustomersOnEvent(ObservableList<Customer> allCustomersOnEvent) {
        tvCustomersOnEvent.setItems(getCustomerOnEventData());
    }

    private void selectedCustomer(){
        this.tvCustomers.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if ((Customer) newValue != null) {
                this.selectedCustomer = (Customer) newValue;
            }
        }));
    }

    private void selectedCustomerOnEvent(){
        this.tvCustomersOnEvent.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if ((Customer) newValue != null) {
                this.selectedCustomerOnEvent = (Customer) newValue;
            }
        }));
    }

    public void setSelectedEvent(Event event) {
        txtFieldEventID.setText(String.valueOf(event.getId()));
        try {
            allCustomersOnEvent = FXCollections.observableList(eventCoordinatorModel.getCustomersOnEvent(Integer.parseInt(txtFieldEventID.getText())));
            tableViewCustomersOnEvent(allCustomersOnEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onActionAddSelectedToEvent() {
        if (selectedCustomer != null) {
            try {
                eventCoordinatorModel.addCustomerToEvent(selectedCustomer.getId(), Integer.parseInt(txtFieldEventID.getText()));
                reloadCustomersOnEvent();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void onActionDeleteSelectedFromEvent() {
        if (selectedCustomerOnEvent != null) {
            try {
                eventCoordinatorModel.deleteFromEvent(selectedCustomerOnEvent.getId(), Integer.parseInt(txtFieldEventID.getText()));
                reloadCustomersOnEvent();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * reloads the customers on the event in view to reflect changes
     */
    public void reloadCustomersOnEvent() {
        try {
            int index = tvCustomersOnEvent.getSelectionModel().getFocusedIndex();
            this.tvCustomersOnEvent.setItems(FXCollections.observableList(eventCoordinatorModel.getCustomersOnEvent(Integer.parseInt(txtFieldEventID.getText()))));
            tvCustomersOnEvent.getSelectionModel().select(index);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


    @FXML
    private void onActionCreateCustomer() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/CreateCustomer.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Manage Customers");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void onActionCloseWindow() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
}
