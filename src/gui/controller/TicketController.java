package gui.controller;

import be.Customer;
import be.Event;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import gui.model.TicketModel;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
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
import javax.print.PrintService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TicketController implements Initializable {
    @FXML/*w  w  w   .   de  m   o   2s    .c    o  m*/
            Button Printstart;
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
    @FXML
    private AnchorPane ticketPane;

    private TicketModel ticketModel;

    private PrintService[] printServices;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setEventData(Event ev, Customer selectedCustomerOnEvent) throws SQLServerException {
        ticketTitle.setText("Event: " + ev.getEventName());
        ticketFullName.setText("Navn: " + selectedCustomerOnEvent.getFirstName() + " " + selectedCustomerOnEvent.getLastName());
        ticketLocation.setText("Lokation: " + ev.getEventLocation());
        ticketDateTime.setText("Dato: " + ev.getEventDate() + " Start: " + ev.getEventTime() + " Slut: " + ev.getEventTimeEnd());
        ticketInfo.setText(ev.getEventInfo());
        ticketID.setText(String.valueOf(ticketModel.getGeneratedTicketID()));
    }

    public void setTicketModel(TicketModel ticketModel){
        this.ticketModel = ticketModel;
    }

    @FXML
    private void onActionCloseWindow() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }



    public void generateTicket(){
        WritableImage image = ticketPane.snapshot(new SnapshotParameters(), null);
        File file = new File("src/gui/image/placeholder-image.png");
        try{
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "PNG", file);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void PrintTicket(ActionEvent actionEvent) {
        generateTicket();
    }
}
