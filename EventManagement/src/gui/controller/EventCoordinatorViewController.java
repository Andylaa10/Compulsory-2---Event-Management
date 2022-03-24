package gui.controller;

import gui.model.EventCoordinatorModel;
import gui.model.EventModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class EventCoordinatorViewController {

    @FXML
    private Button btnEditEvent;
    @FXML
    private Button btnAddEvent;
    @FXML
    private Button btnCreateUser;
    @FXML
    private Button btnLogOutFromEventCoordinator;
    @FXML
    private Button btnMoreInfo;
    @FXML
    private Button btnMoreInfo1;
    @FXML
    private Button btnMoreInfo2;
    @FXML
    private Button btnMoreInfo3;
    @FXML
    private Button btnCustomerList;
    @FXML
    private Button btnCustomerList1;
    @FXML
    private Button btnCustomerList2;
    @FXML
    private Button btnCustomerList3;
    @FXML
    public Text EventCoordinatorTitle;

    EventCoordinatorModel eventCoordinatorModel;
    EventModel eventModel;

    public EventCoordinatorViewController() throws IOException {
        this.eventCoordinatorModel = new EventCoordinatorModel();
        this.eventModel = new EventModel();
    }

    public void LogOutFromEventCoordinator() throws IOException {
        Stage switcher = (Stage) btnLogOutFromEventCoordinator.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/LoginView.fxml"));
        switcher.setTitle("Event Management");
        Scene scene = new Scene(root);
        switcher.setScene(scene);
    }


    public void onActionAddEvent() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/CreateEventView.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Create Event");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void onActionEditEvent() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/EditEventView.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Edit Event");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void onActionCreateUser() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/CreateUserView.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Create User");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void OnActionMoreInfo(ActionEvent actionEvent) {
    }

    public void OnActionMoreInfo1(ActionEvent actionEvent) {
    }

    public void OnActionMoreInfo2(ActionEvent actionEvent) {
    }

    public void OnActionMoreInfo3(ActionEvent actionEvent) {
    }

    public void onActionCustomerList(ActionEvent actionEvent) {
    }

    public void onActionCustomerList1(ActionEvent actionEvent) {
    }

    public void onActionCustomerList2(ActionEvent actionEvent) {
    }

    public void onActionCustomerList3(ActionEvent actionEvent) {
    }
}
