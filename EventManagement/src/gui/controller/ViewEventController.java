package gui.controller;

import be.Customer;
import be.ErrorHandling;
import be.Event;
import dal.EventCoordinatorDAO;
import dal.EventDAO;
import gui.model.CustomerModel;
import gui.model.EventCoordinatorModel;
import gui.model.EventModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

    public ViewEventController() throws IOException, SQLException {
        this.eventCoordinatorModel = new EventCoordinatorModel();
        this.customerModel = new CustomerModel();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTable();
        selectedCustomer();
    }

    private void initializeTable() {
        tcCustomerIDOnEvent.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcFirstNameOnEvent.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tcLastNameOnEvent.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tcPhoneNumberOnEvent.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        tcEmailOnEvent.setCellValueFactory(new PropertyValueFactory<>("email"));
        try {
            allCustomersOnEvent = FXCollections.observableList(eventCoordinatorModel.getCustomersOnEvent(Integer.parseInt(txtFieldEventID.getText())));
            tableViewCustomersOnEvent(allCustomersOnEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }


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

    public void setSelectedEvent(Event event) {
        txtFieldEventID.setText(String.valueOf(event.getId()));
    }

    public void onActionAddSelectedToEvent() {
        if (selectedCustomer != null) {
            try {
                eventCoordinatorModel.addCustomerToEvent(selectedCustomer.getId(), Integer.parseInt(txtFieldEventID.getText()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onActionDeleteSelectedFromEvent() {
        if (selectedCustomer != null) {
            try {
                eventCoordinatorModel.deleteFromEvent(selectedCustomer.getId(), Integer.parseInt(txtFieldEventID.getText()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onActionCreateCustomer() throws IOException {
        Stage switcher = (Stage) btnCreateCustomer.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/CreateCustomerView.fxml"));
        Scene scene = new Scene(root);
        switcher.setTitle("Customer Management");
        switcher.setScene(scene);
    }

    public void onActionCloseWindow() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    public void onActionTest() {
        try {
            allCustomersOnEvent = FXCollections.observableList(eventCoordinatorModel.getCustomersOnEvent(Integer.parseInt(txtFieldEventID.getText())));
            tableViewCustomersOnEvent(allCustomersOnEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
