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
    private Button btnCreateCoordinator;
    @FXML
    private Button btnViewEmployee;
    @FXML
    private Button btnCreateCustomer;
    @FXML
    private Button btnCreateEvent;
    @FXML
    private Button btnHelp;
    @FXML
    private Button btnLogOut;
    @FXML
    private Button btnAddCoordinatorToEvent;

    public AdminController() {
    }

    @FXML
    private void LogOutFromAdmin() throws IOException {
        Stage switcher = (Stage) btnLogOut.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/FrontPage.fxml"));
        switcher.setTitle("Event Management");
        Scene scene = new Scene(root);
        switcher.setScene(scene);
    }

    @FXML
    private void handleBtnCreateCustomer() throws IOException {
        Stage switcher = (Stage) btnCreateCustomer.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/CreateCustomer.fxml"));
        Scene scene = new Scene(root);
        switcher.setTitle("Customer Management");
        switcher.setScene(scene);
    }

    @FXML
    private void handleBtnCreateEvent() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/CreateEvent.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Create Event");
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     *
     * @throws IOException
     */
    @FXML
    private void handleBtnCreateCoordinator( ) throws IOException {
        Stage switcher = (Stage) btnCreateCoordinator.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/CreateCoordinator.fxml"));
        Scene scene = new Scene(root);
        switcher.setTitle("Event Coordinator Management");
        switcher.setScene(scene);
    }

    public void handleBtnAddCoordinatorToEvent() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/AddCoordinatorToEvent.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Add Coordinator to Event");
        stage.setScene(new Scene(root));
        stage.show();

    }

}
