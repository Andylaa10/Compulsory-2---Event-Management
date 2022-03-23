package gui.controller;

import gui.model.EventModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class CreateEventController {

    @FXML
    private TextArea txtAreaEventInfo;
    @FXML
    private Button btnCancelCreateEvent;
    @FXML
    private Button btnSaveCreateEvent;
    @FXML
    private TextField txtFieldEventName;
    @FXML
    private TextField txtFieldEventDate;
    @FXML
    private TextField txtFieldEventTime;
    @FXML
    private TextField txtFieldEventLocation;

    EventModel eventModel;

    public CreateEventController() throws IOException {
        this.eventModel = new EventModel();
    }

    public void onActionSaveEvent() throws SQLException {
        String eventName = txtFieldEventName.getText();
        String eventDate = txtFieldEventDate.getText();
        String eventTime = txtFieldEventTime.getText();
        String eventLocation = txtFieldEventLocation.getText();
        String eventInfo = txtAreaEventInfo.getText();

        eventModel.createEvent(eventName, eventDate, eventTime, eventLocation, eventInfo);
        Stage stage = (Stage) btnSaveCreateEvent.getScene().getWindow();
        stage.close();
    }

    public void onActionCancelCreateEvent() {
        Stage stage = (Stage) btnCancelCreateEvent.getScene().getWindow();
        stage.close();
    }
}
