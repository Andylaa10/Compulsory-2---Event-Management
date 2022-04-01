package gui.controller;

import be.Event;
import be.EventCoordinator;
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
    private ObservableList<EventCoordinator> allCoordinatorOnEvent = FXCollections.observableArrayList();
    private ObservableList<Event> allEvents = FXCollections.observableArrayList();

    private AdminModel adminModel = new AdminModel();
    private EventModel eventModel = new EventModel();

    private EventCoordinator selectedCoordinator = new EventCoordinator();
    private EventCoordinator selectedCoordinatorOnEvent = new EventCoordinator();
    private Event selectedEvent = new Event();

    public AddCoordinatorToEventController() throws SQLException, IOException {
    }

    public void handleBtnBack() throws IOException {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTable();
        selectedCoordinator();
        selectedCoordinatorOnEvent();
        selectedEvent();
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

    @FXML
    private void handleBtnAddSelectedToEvent() {
        if (selectedCoordinator != null) {
            try {
                adminModel.addCoordinatorToEvent(selectedEvent.getId(), selectedCoordinator.getId());
                reloadCoordinatorOnEvent();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

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

    private void selectedCoordinator() {
        this.tvCoordinators.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if ((EventCoordinator) newValue != null) {
                this.selectedCoordinator = (EventCoordinator) newValue;
            }
        }));
    }

    private void selectedCoordinatorOnEvent(){
        this.tvCoordinatorOnEvent.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            this.selectedCoordinatorOnEvent = (EventCoordinator) newValue;
            if (selectedCoordinatorOnEvent != null) {
                this.tvCoordinators.getSelectionModel().clearSelection();
            }
        }));
    }

    private void selectedEvent(){
        this.tvEvents.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if ((Event) newValue != null) {
                this.selectedEvent = (Event) newValue;
                seeCoordinatorsOnEvent();
            }
        }));
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
     * Reloads the event table to reflect changes
     */
    public void reloadEventTable() {
        try {
            int index = tvEvents.getSelectionModel().getFocusedIndex();
            this.tvEvents.setItems(FXCollections.observableList(eventModel.getEvents()));
            tvEvents.getSelectionModel().select(index);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}
