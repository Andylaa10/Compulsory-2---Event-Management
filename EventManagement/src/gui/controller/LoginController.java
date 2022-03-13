package gui.controller;

import be.Admin;
import be.Customer;
import be.EventCoordinator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button btnLogin;

    private Admin admin = new Admin();
    private Customer customer = new Customer();
    private EventCoordinator eventCoordinator = new EventCoordinator();


    public void saveLogin(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void help(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Please contact the administration");
        alert.setHeaderText("Please contract the administration");
        alert.setContentText("Contact the administration for help");
        alert.showAndWait();
    }

    public void LogIn() throws IOException {
        if (passwordField.getText().equals(admin.getPassword()) && usernameField.getText().equals(admin.getUsername())) {
            Stage switcher = (Stage) btnLogin.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/view/AdminView.fxml"));
            switcher.setTitle("AdminManagement");
            Scene scene = new Scene(root);
            switcher.setScene(scene);
        } else if (passwordField.getText().equals(customer.getPassword()) && usernameField.getText().equals(customer.getUsername())) {
            Stage switcher = (Stage) btnLogin.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/view/CustomerView.fxml"));
            Scene scene = new Scene(root);
            switcher.setTitle("CustomerManagement");
            switcher.setScene(scene);
        } else if (passwordField.getText().equals(eventCoordinator.getPassword()) && usernameField.getText().equals(eventCoordinator.getUsername())) {
            Stage switcher = (Stage) btnLogin.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/view/EventCoordinatorView.fxml"));
            Scene scene = new Scene(root);
            switcher.setTitle("EventCoordinatorManagement");
            switcher.setScene(scene);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wrong Username or Password");
            alert.setHeaderText("Please contract the administration");
            alert.setContentText("You can also try again");
            alert.showAndWait();
        }
    }
}
