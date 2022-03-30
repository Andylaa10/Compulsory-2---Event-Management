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
    private Button btnBack;
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
    public TextField txtFieldEventTimeEnd;
    @FXML
    private TextField txtFieldEventLocation;
    @FXML
    private TextField txtFieldEventPrice;

    EventModel eventModel;

    public EditEventController() throws IOException {
        this.eventModel = new EventModel();
    }
    
    public void onActionSaveEvent() {
        int eventID = Integer.parseInt(txtFieldEventID.getText());
        String eventName = txtFieldEventName.getText();
        String eventDate = txtFieldEventDate.getText();
        String eventTime = txtFieldEventTime.getText();
        String eventTimeEnd = txtFieldEventTimeEnd.getText();
        String eventLocation = txtFieldEventLocation.getText();
        String eventInfo = txtFieldEventInfo.getText();
        String eventPrice = txtFieldEventPrice.getText();



        Event event = new Event(eventID, eventName, eventDate, eventTime, eventTimeEnd, eventLocation, eventInfo, eventPrice);
        eventModel.editEvent(event);

        Stage stage = (Stage) btnEditEvent.getScene().getWindow();
        stage.close();
    }

    public void setSelectedEvent(Event event) {
        txtFieldEventID.setText(String.valueOf(event.getId()));
        txtFieldEventName.setText(event.getEventName());
        txtFieldEventDate.setText(event.getEventDate());
        txtFieldEventTime.setText(event.getEventTime());
        txtFieldEventTimeEnd.setText(event.getEventTimeEnd());
        txtFieldEventLocation.setText(event.getEventLocation());
        txtFieldEventInfo.setText(event.getEventInfo());
    }

    public void handleBtnBack() {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
    }
}
