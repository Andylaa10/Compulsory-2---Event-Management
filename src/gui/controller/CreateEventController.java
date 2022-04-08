package gui.controller;

import be.EventCoordinator;
import bll.helpers.ErrorHandling;
import gui.model.EventModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

public class CreateEventController implements IController {

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
    private TextField txtFieldEventMinimum;
    @FXML
    private TextField txtFieldEventMaximum;

    private EventModel eventModel;
    private ErrorHandling errorHandling;
    private EventCoordinator coordinator;

    public CreateEventController() throws IOException {
        this.eventModel = new EventModel();
        this.errorHandling = new ErrorHandling();
    }

    @FXML
    private void onActionSaveEvent() throws SQLException, IOException {
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

            if (coordinator != null){
                eventModel.createAndAssignCoordinator(eventName, eventDate, eventTime, eventTimeEnd, eventLocation, eventInfo, eventPrice, eventMinimum, eventMaximum, coordinator.getId());
            }else{
                eventModel.createEvent(eventName, eventDate, eventTime, eventTimeEnd, eventLocation, eventInfo, eventPrice, eventMinimum, eventMaximum);
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/EventsOverview.fxml"));
            Scene scene = new Scene(loader.load());
            Stage switcher = (Stage) btnCreateEvent.getScene().getWindow();
            switcher.setScene(scene);
            IController controller = loader.getController();
            controller.setEventCoordinator(coordinator);
            switcher.show();
        } else {
            errorHandling.invalidInputWarning();
        }
    }

    @FXML
    private void handleBtnBack() {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
    }

    @Override
    public void setEventCoordinator(EventCoordinator eventCoordinator) throws SQLException, IOException {
        coordinator = eventCoordinator;
    }
}

