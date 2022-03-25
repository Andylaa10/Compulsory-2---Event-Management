package gui.controller;

import gui.model.EventModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

public class CreateEventController {

    @FXML
    public Button btnCreateEvent;
    @FXML
    public Button btnQuit;
    @FXML
    public TextField txtFieldEventName;
    @FXML
    public TextField txtFieldEventDate;
    @FXML
    public TextField txtFieldEventTime;
    @FXML
    public TextField txtFieldEventLocation;
    @FXML
    public TextArea txtFieldEventInfo;
    EventModel eventModel;

    public CreateEventController() throws IOException {
        this.eventModel = new EventModel();
    }

    public void onActionSaveEvent() throws SQLException {
        String eventName = txtFieldEventName.getText();
        String eventDate = txtFieldEventDate.getText();
        String eventTime = txtFieldEventTime.getText();
        String eventLocation = txtFieldEventLocation.getText();
        String eventInfo = txtFieldEventInfo.getText();

        eventModel.createEvent(eventName, eventDate, eventTime, eventLocation, eventInfo);
        Stage stage = (Stage) btnCreateEvent.getScene().getWindow();
        stage.close();
    }

    public void Quit() {
        Stage stage = (Stage) btnQuit.getScene().getWindow();
        stage.close();
    }
}
