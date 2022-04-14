package gui.controller;

import be.Admin;
import be.EventCoordinator;
import bll.helpers.ErrorHandling;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import gui.model.AdminModel;
import gui.model.EventCoordinatorModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

public class FrontPageController{

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
     * Sends you to the EventCoordinator login screen, if the login credentials are in the database.
     * The view is based on all values based on the specific coordinator login
     * @throws IOException
     * @throws SQLServerException
     */
    @FXML
    private void EventCoordinatorLogin() throws IOException, SQLException {
        String username = txtFieldUsername.getText();
        String password = txtPasswordField.getText();
        EventCoordinator eventCoordinator = eventCoordinatorModel.login(username, password);
        if (eventCoordinator != null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/EventsOverview.fxml"));
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

