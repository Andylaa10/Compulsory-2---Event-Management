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
    private Button btnCreateEvent;
    @FXML
    private Button btnBack;
    @FXML
    private TextField txtFieldEventName;
    @FXML
    private TextField txtFieldEventDate;
    @FXML
    private TextField txtFieldEventTime;
    @FXML
    private TextField txtFieldEventLocation;
    @FXML
    private TextArea txtFieldEventInfo;

    private EventModel eventModel;

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

    public void handleBtnBack(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
    }
}
