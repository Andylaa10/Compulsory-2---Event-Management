package gui.controller;

import be.Admin;
import be.EventCoordinator;
import bll.helpers.ErrorHandling;
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
    private ErrorHandling errorHandling;

    /**
     * Constructor
     * @throws IOException
     */
    public FrontPageController() throws IOException, SQLException {
        this.adminModel = new AdminModel();
        this.eventCoordinatorModel = new EventCoordinatorModel();
        this.errorHandling = new ErrorHandling();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    /**
     * Sends you to the Admin login screen, if the login credentials are in the database
     * @throws IOException If there are any exceptions
     */
    public void AdminLogin() throws IOException, SQLServerException {
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
            errorHandling.wrongLoginInfoError();
        }
    }

    /**
     * Sends you to the EventCoordinator login screen, if the login credentials are in the database
     * @throws IOException
     * @throws SQLServerException
     */
    public void EventCoordinatorLogin() throws IOException, SQLServerException {
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
            errorHandling.wrongLoginInfoError();
        }
    }
}

