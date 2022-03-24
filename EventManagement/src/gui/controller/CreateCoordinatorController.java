package gui.controller;

import be.EventCoordinator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CreateCoordinatorController {

    @FXML
    private TableView<EventCoordinator> tvCoordinator;

    @FXML
    private TableColumn<EventCoordinator, Integer> tcID;

    @FXML
    private TableColumn<EventCoordinator, String> tcUsername;

    @FXML
    private TableColumn<EventCoordinator, String> tcPassword;

    @FXML
    private TableColumn<EventCoordinator, Boolean> tcIsAdmin;

    @FXML
    private Button btnRefresh;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnEditCoordinator;

    @FXML
    private Button btnDeleteCoordinator;

    @FXML
    private Button btnAddCoordinator;

    @FXML
    private TextField textFieldSearch;

    @FXML
    private TextField textFieldUsername;

    @FXML
    private TextField textFieldPassword;

    @FXML
    private TextField textFieldIsAdmin;


    public CreateCoordinatorController() {
    }


    public void handleBtnRefresh(){

    }

    public void handleBtnEditCoordinator(ActionEvent actionEvent) {
    }

    public void handleBtnDeleteCoordinator(ActionEvent actionEvent) {
    }

    public void handleBtnBack(ActionEvent actionEvent) {
    }

    public void handleBtnAddCoordinator(ActionEvent actionEvent) {
    }
}
