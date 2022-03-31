package gui.controller;

import be.Event;
import be.EventCoordinator;
import gui.model.AdminModel;
import gui.model.EventModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddCoordinatorToEventController implements Initializable {

    @FXML
    private Button btnBack;
    @FXML
    private Button btnAddSelectedToEvent;
    @FXML
    private Button btnDeleteSelectedFromEvent;
    @FXML
    private Button btnCreateCoordinator;

    @FXML
    private TableView<EventCoordinator> tvCoordinatorOnEvent;
    @FXML
    private TableView<Event> tvEvents;
    @FXML
    private TableView<EventCoordinator> tvCoordinators;

    @FXML
    private TableColumn<EventCoordinator, String> tcCoordinators;
    @FXML
    private TableColumn<Event, String> tcEventName;
    @FXML
    private TableColumn<Event, String> tcEventPrice;
    @FXML
    private TableColumn<Event, String> tcEventDate;
    @FXML
    private TableColumn<Event, String> tcEventTime;
    @FXML
    private TableColumn<Event, String> tcEventTimeEnd;
    @FXML
    private TableColumn<Event, String> tcEventLocation;
    @FXML
    private TableColumn<Event, String> tcEventInfo;
    @FXML
    private TableColumn<EventCoordinator, String> tcCoordinatorOnEvent;


    private ObservableList<EventCoordinator> allCoordinators = FXCollections.observableArrayList();
    private ObservableList<Event> allEvents = FXCollections.observableArrayList();

    private AdminModel adminModel = new AdminModel();
    private EventModel eventModel = new EventModel();

    public AddCoordinatorToEventController() throws SQLException, IOException {
    }

    public void handleBtnBack() throws IOException {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
    }

    public void handleBtnAddSelectedToEvent() {
    }

    public void handleBtnDeleteSelectedFromEvent() {
    }

    public void handleBtnCreateCoordinator() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTable();
    }

    public void initializeTable(){
        tcCoordinators.setCellValueFactory(new PropertyValueFactory<>("username"));
        try {
            allCoordinators = FXCollections.observableList(adminModel.getCoordinator());
            tableViewLoadCoordinator(allCoordinators);
        } catch (Exception e) {
            e.printStackTrace();
        }

        tcEventName.setCellValueFactory(new PropertyValueFactory<>("EventName"));
        tcEventDate.setCellValueFactory(new PropertyValueFactory<>("EventDate"));
        tcEventLocation.setCellValueFactory(new PropertyValueFactory<>("EventLocation"));
        tcEventTime.setCellValueFactory(new PropertyValueFactory<>("EventTime"));
        tcEventTimeEnd.setCellValueFactory(new PropertyValueFactory<>("EventTimeEnd"));
        tcEventInfo.setCellValueFactory(new PropertyValueFactory<>("EventInfo"));
        tcEventPrice.setCellValueFactory(new PropertyValueFactory<>("EventPrice"));
        try {
            allEvents = FXCollections.observableArrayList(eventModel.getEvents());
            tableViewLoadEvents(allEvents);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loading table view coordinators
     * @param allCoordinators
     */
    private void tableViewLoadCoordinator(ObservableList<EventCoordinator> allCoordinators) {
        tvCoordinators.setItems(getCoordinatorData());
    }

    /**
     * returns the allCoordinator list
     * @return
     */
    private ObservableList<EventCoordinator> getCoordinatorData() {
        return allCoordinators;
    }

    /**
     * Loading table view events
     * @param allEvents
     */
    private void tableViewLoadEvents(ObservableList<Event> allEvents) {
        tvEvents.setItems(getEventData());
    }

    /**
     * returns the allEvents list
     * @return
     */
    private ObservableList<Event> getEventData() {
        return allEvents;
    }
}
