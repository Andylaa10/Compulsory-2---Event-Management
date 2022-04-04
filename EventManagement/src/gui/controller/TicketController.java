package gui.controller;

import be.Customer;
import be.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class TicketController implements Initializable {
    @FXML
    private Text ticketTitle;

    @FXML
    private Text ticketFullName;

    @FXML
    private Text ticketLocation;

    @FXML
    private Text ticketTime;

    @FXML
    private Text ticketID;

    @FXML
    private Button btnClose;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setEventData(Event ev, Customer selectedCustomerOnEvent){
        ticketTitle.setText("Title: " + ev.getEventName());
        ticketFullName.setText("Navn: " + selectedCustomerOnEvent.getFirstName() + " " + selectedCustomerOnEvent.getLastName());
        ticketLocation.setText("Location " + ev.getEventLocation());
        ticketTime.setText("Date and time " + ev.getEventDate() + " " + ev.getEventTime());
        ticketID.setText("ID " + ev.getId());
    }

    @FXML
    private void onActionCloseTicket(ActionEvent actionEvent) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

}
