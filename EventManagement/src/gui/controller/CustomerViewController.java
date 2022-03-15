package gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerViewController {

    @FXML
    private Button btnLogOutFromCustomer;

    public void LogOutFromCustomer(ActionEvent actionEvent) throws IOException {
        Stage switcher = (Stage) btnLogOutFromCustomer.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/LoginView.fxml"));
        switcher.setTitle("Event Management");
        Scene scene = new Scene(root);
        switcher.setScene(scene);
    }
}
