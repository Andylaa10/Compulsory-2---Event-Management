package gui.controller;

import gui.model.EventModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    public Button btnBack;
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

    public void handleBtnBack(ActionEvent actionEvent) throws IOException {
        Stage switcher = (Stage) btnBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/AdminView.fxml"));
        switcher.setTitle("Admin Management");
        Scene scene = new Scene(root);
        switcher.setScene(scene);
    }
}
