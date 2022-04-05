package gui.controller;

import be.Customer;
import be.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TicketController implements Initializable {

    @FXML
    private Text ticketDateTime;
    @FXML
    private Text ticketTitle;
    @FXML
    private Text ticketFullName;
    @FXML
    private Text ticketLocation;
    @FXML
    private TextField ticketInfo;
    @FXML
    private Button btnClose;
    @FXML
    private Button printTicketOut;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setEventData(Event ev, Customer selectedCustomerOnEvent) {
        ticketTitle.setText("Event: " + ev.getEventName());
        ticketFullName.setText("Navn: " + selectedCustomerOnEvent.getFirstName() + " " + selectedCustomerOnEvent.getLastName());
        ticketLocation.setText("Lokation: " + ev.getEventLocation());
        ticketDateTime.setText("Dato: " + ev.getEventDate() + " Start: " + ev.getEventTime() + " Slut: " + ev.getEventTimeEnd());
        ticketInfo.setText(ev.getEventInfo());
    }

    @FXML
    private void onActionCloseWindow() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    public void PrintTicket(ActionEvent actionEvent) {
        System.out.println("works");
    }


}
