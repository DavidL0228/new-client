/*
 * Author: David Loovere
 * Course: ESOF 3050
 * Description: Controller class for the login screen in the Smart Home application.
 */

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.text.Text;

import java.io.IOException;

public class loginController extends SmartHomeController {

    // FXML elements
    @FXML
    private Button addUserButton;

    @FXML
    private Text errorText;

    @FXML
    private Button loginButton;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField usernameField;

    // Variable to track login success
    private int success = -1;

    // Setter for success variable
    public void setSuccess(int _success) {
        success = _success;
    }

    // Attempt to perform login on button click
    @FXML
    void attemptLogin(MouseEvent event) throws IOException {
        errorText.setVisible(false);

        // Get username and password from input fields
        String username = usernameField.getText();
        String password = passwordField.getText();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Send login information to the server
        client.sendLoginInfo(username, password);

        // Wait for the server response
        while (success < 0) {
            // Loop until the server response is received
            System.out.println("looping");
        }

        // Process the server response
        if (success == 0) {
            errorText.setVisible(true);
            errorText.setText("ERROR: Incorrect Username or Password");
        } else if (success == 1) {
            loginSuccess(stage);
        }

        // Reset success variable
        success = -1;
    }

    // Method to handle login success
    void loginSuccess(Stage stage) throws IOException {
        System.out.println("login success");

        // Set the local username for use in the application
        setUsername(usernameField.getText());

        // Switch to the home screen
        Parent root = FXMLLoader.load(getClass().getResource("homeScreenMenu.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    // Navigate to the add user screen
    @FXML
    void gotoAddUser(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("addUserMenu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Initialization method
    public void initialize() {
        // Initialize the SmartClient
        client = new SmartClient("172.17.1.212", 9200);
        client.setController(this);
        try {
            client.openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
