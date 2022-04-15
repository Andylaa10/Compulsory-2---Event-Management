package gui.controller;

import be.Customer;
import be.Event;
import be.EventCoordinator;
import bll.helpers.ErrorHandling;
import gui.model.EventModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class EventsOverViewController implements Initializable, IController {

    @FXML
    private Button btnSearchEvents;
    @FXML
    private Button btnAddEvent;
    @FXML
    private Button btnLogOut;

    @FXML
    private TextField tfFieldSearch;

    @FXML
    private TableView<Event> tvEvents;

    @FXML
    private TableColumn<Event, String> tcEventName;
    @FXML
    private TableColumn<Event, String> tcEventDate;
    @FXML
    private TableColumn<Event, String> tcEventLocation;
    @FXML
    private TableColumn<Event, String> tcEventTime;
    @FXML
    private TableColumn<Event, String> tcEventTimeEnd;
    @FXML
    private TableColumn<Event, String> tcEventMinimum;
    @FXML
    private TableColumn<Customer, Integer> tcEventCurrentParticipants;
    @FXML
    private TableColumn<Event, String> tcEventMaximum;
    @FXML
    private TableColumn<Event, String> tcEventInfo;
    @FXML
    private TableColumn<Event, String> tcEventPrice;

    @FXML
    private ComboBox<String> eventCombo;

    private ObservableList<Event> allEvents = FXCollections.observableArrayList();
    private ObservableList<Event> searchData = FXCollections.observableArrayList();

    private boolean hasSearched = true;

    private final EventModel eventModel;
    private final ErrorHandling errorHandling;
    private Event selectedEvent;
    private EventCoordinator coordinator;
    private ViewEventController viewEventController;
    private EditEventController editEventController;


    /**
     * Constructor
     * @throws IOException
     */
    public EventsOverViewController() throws IOException {
        this.eventModel = new EventModel();
        this.errorHandling = new ErrorHandling();
    }

    /**
     * Runs the methods inside when this view appears
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectedEvent();
        initializeTable();
    }

    /**
     * Loading the table view
     */
    public void initializeTable(){
        tcEventName.setCellValueFactory(new PropertyValueFactory<>("EventName"));
        tcEventDate.setCellValueFactory(new PropertyValueFactory<>("EventDate"));
        tcEventLocation.setCellValueFactory(new PropertyValueFactory<>("EventLocation"));
        tcEventTime.setCellValueFactory(new PropertyValueFactory<>("EventTime"));
        tcEventTimeEnd.setCellValueFactory(new PropertyValueFactory<>("EventTimeEnd"));
        tcEventInfo.setCellValueFactory(new PropertyValueFactory<>("EventInfo"));
        tcEventPrice.setCellValueFactory(new PropertyValueFactory<>("EventPrice"));
        tcEventMinimum.setCellValueFactory(new PropertyValueFactory<>("EventMinimum"));
        tcEventMaximum.setCellValueFactory(new PropertyValueFactory<>("EventMaximum"));
        tcEventCurrentParticipants.setCellValueFactory(new PropertyValueFactory<>("CurrentCustomersOnEvent"));
    }

    /**
     * Method that filters the events, with the text input you write in the textfield.
     * Updates the icon with each press and clears the search on every second click
     */
    @FXML
    private void onActionSearchEvents() {
        if (hasSearched && !tfFieldSearch.getText().equals("")) {
            btnSearchEvents.setText("X");
            hasSearched = false;
        } else {
            btnSearchEvents.setText("🔍");
            hasSearched = true;
            tfFieldSearch.clear();
        }
        try {
            if (eventCombo.getSelectionModel().isSelected(1)) {
                searchData = FXCollections.observableList(eventModel.searchEvent(tfFieldSearch.getText()));
            } else {
                searchData = FXCollections.observableList(eventModel.searchAssignedEvent(tfFieldSearch.getText(), coordinator.getId()));
            }
            searchTableViewLoad(searchData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the different tableview based on the selected value
     */
    @FXML
    private void handleEventCombo(){
        if (eventCombo.getSelectionModel().isSelected(0)){
            allEvents = FXCollections.observableArrayList(eventModel.getEventsCoordinator(coordinator.getId()));
            tableViewLoadEvents(allEvents);
        } else if (eventCombo.getSelectionModel().isSelected(1)){
            allEvents = FXCollections.observableArrayList(eventModel.getEvents());
            tableViewLoadEvents(allEvents);
        }
    }

    /**
     * Sets the different tableview based on the selected value
     */
    private void selectedComboItem(){
        String comboBox = eventCombo.getSelectionModel().getSelectedItem();
        switch (comboBox) {
            case "Assigned Events" -> {
                allEvents = FXCollections.observableList(eventModel.getEventsCoordinator(coordinator.getId()));
                tableViewLoadEvents(allEvents);
                eventCombo.getSelectionModel().isSelected(0);
            }
            case "All Events" -> {
                allEvents = FXCollections.observableList(eventModel.getEvents());
                tableViewLoadEvents(allEvents);
                eventCombo.getSelectionModel().isSelected(1);
            }
            default -> System.out.println("No match");
        }
    }

    /**
     * Logout and brings you back to the login menu
     * @throws IOException
     */
    @FXML
    private void LogOutFromEventCoordinator() throws IOException {
        Stage switcher = (Stage) btnLogOut.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/FrontPage.fxml"));
        switcher.setTitle("Event Management");
        Scene scene = new Scene(root);
        switcher.setScene(scene);
    }

    /**
     * Opens the list of customers
     * @throws IOException
     */
    @FXML
    private void onActionOpenCustomers() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/CreateCustomer.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Manage Customers");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Opens the view where adding an event is possible
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    private void onActionAddEvent() throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/CreateEvent.fxml"));
        Scene scene = new Scene(loader.load());
        Stage switcher = (Stage) btnAddEvent.getScene().getWindow();
        switcher.setScene(scene);
        IController controller = loader.getController();
        controller.setEventCoordinator(coordinator);
        switcher.show();
        switcher.setOnHiding(event ->
        {
            try {
                selectedComboItem();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Opens the view where editing an event is possible
     * @throws IOException
     */
    @FXML
    private void onActionEditEvent() throws IOException {
        if (selectedEvent != null) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/gui/view/EditEvent.fxml"));

            Scene scene = new Scene(fxmlLoader.load());

            Stage stage = new Stage();
            stage.setScene(scene);

            editEventController = fxmlLoader.getController();
            editEventController.setSelectedEvent(selectedEvent);

            stage.setResizable(false);
            stage.show();
            stage.setOnHiding(event ->
            {
                try {
                    selectedComboItem();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } else {
            errorHandling.noEventSelectedWarning();
        }
    }


    /**
     * Deletes a selected event from the table
     */
    @FXML
    private void handleBtnDeleteEvent(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("WARNING MESSAGE");
        alert.setHeaderText("Warning before you delete event");
        alert.setContentText("Remove all customer and tickets from selected event to delete! \n Are you sure you want " +
                "to delete this event?");
        if (selectedEvent != null) {
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                selectedEvent();
                eventModel.deleteCoordinatorFromEvent(selectedEvent.getId(), coordinator.getId());
                eventModel.deleteEvent(selectedEvent.getId());
            }
        } else {
            return;
        }
        try {
            selectedComboItem();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens the view where all the events are
     * @throws IOException
     */
    @FXML
    private void onActionViewEvent() throws IOException {
        if (selectedEvent != null) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/gui/view/ViewEvent.fxml"));

            Scene scene = new Scene(fxmlLoader.load());

            Stage stage = new Stage();
            stage.setScene(scene);

            viewEventController = fxmlLoader.getController();
            viewEventController.setSelectedEvent(selectedEvent);

            stage.setResizable(false);
            stage.show();
            stage.setOnHiding(event ->
            {
                try {
                    selectedComboItem();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } else {
            errorHandling.noEventSelectedWarning();
        }
    }

    /**
     * When pressing enter you can search on events based on the input given to the search field
     */
    @FXML
    private void onActionSearchWithEnter() {
        onActionSearchEvents();
    }

    /**
     * Sets the coordinator and the assigned events
     * @param eventCoordinator
     */
    @Override
    public void setEventCoordinator(EventCoordinator eventCoordinator) {
        coordinator = eventCoordinator;
        try {
            allEvents = FXCollections.observableArrayList(eventModel.getEventsCoordinator(coordinator.getId()));
            tableViewLoadEvents(allEvents);
            eventCombo.getItems().add("Assigned Events");
            eventCombo.getItems().add("All Events");
            eventCombo.getSelectionModel().selectFirst();
        } catch (Exception e){
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


    /**
     * Loading table view events
     *
     * @param allEvents
     */
    private void tableViewLoadEvents(ObservableList<Event> allEvents) {
        tvEvents.setItems(getEventData());
    }

    /**
     * Gets the list of events
     * @return
     */
    private ObservableList<Event> getEventData() {
        return allEvents;
    }

    /**
     * Loads the tableview for the event, when search is pressed.
     * @param searchData
     */
    private void searchTableViewLoad(ObservableList<Event> searchData) {
        tvEvents.setItems(getSearchData());
    }

    /**
     * @return searchData;
     */
    private ObservableList<Event> getSearchData() {
        return searchData;
    }
}
