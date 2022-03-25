package be;

import javafx.scene.control.Alert;

public class ErrorHandling {
    public void deleteEventDAOError() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("WARNING MESSAGE");
        alert.setHeaderText("This event is still assigned to ticket!!");
        alert.setContentText(" To delete a event, delete it from all tickets first!!");
        alert.showAndWait();
    }

    public void addCoordinatorError(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ERROR MESSAGE");
        alert.setHeaderText("Username and password can not be empty");
        alert.setContentText("To add coordinator, please enter information");
        alert.showAndWait();
    }

    public void addCustomerError(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ERROR MESSAGE");
        alert.setHeaderText("First name, last name, phone number and email can not be empty");
        alert.setContentText("To add customer, please enter information");
        alert.showAndWait();
    }
}
