package bll.helpers;

import javafx.scene.control.Alert;

public class ErrorHandling {
    public void deleteEventDAOError() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("This event still has assigned customers");
        alert.setContentText("To delete an event, remove all customers from it first");
        alert.showAndWait();
    }

    public void addCoordinatorError() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Username and password can't be empty");
        alert.setContentText("To add a coordinator, please enter his login credentials");
        alert.showAndWait();
    }

    public void addCustomerError() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("First name, last name, phone number and e-mail can't be empty");
        alert.setContentText("To add a customer, please enter his credentials");
        alert.showAndWait();
    }

    public void noCustomerSelectedWarning() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("No customer was selected");
        alert.showAndWait();
    }

    public void noEventSelectedWarning() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("No event was selected");
        alert.showAndWait();
    }

    public void invalidInputWarning() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Cant create event");
        alert.setContentText("The required fields were empty");
        alert.showAndWait();
    }

    public void editCustomerWarning() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Required fields were empty");
        alert.setContentText("The customer needs to have a name, phone number and an email address");
        alert.showAndWait();
    }

    public void editEventWarning() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Required fields were empty");
        alert.setContentText("The event needs to have a name, date, time and location");
        alert.showAndWait();
    }

    public void wrongLoginInfoError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Wrong Username or Password");
        alert.setContentText("Contact administration for further help");
        alert.showAndWait();
    }

}
