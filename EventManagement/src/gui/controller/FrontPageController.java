package gui.controller;

import be.Admin;
import be.Customer;
import be.EventCoordinator;
import bll.AdminManager;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Stream;

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
    @FXML
    private Button btnHelp;

    private Admin admin;
    private EventCoordinator eventCoordinator;

    /**
     * Constructor
     * @throws IOException
     */
    public FrontPageController() throws IOException {
        this.admin = new Admin();
        this.eventCoordinator = new EventCoordinator();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void saveLogin() throws IOException {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (Exception e) {
            e.printStackTrace();
        }

        BufferedImage i = robot.createScreenCapture(new Rectangle(1920, 1080));
        File output = new File("./save.png");
        try {
            ImageIO.write(i, "png", output);

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * This helps you, if you cannot remember or have problems with login
     */
    public void help() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Please contact the administration");
        alert.setHeaderText("Please contact the administration");
        alert.setContentText("Contact the administration for help");
        alert.showAndWait();
    }

    /**
     * This method send you to another view, if you have an account
     *
     * @throws IOException If there are any exceptions
     */
    public void AdminLogIn() throws IOException {
        if (txtPasswordField.getText().equals(admin.getPassword()) && txtFieldUsername.getText().equals(admin.getUsername())) {
            Stage switcher = (Stage) btnAdminLogin.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/view/AdminView.fxml"));
            switcher.setTitle("AdminManagement");
            Scene scene = new Scene(root);
            switcher.setScene(scene);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wrong Username or Password");
            alert.setHeaderText("Please contact the administration");
            alert.setContentText("You can also try again");
            alert.showAndWait();
        }
    }

    public void EventCoLogIn() throws IOException {
        if (txtPasswordField.getText().equals(eventCoordinator.getPassword()) && txtFieldUsername.getText().equals(eventCoordinator.getUsername())) {
            Stage switcher = (Stage) btnEventCoLogin.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/view/EventCoordinatorView.fxml"));
            Scene scene = new Scene(root);
            switcher.setTitle("EventCoordinatorManagement");
            switcher.setScene(scene);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wrong Username or Password");
            alert.setHeaderText("Please contact the administration");
            alert.setContentText("You can also try again");
            alert.showAndWait();
        }
    }
}

