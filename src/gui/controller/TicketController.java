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
import java.util.Random;
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
    private Text ticketID;
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
        ticketID.setText(generateID());
    }

    @FXML
    private void onActionCloseWindow() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    private String generateID(){
        Random random = new Random();
        int idSize = 10;
        char[] arrayOfCharacter = {'1','2','3','4','5','6','7','8','9','0','Q','W','E','R','T','Y','U','I','O','P'
                ,'A','S','D','F','G','H','J','K','L','Z','X','C','V','B','N','M'};
        StringBuilder newValueID = new StringBuilder();

        for (int i = 0; i < idSize; i++) {
            int value = random.nextInt(arrayOfCharacter.length);
            char nextChar = arrayOfCharacter[value];
            newValueID.append(nextChar);
        }

        return newValueID.toString();
    }

    public void PrintTicket(ActionEvent actionEvent) {
        System.out.println("works");
    }


}
