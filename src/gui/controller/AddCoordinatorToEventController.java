package gui.controller;

import be.Event;
import be.EventCoordinator;
import bll.helpers.ErrorHandling;
import gui.model.AdminModel;
import gui.model.EventModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddCoordinatorToEventController implements Initializable {

    @FXML
    private Button btnBack;

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
    private ObservableList<EventCoordinator> allCoordinatorOnEvent = FXCollections.observableArrayList();
    private ObservableList<Event> allEvents = FXCollections.observableArrayList();

    private AdminModel adminModel;
    private EventModel eventModel;

    private EventCoordinator selectedCoordinator;
    private EventCoordinator selectedCoordinatorOnEvent;
    private Event selectedEvent;
    private ErrorHandling errorHandling;

    /**
     * Constructor
     * @throws IOException
     */
    public AddCoordinatorToEventController() throws IOException {
        this.selectedCoordinator = new EventCoordinator();
        this.selectedCoordinatorOnEvent = new EventCoordinator();
        this.selectedEvent = new Event();
        this.adminModel = new AdminModel();
        this.eventModel = new EventModel();
        this.errorHandling = new ErrorHandling();
    }

    /**
     * Goes back to the previous view
     */
    @FXML
    private void handleBtnBack() {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
    }

    /**
     * Runs the methods inside when this view appears
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTable();
        selectedCoordinator();
        selectedCoordinatorOnEvent();
        selectedEvent();
    }

    /**
     * Loading the tableviews
     */
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
     * Makes the tableColumn show the coordinator on each event
     */
    public void seeCoordinatorsOnEvent() {
        tcCoordinatorOnEvent.setCellValueFactory(new PropertyValueFactory<>("username"));
        try {
            allCoordinatorOnEvent = FXCollections.observableList(adminModel.getCoordinatorsOnEvent(selectedEvent.getId()));
            tableViewLoadCoordinatorOnEvent(allCoordinatorOnEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Adding a selected coordinator to a selected event
     */
    @FXML
    private void handleBtnAddSelectedToEvent() {
        if (selectedCoordinator != null) {
            try {
                adminModel.addCoordinatorToEvent(selectedEvent.getId(), selectedCoordinator.getId());
                reloadCoordinatorOnEvent();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            errorHandling.noCoordinatorSelectedWarning();
        }
    }

    /**
     * Deletes coordinator from event
     */
    @FXML
    private void handleBtnDeleteSelectedFromEvent() {
        if (selectedEvent != null && selectedCoordinatorOnEvent != null) {
            try {
                int index = tvCoordinatorOnEvent.getSelectionModel().getFocusedIndex();
                adminModel.deleteFromEvent(selectedCoordinatorOnEvent.getId(), selectedEvent.getId());
                reloadCoordinatorOnEvent();
                tvCoordinatorOnEvent.getSelectionModel().select(index > 0 ? index - 1 : index);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            errorHandling.noCoordinatorSelectedWarning();
        }
    }

    /**
     * reloads the coordinator on the event in view to reflect changes
     */
    public void reloadCoordinatorOnEvent() {
        try {
            int index = tvCoordinatorOnEvent.getSelectionModel().getFocusedIndex();
            this.tvCoordinatorOnEvent.setItems(FXCollections.observableList(adminModel.getCoordinatorsOnEvent(selectedEvent.getId())));
            tvCoordinatorOnEvent.getSelectionModel().select(index);
        } catch (Exception exception) {
            exception.printStackTrace();
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

    /**
     * Loading table view events
     * @param allCoordinatorOnEvent
     */
    private void tableViewLoadCoordinatorOnEvent(ObservableList<EventCoordinator> allCoordinatorOnEvent) {
        tvCoordinatorOnEvent.setItems(getCoordinatorOnEventData());
    }

    /**
     * returns the allEvents list
     * @return
     */
    private ObservableList<EventCoordinator> getCoordinatorOnEventData() {
        return allCoordinatorOnEvent;
    }

    /**
     * Selecting a coordinator
     */
    private void selectedCoordinator() {
        this.tvCoordinators.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if ((EventCoordinator) newValue != null) {
                this.selectedCoordinator = (EventCoordinator) newValue;
            }
        }));
    }

    /**
     * Selecting a coordinator on event
     */
    private void selectedCoordinatorOnEvent(){
        this.tvCoordinatorOnEvent.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            this.selectedCoordinatorOnEvent = (EventCoordinator) newValue;
            if (selectedCoordinatorOnEvent != null) {
                this.tvCoordinators.getSelectionModel().clearSelection();
            }
        }));
    }

    /**
     * Selecting an event
     */
    private void selectedEvent(){
        this.tvEvents.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if ((Event) newValue != null) {
                this.selectedEvent = (Event) newValue;
                seeCoordinatorsOnEvent();
            }
        }));
    }
}

