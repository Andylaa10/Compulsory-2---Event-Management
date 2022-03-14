package gui.controller;

import be.Admin;
import be.Customer;
import be.EventCoordinator;
import javafx.animation.*;
import javafx.event.ActionEvent;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Stream;


public class LoginController implements Initializable {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button btnLogin;

    @FXML
    private AnchorPane loginPane;

    @FXML
    private ImageView img1;
    @FXML
    private ImageView img2;
    @FXML
    private ImageView img3;
    @FXML
    private ImageView img4;
    @FXML
    private ImageView img5;
    @FXML
    private ImageView img6;

    private Admin admin = new Admin();
    private Customer customer = new Customer();
    private EventCoordinator eventCoordinator = new EventCoordinator();

    //https://www.youtube.com/watch?v=mgw1sAvhjy8&ab_channel=JavaGuides
    public void saveLogin() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setBorder(loginPane);
        delayLight();

    }

    /**
     * This helps you, if you cannot remember or have problems with login
     *
     */
    public void help() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Please contact the administration");
        alert.setHeaderText("Please contract the administration");
        alert.setContentText("Contact the administration for help");
        alert.showAndWait();
    }

    /**
     * This method send you to another view, if you have an account
     * @throws IOException
     * If there are any exceptions
     */
    public void LogIn() throws IOException {
        if (passwordField.getText().equals(admin.getPassword()) && usernameField.getText().equals(admin.getUsername())) {
            Stage switcher = (Stage) btnLogin.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/view/AdminView.fxml"));
            switcher.setTitle("AdminManagement");
            Scene scene = new Scene(root);
            switcher.setScene(scene);
        } else if (passwordField.getText().equals(customer.getPassword()) && usernameField.getText().equals(customer.getUsername())) {
            Stage switcher = (Stage) btnLogin.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/view/CustomerView.fxml"));
            Scene scene = new Scene(root);
            switcher.setTitle("CustomerManagement");
            switcher.setScene(scene);
        } else if (passwordField.getText().equals(eventCoordinator.getPassword()) && usernameField.getText().equals(eventCoordinator.getUsername())) {
            Stage switcher = (Stage) btnLogin.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/view/EventCoordinatorView.fxml"));
            Scene scene = new Scene(root);
            switcher.setTitle("EventCoordinatorManagement");
            switcher.setScene(scene);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wrong Username or Password");
            alert.setHeaderText("Please contract the administration");
            alert.setContentText("You can also try again");
            alert.showAndWait();
        }
    }

    //Beautiful color animation around the anchor pane
    private void setBorder(AnchorPane pane) {

        Color[] colors = Stream.of("darkorange", "tomato", "deeppink", "blueviolet", "steelblue", "cornflowerblue", "lightseagreen", "#6fba82", "chartreuse", "crimson")
                .map(Color::web)
                .toArray(Color[]::new);


        int mills[] = {-200};
        KeyFrame keyFrames[]  = Stream.iterate(0, i -> i+1)
                .limit(100)
                .map(i -> new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, new Stop[]{new Stop(0, colors[i%colors.length]), new Stop(1, colors[(i+1)%colors.length])}))
                .map(lg -> new Border(new BorderStroke(lg, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(2))))
                .map(b -> new KeyFrame(Duration.millis(mills[0]+=200), new KeyValue(pane.borderProperty(), b, Interpolator.EASE_IN)))
                .toArray(KeyFrame[]::new);

        Timeline timeline = new Timeline(keyFrames);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * Delay the code and makes a star effect
     */
    private void delayLight(){
        try{
            setFade(img1);
            Thread.sleep(500);
            setFade(img2);
            Thread.sleep(500);
            setFade(img3);
            Thread.sleep(500);
            setFade(img4);
            Thread.sleep(500);
            setFade(img5);
            Thread.sleep(200);
            setFade(img6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Makes the images fade, this creates a star effect
     * @param img
     * Takes an images and make it fade
     */
    private void setFade(ImageView img){
        //fade
        FadeTransition fade = new FadeTransition();
        fade.setNode(img);
        fade.setDuration(Duration.millis(3500));
        fade.setCycleCount(TranslateTransition.INDEFINITE);
        fade.setInterpolator(Interpolator.LINEAR);
        fade.setFromValue(0.4);
        fade.setToValue(0);
        fade.play();
    }

    public void Quit(ActionEvent actionEvent) {
        System.exit(0);
    }
}
