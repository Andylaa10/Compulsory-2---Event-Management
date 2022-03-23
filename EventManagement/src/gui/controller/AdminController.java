package gui.controller;

import be.Customer;
import be.Event;
import gui.model.CustomerModel;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML
    private TableView<Customer> tvCustomers;

    @FXML
    private TableColumn<Customer, String> tcFirstName;

    @FXML
    private TableColumn<Customer, String> tcPhoneNumber;

    @FXML
    private TableColumn<Customer, String> tcEmail;

    @FXML
    private TableView<Event> tvEvents;

    @FXML
    private TableColumn<Event, String> tcEventName;

    @FXML
    private Button btnLogOutFromAdmin;

    @FXML
    private Button btnCreateUser;

    @FXML
    private Button btnDeleteEvent;

    private ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    private ObservableList<Event> allEvents = FXCollections.observableArrayList();

    private CustomerModel customerModel;
    private EventModel eventModel;

    private Event selectedEvent = new Event();


    public AdminController() throws IOException {
        customerModel = new CustomerModel();
        eventModel = new EventModel();
    }

    public void LogOutFromAdmin() throws IOException {

        Stage switcher = (Stage) btnLogOutFromAdmin.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/LoginView.fxml"));
        switcher.setTitle("Event Management");
        Scene scene = new Scene(root);
        switcher.setScene(scene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tcFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tcPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));

        try {
            allCustomers = FXCollections.observableArrayList(customerModel.getCustomers());
            tableViewLoadCustomers(allCustomers);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        tcEventName.setCellValueFactory(new PropertyValueFactory<>("eventName"));

        try {
            allEvents = FXCollections.observableArrayList(eventModel.getEvents());
            tableViewLoadEvents(allEvents);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Loading table view events
     *
     * @param allEvents
     */
    private void tableViewLoadEvents(ObservableList<Event> allEvents) {
        tvEvents.setItems(getEventData());
    }

    private ObservableList<Event> getEventData() {
        return allEvents;
    }

    /**
     * Loading table view customers
     *
     * @param allCustomers
     */
    public void tableViewLoadCustomers(ObservableList<Customer> allCustomers) {
        tvCustomers.setItems(getCustomersData());
    }

    /**
     * returns the allCustomers list
     *
     * @return
     */
    public ObservableList<Customer> getCustomersData() {
        return allCustomers;
    }

    public void handleBtnCreateUser() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/CreateUserView.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Add Customer");
        stage.setScene(new Scene(root));
        stage.show();
        stage.setOnHiding(event ->
        {
            try {
                allCustomers = FXCollections.observableList(customerModel.getCustomers());
                tableViewLoadCustomers(allCustomers);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Deletes an event from the table
     */
    public void handleBtnDeleteEvent(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("WARNING MESSAGE");
        alert.setHeaderText("Warning before you delete event");
        alert.setContentText(" Remove all customer and tickets from selected event to delete!! \n Are you sure you want " +
                "to delete this movie?");
        if (selectedEvent != null) {
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                selectedEvent();
                eventModel.deleteEvent(selectedEvent.getId());
            }
        } else {
            return;
        }
        try {
            allEvents = FXCollections.observableList(eventModel.getEvents());
            tableViewLoadEvents(allEvents);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * Makes you able to select an event from the table
     */
    private void selectedEvent() {
        this.tvEvents.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if ((Event) newValue != null) {
                this.selectedEvent = (Event) newValue;
            }
        }));

    }
}
