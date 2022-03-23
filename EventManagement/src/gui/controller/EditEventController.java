package gui.controller;

import be.Event;
import gui.model.EventModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class EditEventController {

    @FXML
    private TextField txtFieldEventName;
    @FXML
    private TextField txtFieldEventDate;
    @FXML
    private TextField txtFieldEventTime;
    @FXML
    private TextField txtFieldEventLocation;
    @FXML
    private TextArea txtAreaEventInfo;
    @FXML
    private Button btnCancelCreateEvent;
    @FXML
    private Button btnSaveCreateEvent;

    EventModel eventModel;

    public EditEventController() throws IOException {
        this.eventModel = new EventModel();
    }
    
    public void onActionSaveEvent() {
        String eventName = txtFieldEventName.getText();
        String eventDate = txtFieldEventDate.getText();
        String eventTime = txtFieldEventTime.getText();
        String eventLocation = txtFieldEventLocation.getText();
        String eventInfo = txtAreaEventInfo.getText();

        Event event = new Event(eventName, eventDate, eventTime, eventLocation, eventInfo);
        eventModel.editEvent(event);

        Stage stage = (Stage) btnSaveCreateEvent.getScene().getWindow();
        stage.close();
    }

    //TODO SKAL BRUGES I EVENTCOORDINATORVIEWCONTROLLER
    public void setSelectedEvent(Event event) {
        txtFieldEventName.setText(event.getEventName());
        txtFieldEventDate.setText(event.getEventDate());
        txtFieldEventTime.setText(event.getEventTime());
        txtFieldEventLocation.setText(event.getEventLocation());
        txtAreaEventInfo.setText(event.getEventInfo());
    }

    public void onActionCancelCreateEvent() {
        Stage stage = (Stage) btnCancelCreateEvent.getScene().getWindow();
        stage.close();
    }
}
