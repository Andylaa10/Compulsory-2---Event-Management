package gui.controller;

import be.Customer;
import be.Event;
import be.Ticket;
import bll.helpers.ErrorHandling;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import gui.model.CustomerModel;
import gui.model.EventCoordinatorModel;
import gui.model.TicketModel;
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
import java.util.Random;
import java.util.ResourceBundle;

public class ViewEventController implements Initializable {

    @FXML
    private Button btnTest;
    @FXML
    private Button btnSeeTicket;
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
    private TableColumn<Customer, Integer> tcCustomerIDOnEvent;
    @FXML
    private TableColumn<Customer, String> tcFirstNameOnEvent;
    @FXML
    private TableColumn<Customer, String> tcLastNameOnEvent;
    @FXML
    private TableColumn<Customer, String> tcPhoneNumberOnEvent;
    @FXML
    private TableColumn<Customer, String> tcEmailOnEvent;
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

    private ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    private ObservableList<Customer> allCustomersOnEvent = FXCollections.observableArrayList();

    private EventCoordinatorModel eventCoordinatorModel;
    private CustomerModel customerModel;
    private TicketModel ticketModel;
    private Customer selectedCustomer;
    private Customer selectedCustomerOnEvent;
    private Ticket selectedTicketOnEvent;
    private ErrorHandling errorHandling;
    private Event passedEvent;

    public ViewEventController() throws IOException, SQLException {
        this.eventCoordinatorModel = new EventCoordinatorModel();
        this.customerModel = new CustomerModel();
        this.errorHandling = new ErrorHandling();
        this.ticketModel = new TicketModel();
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
        passedEvent = event;
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
                createAndSaveTicket();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            errorHandling.noCustomerSelectedWarning();
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
        } else {
            errorHandling.noCustomerSelectedWarning();
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

    public void onActionSeeTicket(ActionEvent actionEvent) throws IOException, SQLServerException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/gui/view/TicketView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        //Parent root = FXMLLoader.load(getClass().getResource("/gui/view/TicketView.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Ticket");
        stage.setScene(scene);
        stage.show();
        TicketController tc = fxmlLoader.getController();
        tc.setEventData(passedEvent, selectedCustomerOnEvent);
    }



    public void createAndSaveTicket() throws SQLException {
        int eventID = Integer.parseInt(txtFieldEventID.getText());
        int customerID = selectedCustomer.getId();
        String generatedTicketID = GenerateID();

        System.out.println(eventID);
        System.out.println(customerID);
        System.out.println(generatedTicketID);

        ticketModel.createTicket(eventID, customerID, generatedTicketID);

    }

    private String GenerateID(){
        Random random = new Random();
        int idSize = 10;
        char[] arrayOfCharacter = {'1','2','3','4','5','6','7','8','9','0','Q','W','E','R','T','Y','U','I','O','P'
                ,'A','S','D','F','G','H','J','K','L','Z','X','C','V','B','N','M'};
        StringBuilder newValueID = new StringBuilder();

        for (int i = 0; i < idSize; i++) {
            int value = random.nextInt(arrayOfCharacter.length);
            char nextChar = arrayOfCharacter[value];
            newValueID.append(nextChar);
        }

        return newValueID.toString();
    }


}
