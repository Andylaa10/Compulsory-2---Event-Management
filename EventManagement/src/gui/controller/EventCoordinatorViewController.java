package gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class EventCoordinatorViewController {

    @FXML
    private Button btnLogOutFromEventCoordinator;

    public void LogOutFromEventCoordinator() throws IOException {
        Stage switcher = (Stage) btnLogOutFromEventCoordinator.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/LoginView.fxml"));
        switcher.setTitle("Event Management");
        Scene scene = new Scene(root);
        switcher.setScene(scene);
    }
}
