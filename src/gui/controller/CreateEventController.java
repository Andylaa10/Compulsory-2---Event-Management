package gui.controller;

import bll.helpers.ErrorHandling;
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
    private TextField txtFieldEventTimeEnd;
    @FXML
    private TextField txtFieldEventLocation;
    @FXML
    private TextArea txtFieldEventInfo;
    @FXML
    private TextField txtFieldEventPrice;
    @FXML
    public TextField txtFieldEventMinimum;
    @FXML
    public TextField txtFieldEventMaximum;

    private EventModel eventModel;
    private ErrorHandling errorHandling;

    public CreateEventController() throws IOException {
        this.eventModel = new EventModel();
        this.errorHandling = new ErrorHandling();
    }

    @FXML
    private void onActionSaveEvent() throws SQLException {
        if (!txtFieldEventName.getText().isEmpty() && !txtFieldEventDate.getText().isEmpty() && !txtFieldEventTime.getText().isEmpty()
                && !txtFieldEventTimeEnd.getText().isEmpty() && !txtFieldEventLocation.getText().isEmpty()
                && !txtFieldEventMinimum.getText().isEmpty() && !txtFieldEventMaximum.getText().isEmpty())
        {
            String eventName = txtFieldEventName.getText();
            String eventDate = txtFieldEventDate.getText();
            String eventTime = txtFieldEventTime.getText();
            String eventTimeEnd = txtFieldEventTimeEnd.getText();
            String eventLocation = txtFieldEventLocation.getText();
            String eventInfo = txtFieldEventInfo.getText();
            String eventPrice = txtFieldEventPrice.getText();
            int eventMinimum = Integer.parseInt(txtFieldEventMinimum.getText());
            int eventMaximum = Integer.parseInt(txtFieldEventMaximum.getText());

            eventModel.createEvent(eventName, eventDate, eventTime, eventTimeEnd, eventLocation, eventInfo, eventPrice, eventMinimum, eventMaximum);

            Stage stage = (Stage) btnCreateEvent.getScene().getWindow();
            stage.close();
        } else {
            errorHandling.invalidInputWarning();
        }
    }

    @FXML
    private void handleBtnBack() {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
    }
}

