package gui.controller;

import be.Customer;
import gui.model.CustomerModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreateCustomerController implements Initializable {

    @FXML
    public TableView tvCustomers;
    @FXML
    public TableColumn<Customer, Integer> tcCustomerID;
    @FXML
    public TableColumn<Customer, String> tcFirstName;
    @FXML
    public TableColumn<Customer, String> tcLastName;
    @FXML
    public TableColumn<Customer, String> tcPhoneNumber;
    @FXML
    public TableColumn<Customer, String> tcEmail;
    @FXML
    public Button btnAddCustomer;
    @FXML
    public TextField txtFieldSearch;
    @FXML
    public TextField txtFieldLastName;
    @FXML
    public TextField txtFieldFirstName;
    @FXML
    public Button btnEditCustomer;
    @FXML
    public Button btnDeleteCustomer;
    @FXML
    public TextField txtFieldPhoneNumber;
    @FXML
    public TextField txtFieldEmail;
    @FXML
    public Button btnBack;


    private ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

    private CustomerModel customerModel;


    public CreateCustomerController() throws IOException {
        this.customerModel = new CustomerModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTable();
    }

    public void initializeTable() {
        tcCustomerID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tcLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tcPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        try {
            allCustomers = FXCollections.observableList(customerModel.getCustomers());
            tableViewLoadCustomer(allCustomers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loading table view categories
     *
     * @param allCustomers
     */
    private void tableViewLoadCustomer(ObservableList<Customer> allCustomers) {
        tvCustomers.setItems(getCustomerData());
    }

    /**
     * returns the allCategories list
     *
     * @return
     */
    private ObservableList<Customer> getCustomerData() {
        return allCustomers;
    }


    public void handleBtnAddCustomer() throws SQLException {
        String customerFirstName = txtFieldFirstName.getText();
        String customerLastName = txtFieldLastName.getText();
        String customerPhoneNumber = txtFieldPhoneNumber.getText();
        String customerEmail = txtFieldEmail.getText();

        customerModel.createCustomer(customerFirstName, customerLastName, customerPhoneNumber, customerEmail);
        reloadCustomerTable();
    }

    public void handleBtnEditCustomer(){
        //TODO Implement metode her til at edit
    }

    public void handleBtnDeleteCustomer(){
        //TODO Implement metode her til at delete
    }

    public void reloadCustomerTable() {
        try {
            int index = tvCustomers.getSelectionModel().getFocusedIndex();
            this.tvCustomers.setItems(FXCollections.observableList(customerModel.getCustomers()));
            tvCustomers.getSelectionModel().select(index);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void selectedCustomer(){
        //TODO implementer metode her til at selecte en customer
    }

    /**
     * public void onActionCancelCreateCustomer() {
     * Stage stage = (Stage) btnCancelCreateCustomer.getScene().getWindow();
     * stage.close();
     * }
     */

    public void handleBtnBack() throws IOException {
        Stage switcher = (Stage) btnBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/AdminView.fxml"));
        switcher.setTitle("Admin Management");
        Scene scene = new Scene(root);
        switcher.setScene(scene);
    }
}
