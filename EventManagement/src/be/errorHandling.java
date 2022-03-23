package be;

import javafx.scene.control.Alert;

public class errorHandling {
    public static void deleteEventDAOError() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("WARNING MESSAGE");
        alert.setHeaderText("This event is still assigned to ticket!!");
        alert.setContentText(" To delete a event, delete it from all tickets first!!");
        alert.showAndWait();
    }
}
