package gui.controller;

import be.Event;
import bll.helpers.ErrorHandling;
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
    private TextField txtFieldEventTimeEnd;
    @FXML
    private TextField txtFieldEventLocation;
    @FXML
    private TextField txtFieldEventPrice;
    @FXML
    private TextField txtFieldEventMinimum;
    @FXML
    private TextField txtFieldEventMaximum;

    private EventModel eventModel;
    private ErrorHandling errorHandling;

    /**
     * Constructor
     * @throws IOException
     */
    public EditEventController() throws IOException {
        this.eventModel = new EventModel();
        this.errorHandling = new ErrorHandling();
    }

    /**
     * Saves the event based on the input given
     */
    @FXML
    private void onActionSaveEvent() {
        if (!txtFieldEventName.getText().isEmpty() && !txtFieldEventDate.getText().isEmpty() && !txtFieldEventTime.getText().isEmpty()
                && !txtFieldEventTimeEnd.getText().isEmpty() && !txtFieldEventLocation.getText().isEmpty()
                && !txtFieldEventMinimum.getText().isEmpty() && !txtFieldEventMaximum.getText().isEmpty())
        {
            int eventID = Integer.parseInt(txtFieldEventID.getText());
            String eventName = txtFieldEventName.getText();
            String eventDate = txtFieldEventDate.getText();
            String eventTime = txtFieldEventTime.getText();
            String eventTimeEnd = txtFieldEventTimeEnd.getText();
            String eventLocation = txtFieldEventLocation.getText();
            String eventInfo = txtFieldEventInfo.getText();
            String eventPrice = txtFieldEventPrice.getText();
            int eventMinimum = Integer.parseInt(txtFieldEventMinimum.getText());
            int eventMaximum = Integer.parseInt(txtFieldEventMaximum.getText());

            Event event = new Event(eventID, eventName, eventDate, eventTime, eventTimeEnd, eventLocation, eventInfo, eventPrice, eventMinimum, eventMaximum);
            eventModel.editEvent(event);

            Stage stage = (Stage) btnEditEvent.getScene().getWindow();
            stage.close();
        } else {
            errorHandling.editEventWarning();
        }

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
     * Sets the selected event
     * @param event
     */
    public void setSelectedEvent(Event event) {
        txtFieldEventID.setText(String.valueOf(event.getId()));
        txtFieldEventName.setText(event.getEventName());
        txtFieldEventDate.setText(event.getEventDate());
        txtFieldEventTime.setText(event.getEventTime());
        txtFieldEventTimeEnd.setText(event.getEventTimeEnd());
        txtFieldEventLocation.setText(event.getEventLocation());
        txtFieldEventInfo.setText(event.getEventInfo());
        txtFieldEventPrice.setText(event.getEventPrice());
        txtFieldEventMinimum.setText(event.getEventMinimum());
        txtFieldEventMaximum.setText(event.getEventMaximum());
    }
}
