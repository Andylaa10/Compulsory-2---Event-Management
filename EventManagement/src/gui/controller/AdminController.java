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
    public Button btnViewEmployee;
    @FXML
    public Button btnCreateCustomer;
    @FXML
    public Button btnCreateEvent;
    @FXML
    public Button btnHelp;
    @FXML
    public Button btnLogout;

    private ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    private ObservableList<Event> allEvents = FXCollections.observableArrayList();

    private CustomerModel customerModel;
    private EventModel eventModel;
    private Event selectedEvent;


    public AdminController() throws IOException {
        customerModel = new CustomerModel();
        eventModel = new EventModel();
        selectedEvent = new Event();
    }

    public void LogOutFromAdmin() throws IOException {
        Stage switcher = (Stage) btnLogout.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/FrontPage.fxml"));
        switcher.setTitle("Event Management");
        Scene scene = new Scene(root);
        switcher.setScene(scene);
    }

    /**
     * This helps you, if you cannot remember or have problems with login
     */
    public void help() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Please contact the administration");
        alert.setHeaderText("Please contact the administration");
        alert.setContentText("Contact the administration for help");
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // tcFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        //  tcPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
        // tcEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));

        //   try {
        //      allCustomers = FXCollections.observableArrayList(customerModel.getCustomers());
        //        tableViewLoadCustomers(allCustomers);
        //   } catch (SQLException sqlException) {
        //       sqlException.printStackTrace();
        // }

    }


    /**
     * Loading table view customers
     *
     * @param allCustomers
     */

    //public void tableViewLoadCustomers(ObservableList<Customer> allCustomers) {
    //  tvCustomers.setItems(getCustomersData());
    // }

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
                //tableViewLoadCustomers(allCustomers);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Deletes an event from the table
     */
    // public void handleBtnDeleteEvent(ActionEvent actionEvent) {
    //   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    //  alert.setTitle("WARNING MESSAGE");
    //  alert.setHeaderText("Warning before you delete event");
    //  alert.setContentText(" Remove all customer and tickets from selected event to delete!! \n Are you sure you want " +
    //          "to delete this movie?");
    //  if (selectedEvent != null) {
    //      Optional<ButtonType> result = alert.showAndWait();
    //      if (result.get() == ButtonType.OK) {
    //          selectedEvent();
    //          eventModel.deleteEvent(selectedEvent.getId());
    //      }
    //  } else {
    //      return;
    //  }
    //  try {
    //      allEvents = FXCollections.observableList(eventModel.getEvents());
    //      tableViewLoadEvents(allEvents);
    //  } catch (Exception e) {
    //      e.printStackTrace();
    //  }
    //}
}
