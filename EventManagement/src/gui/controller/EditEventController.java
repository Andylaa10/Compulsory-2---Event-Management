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
    private Button btnEditEvent;
    @FXML
    private TextArea txtFieldEventInfo;
    @FXML
    private TextField txtFieldEventID;
    @FXML
    private TextField txtFieldEventName;
    @FXML
    private TextField txtFieldEventDate;
    @FXML
    private TextField txtFieldEventTime;
    @FXML
    private TextField txtFieldEventLocation;
    

    EventModel eventModel;

    public EditEventController() throws IOException {
        this.eventModel = new EventModel();
    }
    
    public void onActionSaveEvent() {
        int eventID = Integer.parseInt(txtFieldEventID.getText());
        String eventName = txtFieldEventName.getText();
        String eventDate = txtFieldEventDate.getText();
        String eventTime = txtFieldEventTime.getText();
        String eventLocation = txtFieldEventLocation.getText();
        String eventInfo = txtFieldEventInfo.getText();


        Event event = new Event(eventID, eventName, eventDate, eventTime, eventLocation, eventInfo);
        eventModel.editEvent(event);

        Stage stage = (Stage) btnEditEvent.getScene().getWindow();
        stage.close();
    }

    public void setSelectedEvent(Event event) {
        txtFieldEventID.setText(String.valueOf(event.getId()));
        txtFieldEventName.setText(event.getEventName());
        txtFieldEventDate.setText(event.getEventDate());
        txtFieldEventTime.setText(event.getEventTime());
        txtFieldEventLocation.setText(event.getEventLocation());
        txtFieldEventInfo.setText(event.getEventInfo());
    }



}
