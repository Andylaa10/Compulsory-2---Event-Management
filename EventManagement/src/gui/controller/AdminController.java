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
import javafx.stage.Stage;
import java.io.IOException;

public class AdminController{


    @FXML
    private Button btnViewEmployee;
    @FXML
    private Button btnCreateCustomer;
    @FXML
    private Button btnCreateEvent;
    @FXML
    private Button btnHelp;
    @FXML
    private Button btnLogOut;



    public AdminController() throws IOException {
    }

    public void LogOutFromAdmin() throws IOException {
        Stage switcher = (Stage) btnLogOut.getScene().getWindow();
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

    public void handleBtnCreateCustomer() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/CreateUserView.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Add Customer");
        stage.setScene(new Scene(root));
        stage.show();

    }

    /**
     * Mangler Edit Coordinator view
     * @throws IOException
     */
    public void handleBtnViewCoordinator() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/"));
        Stage stage = new Stage();
        stage.setTitle("Add Customer");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void handleBtnCreateEvent() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/CreateEventView.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Add Customer");
        stage.setScene(new Scene(root));
        stage.show();
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

    public void handleBtnCreateCoordinator( ) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/CreateCoordinatorView.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Add Customer");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
