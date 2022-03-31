package gui.controller;

import be.Admin;
import be.EventCoordinator;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import gui.model.AdminModel;
import gui.model.EventCoordinatorModel;
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
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FrontPageController implements Initializable {

    @FXML
    private Button btnQuit;
    @FXML
    private Button btnAdminLogin;
    @FXML
    private Button btnEventCoLogin;
    @FXML
    private TextField txtFieldUsername;
    @FXML
    private PasswordField txtPasswordField;

    private AdminModel adminModel;
    private EventCoordinatorModel eventCoordinatorModel;

    /**
     * Constructor
     * @throws IOException
     */
    public FrontPageController() throws IOException, SQLException {
        this.adminModel = new AdminModel();
        this.eventCoordinatorModel = new EventCoordinatorModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    /**
     * This helps you, if you cannot remember or have problems with login
     */
    public void onActionHelp() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Please contact the administration");
        alert.setHeaderText("Please contact the administration");
        alert.setContentText("Contact the administration for help");
        alert.showAndWait();
    }

    /**
     * Sends you to the Admin login screen, if the login credentials are in the database
     * @throws IOException If there are any exceptions
     */
    public void AdminLogIn() throws IOException, SQLServerException {
        String username = txtFieldUsername.getText();
        String password = txtPasswordField.getText();
        Admin admin = adminModel.login(username, password);
        if(admin != null){
            Stage switcher = (Stage) btnAdminLogin.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/view/AdminView.fxml"));
            switcher.setTitle("AdminManagement");
            Scene scene = new Scene(root);
            switcher.setScene(scene);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Wrong Username or Password");
            alert.setContentText("Contact administration for further help");
            alert.showAndWait();
        }
    }

    /**
     * Sends you to the EventCoordinator login screen, if the login credentials are in the database
     * @throws IOException
     * @throws SQLServerException
     */
    public void EventCoLogIn() throws IOException, SQLServerException {
        String username = txtFieldUsername.getText();
        String password = txtPasswordField.getText();
        EventCoordinator eventCoordinator = eventCoordinatorModel.login(username, password);
        if (eventCoordinator != null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/EventCoordinator.fxml"));
            Scene scene = new Scene(loader.load());
            Stage switcher = (Stage) btnEventCoLogin.getScene().getWindow();
            switcher.setScene(scene);
            IController controller = loader.getController();
            controller.setEventCoordinator(eventCoordinator);
            switcher.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wrong Username or Password");
            alert.setHeaderText("Please contact the administration");
            alert.setContentText("You can also try again");
            alert.showAndWait();
        }
    }
}

