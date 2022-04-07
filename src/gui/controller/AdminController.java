package gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

public class AdminController{

    @FXML
    private Button btnLogOut;

    public AdminController() {
    }

    @FXML
    private void LogOutFromAdmin() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/FrontPage.fxml"));
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        stage.setTitle("Event Management");
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    private void handleBtnCreateCustomer() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/CreateCustomer.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Manage Customers");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void handleBtnCreateEvent() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/CreateEvent.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Create Event");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void handleBtnCreateCoordinator( ) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/CreateCoordinator.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Manage Coordinators");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void handleBtnAddCoordinatorToEvent() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/AddCoordinatorToEvent.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Manage Coordinators");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();

    }

}
