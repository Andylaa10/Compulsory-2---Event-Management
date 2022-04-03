package gui.controller;

import be.Customer;
import be.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;

public class TicketController implements Initializable {
    @FXML
    private Text ticketTitle;

    @FXML
    private Text ticketFullName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setEventData(Event ev, Customer selectedCustomerOnEvent){
        ticketTitle.setText("Title: " + ev.getEventName());
        ticketFullName.setText("Fuld navn: " + selectedCustomerOnEvent.getFirstName());
    }
}
