package gui.controller;

import be.Customer;
import be.Event;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import gui.model.TicketModel;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
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
    private Text ticketID;

    @FXML
    private TextField ticketInfo;

    @FXML
    private Button btnClose;

    @FXML
    private AnchorPane ticketPane;

    private TicketModel ticketModel;

    /**
     * Runs the methods inside when this view appears
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ticketModel = new TicketModel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the eventData
     * @param ev
     * @param selectedCustomerOnEvent
     * @throws SQLServerException
     */
    public void setEventData(Event ev, Customer selectedCustomerOnEvent) throws SQLServerException {
        ticketTitle.setText("Event: " + ev.getEventName());
        ticketFullName.setText("Navn: " + selectedCustomerOnEvent.getFirstName() + " " + selectedCustomerOnEvent.getLastName());
        ticketLocation.setText("Lokation: " + ev.getEventLocation());
        ticketDateTime.setText("Dato: " + ev.getEventDate() + " Start: " + ev.getEventTime() + " Slut: " + ev.getEventTimeEnd());
        ticketInfo.setText(ev.getEventInfo());
        ticketID.setText(String.valueOf(ticketModel.getGeneratedTicketID(selectedCustomerOnEvent.getId(), ev.getId())));
    }

    /**
     * Screenshot of the anchor pane down on your Desktop
     */
    public void generateTicket(){
        String userHomeFolder = System.getProperty("user.home") + "/Desktop";
        WritableImage image = ticketPane.snapshot(new SnapshotParameters(), null);
        File file = new File(userHomeFolder, "placeholder-image.png");
        try{
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "PNG", file);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Takes a screenshot when pressed
     */
    @FXML
    private void onActionPrintTicket() {
        generateTicket();
    }

    /**
     * Closes the window
     */
    @FXML
    private void onActionCloseWindow() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
}
