package gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

public class AdminController{

    @FXML
    private Button btnCreateCoordinator;
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

    public AdminController() {
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

    public void handleBtnCreateCustomer() throws IOException {
        Stage switcher = (Stage) btnCreateCustomer.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/CreateUserView.fxml"));
        Scene scene = new Scene(root);
        switcher.setTitle("Customer Management");
        switcher.setScene(scene);
    }

    /**
     * Mangler Edit Coordinator view
     * @throws IOException
     *
    //TODO
    //public void tableViewLoadCustomers(ObservableList<Customer> allCustomers) {
    //  tvCustomers.setItems(getCustomersData());
    // }


    public void handleBtnCreateCustomer() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/CreateCustomerView.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Create customer");
        stage.setScene(new Scene(root));
        stage.show();
    }


    //TODO Mangler Edit Coordinator view
    public void handleBtnViewCoordinator() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/"));
        Stage stage = new Stage();
        stage.setTitle("View coordinators");
        stage.setScene(new Scene(root));
        stage.show();
    }*/

    public void handleBtnCreateEvent() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/CreateEventView.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Create Event");
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

    /**
     *
     * @throws IOException
     */
    public void handleBtnCreateCoordinator( ) throws IOException {
        Stage switcher = (Stage) btnCreateCoordinator.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/CreateCoordinatorView.fxml"));
        Scene scene = new Scene(root);
        switcher.setTitle("Event Coordinator Management");
        switcher.setScene(scene);
    }
}
