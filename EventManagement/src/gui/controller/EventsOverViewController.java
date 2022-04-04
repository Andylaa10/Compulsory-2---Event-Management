package gui.controller;

import be.Customer;
import be.Event;
import be.EventCoordinator;
import bll.helpers.ErrorHandling;
import gui.model.AdminModel;
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
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class EventsOverViewController implements Initializable, IController {

    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnSearchEvents;
    @FXML
    private Button btnAddEvent;
    @FXML
    private Button btnDeleteEvent;
    @FXML
    private Button btnViewEvent;
    @FXML
    private Button btnQuit;
    @FXML
    private TextField tfFieldSearch;
    @FXML
    private Button btnHelp;
    @FXML
    private Button btnLogOut;
    @FXML
    private TableView tvEvents;
    @FXML
    private TableColumn tcEventName;
    @FXML
    private TableColumn tcEventDate;
    @FXML
    private TableColumn tcEventLocation;
    @FXML
    private TableColumn tcEventTime;
    @FXML
    private TableColumn tcEventTimeEnd;
    @FXML
    private TableColumn tcEventMinimum;
    @FXML
    private TableColumn<Customer, Integer> tcEventCurrentParticipants;
    @FXML
    private TableColumn tcEventMaximum;
    @FXML
    public TableColumn tcLoginID;
    @FXML
    private Button btnEditEvent;
    @FXML
    private TableColumn tcEventInfo;
    @FXML
    private TableColumn tcEventPrice;
    @FXML
    private ComboBox<String> eventCombo;

    private ObservableList<Event> allEvents = FXCollections.observableArrayList();
    private ObservableList<Event> searchData = FXCollections.observableArrayList();
    private ObservableList<Event> options = FXCollections.observableArrayList();



    private boolean hasSearched = true;

    private EventCoordinatorModel eventCoordinatorModel;
    private EventModel eventModel;
    private Event selectedEvent;
    private EditEventController editEventController;
    private ViewEventController viewEventController;
    private EventCoordinator coordinator;
    private ErrorHandling errorHandling;


    public EventsOverViewController() throws IOException, SQLException {
        this.eventCoordinatorModel = new EventCoordinatorModel();
        this.eventModel = new EventModel();
        this.editEventController = new EditEventController();
        this.errorHandling = new ErrorHandling();
    }

    public void handleEventCombo(){
        if (eventCombo.getSelectionModel().isSelected(0)){
            allEvents = FXCollections.observableArrayList(eventModel.getEventsCoordinator(coordinator.getId()));
            tableViewLoadEvents(allEvents);
        } else if (eventCombo.getSelectionModel().isSelected(1)){
            allEvents = FXCollections.observableArrayList(eventModel.getEvents());
            tableViewLoadEvents(allEvents);
        }
    }

    @Override
    public void setEventCoordinator(EventCoordinator eventCoordinator) {
        coordinator = eventCoordinator;
        try {
            allEvents = FXCollections.observableArrayList(eventModel.getEventsCoordinator(coordinator.getId()));
            tableViewLoadEvents(allEvents);
            eventCombo.getItems().add("Assigned Events");
            eventCombo.getItems().add("All Events");
        } catch (Exception e){
            e.printStackTrace();
        }
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



    @FXML
    private void LogOutFromEventCoordinator() throws IOException {
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

    @FXML
    private void onActionOpenCustomers() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/CreateCustomer.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Manage Customers");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void onActionAddEvent() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/CreateEvent.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Create Event");
        stage.setScene(new Scene(root));
        stage.show();
        stage.setOnHiding(event ->
        {
            try {
                allEvents = FXCollections.observableList(eventModel.getEventsCoordinator(coordinator.getId()));
                tableViewLoadEvents(allEvents);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void onActionEditEvent() {
        if (selectedEvent != null) {
            Event selectedEvent = (Event) tvEvents.getSelectionModel().getSelectedItem();

            FXMLLoader parent = new FXMLLoader(getClass().getResource("/gui/view/EditEvent.fxml"));
            Scene mainWindowScene = null;
            try {
                mainWindowScene = new Scene(parent.load());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            Stage editEventStage;
            editEventStage = new Stage();
            editEventStage.setScene(mainWindowScene);
            EditEventController editEventController = parent.getController();
            editEventController.setSelectedEvent(selectedEvent);
            editEventStage.show();
            editEventStage.setOnHiding(event ->
            {
                try {
                    allEvents = FXCollections.observableList(eventModel.getEventsCoordinator(coordinator.getId()));
                    tableViewLoadEvents(allEvents);
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
     private void handleBtnDeleteEvent(ActionEvent actionEvent) {
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
          allEvents = FXCollections.observableList(eventModel.getEventsCoordinator(coordinator.getId()));
          tableViewLoadEvents(allEvents);
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

            stage.show();
        } else {
            errorHandling.noEventSelectedWarning();
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
