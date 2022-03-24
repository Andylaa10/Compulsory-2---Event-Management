package gui.controller;

import be.Event;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EventCoordinatorViewController implements Initializable {

    @FXML
    public Button btnAddEvent;
    @FXML
    public Button btnDeleteEvent;
    @FXML
    public Button btnViewEvent;
    @FXML
    public Button btnQuit;
    @FXML
    public TextField txtFieldSearch;
    @FXML
    public Button btnHelp;
    @FXML
    public Button btnLogOut;
    @FXML
    public TableView tvEvents;
    @FXML
    public TableColumn tcId;
    @FXML
    public TableColumn tcEventName;
    @FXML
    public TableColumn tcEventDate;
    @FXML
    public TableColumn tcEventLocation;
    @FXML
    public TableColumn tcEventTime;
    @FXML
    public Button btnEditEvent;
    @FXML
    public TableColumn tcEventInfo;

    private ObservableList<Event> allEvents = FXCollections.observableArrayList();

    private EventCoordinatorModel eventCoordinatorModel;
    private EventModel eventModel;

    public EventCoordinatorViewController() throws IOException {
        this.eventCoordinatorModel = new EventCoordinatorModel();
        this.eventModel = new EventModel();
    }



    public void LogOutFromEventCoordinator() throws IOException {
        Stage switcher = (Stage) btnLogOut.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/FrontPage.fxml"));
        switcher.setTitle("Event Management");
        Scene scene = new Scene(root);
        switcher.setScene(scene);
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

    public void onActionAddEvent() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/CreateEventView.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Create Event");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void onActionEditEvent() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/EditEventView.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Edit Event");
        stage.setScene(new Scene(root));
        stage.show();
    }
    // public void onActionCreateUser() throws IOException {
    //  Parent root = FXMLLoader.load(getClass().getResource("/gui/view/CreateUserView.fxml"));
    //  Stage stage = new Stage();
    //  stage.setTitle("Create User");
    //  stage.setScene(new Scene(root));
    //  stage.show();

    //}
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcEventName.setCellValueFactory(new PropertyValueFactory<>("EventName"));
        tcEventDate.setCellValueFactory(new PropertyValueFactory<>("EventDate"));
        tcEventLocation.setCellValueFactory(new PropertyValueFactory<>("EventLocation"));
        tcEventTime.setCellValueFactory(new PropertyValueFactory<>("EventTime"));
        tcEventInfo.setCellValueFactory(new PropertyValueFactory<>("EventInfo"));

        try {
            allEvents = FXCollections.observableArrayList(eventModel.getEvents());
            tableViewLoadEvents(allEvents);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void help() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Please contact the administration");
        alert.setHeaderText("Please contact the administration");
        alert.setContentText("Contact the administration for help");
        alert.showAndWait();
    }

    public void Quit() {
        System.exit(0);
    }
}
