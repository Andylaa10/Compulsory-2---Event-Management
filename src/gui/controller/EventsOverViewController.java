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
import java.util.Optional;
import java.util.ResourceBundle;

public class EventsOverViewController implements Initializable, IController {

    @FXML
    private Button btnSearchEvents;
    @FXML
    private TextField tfFieldSearch;
    @FXML
    private Button btnLogOut;
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


    public EventsOverViewController() throws IOException {
        this.eventModel = new EventModel();
        this.errorHandling = new ErrorHandling();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectedEvent();
        initializeTable();
    }

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

    @FXML
    private void LogOutFromEventCoordinator() throws IOException {
        Stage switcher = (Stage) btnLogOut.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/FrontPage.fxml"));
        switcher.setTitle("Event Management");
        Scene scene = new Scene(root);
        switcher.setScene(scene);
    }

    @FXML
    private void onActionOpenCustomers() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/CreateCustomer.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Manage Customers");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void onActionAddEvent() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/CreateEvent.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Create Event");
        stage.setScene(new Scene(root));
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
    }

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
     * Deletes an event from the table
     */
    @FXML
     private void handleBtnDeleteEvent() {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("WARNING MESSAGE");
      alert.setHeaderText("Warning before you delete event");
      alert.setContentText("Remove all customer and tickets from selected event to delete! \n Are you sure you want " +
              "to delete this event?");
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
          selectedComboItem();
      } catch (Exception e) {
          e.printStackTrace();
      }
    }

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
     * Method that filters the events, with the text input you write in the textfield.
     * Updates the icon with each press and clears the search on every second click
     */
    @FXML
    private void onActionSearchEvents() {
        if (hasSearched == true && !tfFieldSearch.getText().equals("")) {
            btnSearchEvents.setText("X");
            hasSearched = false;
        } else {
            btnSearchEvents.setText("🔍");
            hasSearched = true;
            tfFieldSearch.clear();
        }
        try {
            searchData = FXCollections.observableList(eventModel.searchEvent(tfFieldSearch.getText()));
            searchTableViewLoad(searchData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
