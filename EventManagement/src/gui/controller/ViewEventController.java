package gui.controller;

import be.Customer;
import be.ErrorHandling;
import be.Event;
import gui.model.CustomerModel;
import gui.model.EventCoordinatorModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewEventController implements Initializable {

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

    private EventCoordinatorModel eventCoordinatorModel;
    private CustomerModel customerModel;
    private Customer selectedCustomer;

    public ViewEventController() throws IOException {
        eventCoordinatorModel = new EventCoordinatorModel();
        customerModel = new CustomerModel();
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
        try {
            allCustomers = FXCollections.observableList(customerModel.getCustomers());
            tableViewLoadCustomer(allCustomers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tableViewLoadCustomer(ObservableList<Customer> allCustomers) {
        tvCustomers.setItems(getCustomerData());
    }

    private void tableViewCustomersOnEvent(ObservableList<Customer> allCustomersOnEvent) {
        tvCustomersOnEvent.setItems(getCustomerOnEventData());
    }

    private ObservableList<Customer> getCustomerOnEventData() {
        return allCustomers;
    }

    private ObservableList<Customer> getCustomerData() {
        return allCustomers;
    }





    private void selectedCustomer(){
        this.tvCustomers.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if ((Customer) newValue != null) {
                this.selectedCustomer = (Customer) newValue;
            }
        }));
    }


    public void onActionAddSelectedToEvent(ActionEvent actionEvent) {
        if (selectedCustomer != null) {
            try {
                //eventCoordinatorModel.addCustomerToEvent(selectedCustomer.getId(), selectedEvent.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setSelectedEvent(Event event) {

    }

    public void onActionCloseWindow() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
}
