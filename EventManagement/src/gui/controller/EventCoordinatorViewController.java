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
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class EventCoordinatorViewController implements Initializable {

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
    private TableColumn tcId;
    @FXML
    private TableColumn tcEventName;
    @FXML
    private TableColumn tcEventDate;
    @FXML
    private TableColumn tcEventLocation;
    @FXML
    private TableColumn tcEventTime;
    @FXML
    private Button btnEditEvent;
    @FXML
    private TableColumn tcEventInfo;
    @FXML
    private TableColumn tcEventPrice;

    private ObservableList<Event> allEvents = FXCollections.observableArrayList();
    private ObservableList<Event> searchData = FXCollections.observableArrayList();

    private boolean hasSearched = true;

    private EventCoordinatorModel eventCoordinatorModel;
    private EventModel eventModel;
    private Event selectedEvent;
    private EditEventController editEventController;

    public EventCoordinatorViewController() throws IOException {
        this.eventCoordinatorModel = new EventCoordinatorModel();
        this.eventModel = new EventModel();
        this.editEventController = new EditEventController();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectedEvent();

        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcEventName.setCellValueFactory(new PropertyValueFactory<>("EventName"));
        tcEventDate.setCellValueFactory(new PropertyValueFactory<>("EventDate"));
        tcEventLocation.setCellValueFactory(new PropertyValueFactory<>("EventLocation"));
        tcEventTime.setCellValueFactory(new PropertyValueFactory<>("EventTime"));
        tcEventInfo.setCellValueFactory(new PropertyValueFactory<>("EventInfo"));
        tcEventPrice.setCellValueFactory(new PropertyValueFactory<>("EventPrice"));

        try {
            allEvents = FXCollections.observableArrayList(eventModel.getEvents());
            tableViewLoadEvents(allEvents);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        stage.setOnHiding(event ->
        {
            try {
                allEvents = FXCollections.observableList(eventModel.getEvents());
                tableViewLoadEvents(allEvents);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void onActionEditEvent() throws IOException {
        //TODO ADD ERROR HANDLING IF NO EVENT SELECTED
        if (selectedEvent != null) {
            Event selectedEvent = (Event) tvEvents.getSelectionModel().getSelectedItem();

            FXMLLoader parent = new FXMLLoader(getClass().getResource("/gui/view/EditEventView.fxml"));
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
                    allEvents = FXCollections.observableList(eventModel.getEvents());
                    tableViewLoadEvents(allEvents);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } else {
            System.out.println("No event selected");
        }
    }


    /**
     * Deletes an event from the table
     */
     public void handleBtnDeleteEvent(ActionEvent actionEvent) {
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
          allEvents = FXCollections.observableList(eventModel.getEvents());
          tableViewLoadEvents(allEvents);
      } catch (Exception e) {
          e.printStackTrace();
      }
    }

    public void onActionViewEvent() {
        //TODO ADD ERROR HANDLING IF NO EVENT SELECTED
        if (selectedEvent != null) {
            Event selectedEvent = (Event) tvEvents.getSelectionModel().getSelectedItem();

            FXMLLoader parent = new FXMLLoader(getClass().getResource("/gui/view/ViewEvent.fxml"));
            Scene mainWindowScene = null;
            try {
                mainWindowScene = new Scene(parent.load());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            Stage viewEventStage;
            viewEventStage = new Stage();
            viewEventStage.setScene(mainWindowScene);
            ViewEventController viewEventController = parent.getController();
            viewEventController.setSelectedEvent(selectedEvent);
            viewEventStage.show();
            viewEventStage.setOnHiding(event ->
            {
                try {
                    allEvents = FXCollections.observableList(eventModel.getEvents());
                    tableViewLoadEvents(allEvents);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } else {
            System.out.println("No event selected");
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

    public void help() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Please contact the administration");
        alert.setHeaderText("Please contact the administration");
        alert.setContentText("Contact the administration for help");
        alert.showAndWait();
    }

    /**
     * Method that filters the events, with the text input you write in the textfield.
     * Updates the icon with each press and clears the search on every second click
     */
    public void onActionSearchEvents() {
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
    public ObservableList<Event> getSearchData() {
        return searchData;
    }


    public void Quit() {
        System.exit(0);
    }
}
