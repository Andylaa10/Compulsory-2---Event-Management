package bll.helpers;

import javafx.scene.control.Alert;

public class ErrorHandling {
    public void deleteEventDAOError() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("This event still has assigned customers");
        alert.setContentText("To delete an event, remove all customers from it first");
        alert.showAndWait();
    }

    public void addCoordinatorError(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Username and password can't be empty");
        alert.setContentText("To add a coordinator, please enter his login credentials");
        alert.showAndWait();
    }

    public void addCustomerError(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("First name, last name, phone number and e-mail can't be empty");
        alert.setContentText("To add a customer, please enter his credentials");
        alert.showAndWait();
    }
    
}
