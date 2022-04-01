package gui.controller;

import bll.helpers.ErrorHandling;
import be.EventCoordinator;
import gui.model.AdminModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class CreateCoordinatorController implements Initializable {

    @FXML
    private Button btnSearchCoordinators;
    @FXML
    private TableView<EventCoordinator> tvCoordinator;
    @FXML
    private TableColumn<EventCoordinator, Integer> tcID;
    @FXML
    private TableColumn<EventCoordinator, String> tcUsername;
    @FXML
    private TableColumn<EventCoordinator, String> tcPassword;
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

    private ObservableList<EventCoordinator> allCoordinators = FXCollections.observableArrayList();

    private EventCoordinator selectedCoordinator;
    private AdminModel adminModel;
    private ErrorHandling errorHandling;

    public CreateCoordinatorController() throws IOException, SQLException {
        adminModel = new AdminModel();
        errorHandling = new ErrorHandling();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTable();
        selectedCoordinator();
    }

    public void initializeTable(){
        tcID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        tcPassword.setCellValueFactory(new PropertyValueFactory<>("password"));

        try {
            allCoordinators = FXCollections.observableList(adminModel.getCoordinator());
            tableViewLoadCoordinator(allCoordinators);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loading table view coordinators
     * @param allCoordinators
     */
    private void tableViewLoadCoordinator(ObservableList<EventCoordinator> allCoordinators) {
        tvCoordinator.setItems(getCoordinatorData());
    }

    /**
     * returns the allCoordinator list
     * @return
     */
    private ObservableList<EventCoordinator> getCoordinatorData() {
        return allCoordinators;
    }

    @FXML
    private void handleBtnDeleteCoordinator() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("WARNING MESSAGE");
        alert.setHeaderText("Warning before you delete a coordinator");
        alert.setContentText(" To delete a coordinator, remove it from all events first!! \n Are you sure you want " +
                "to delete this coordinator?");
        if (selectedCoordinator != null) {
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                selectedCoordinator();
                adminModel.deleteCoordinator(selectedCoordinator.getId());
            } else {
                return;
            }
            try {
                allCoordinators = FXCollections.observableList(adminModel.getCoordinator());
                tableViewLoadCoordinator(allCoordinators);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleBtnBack() throws IOException {
        Stage switcher = (Stage) btnBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/AdminView.fxml"));
        switcher.setTitle("Admin Management");
        Scene scene = new Scene(root);
        switcher.setScene(scene);
    }

    @FXML
    private void handleBtnAddCoordinator() throws SQLException {
        if (!textFieldUsername.getText().isEmpty() && !textFieldPassword.getText().isEmpty()){
            String username = textFieldUsername.getText();
            String password = textFieldPassword.getText();
            boolean isAdmin = false;

            adminModel.createCoordinator(username, password, isAdmin);
            reloadCoordinatorTable();
            textFieldUsername.clear();
            textFieldPassword.clear();
        }else {
            errorHandling.addCoordinatorError();
        }
    }

    /**
     * Reloads the movie table to reflect changes
     */
    private void reloadCoordinatorTable() {
        try {
            int index = tvCoordinator.getSelectionModel().getFocusedIndex();
            this.tvCoordinator.setItems(FXCollections.observableList(adminModel.getCoordinator()));
            tvCoordinator.getSelectionModel().select(index);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Makes you able to select a coordinator from the table
     */
    private void selectedCoordinator() {
        this.tvCoordinator.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if ((EventCoordinator) newValue != null) {
                this.selectedCoordinator = (EventCoordinator) newValue;
            }
        }));
    }
}
