package gui.controller;

import be.Event;
import be.EventCoordinator;
import gui.model.EventModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EventCoordinatorViewController implements Initializable, IController {
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnEvents;
    @FXML
    private Button btnLogOut;

    private ObservableList<Event> allEvents = FXCollections.observableArrayList();

    private EventModel eventModel;
    private EventCoordinator coordinator;


    public EventCoordinatorViewController() throws IOException {
        eventModel = new EventModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void onActionViewEvents() throws IOException {
        Stage switcher = (Stage) btnEvents.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/EventsOverview.fxml"));
        Scene scene = new Scene(root);
        switcher.setTitle("Events");
        switcher.setScene(scene);
        try {
            allEvents = FXCollections.observableArrayList(eventModel.getEventsCoordinator(coordinator.getId()));
        } catch (Exception e){
            e.printStackTrace();
        }
    }




    @FXML
    private void onActionCustomers() throws IOException {
        Stage switcher = (Stage) btnCustomers.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/CreateCustomer.fxml"));
        Scene scene = new Scene(root);
        switcher.setTitle("Customer Management");
        switcher.setScene(scene);
    }

    @FXML
    private void onActionLogOut() throws IOException {
        Stage switcher = (Stage) btnLogOut.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/FrontPage.fxml"));
        switcher.setTitle("Event Management");
        Scene scene = new Scene(root);
        switcher.setScene(scene);
    }

    @Override
    public void setEventCoordinator(EventCoordinator eventCoordinator) {
        coordinator = eventCoordinator;
    }
}
