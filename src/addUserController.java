/*
 * Author: David Loovere
 * Course: ESOF 3050
 * Description: Controller class for adding a user through the registration process in the Smart Home application.
 */

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.text.Text;

import java.io.IOException;

public class addUserController extends SmartHomeController {

    // FXML elements
    @FXML
    private Button addUserButton;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button gotoLoginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private Text errorText;

    // Represents the success status of the registration process
    private int success = -1;

    // Handles adding a new user through the registration process
    @FXML
    void addUser(MouseEvent event) throws IOException {
        errorText.setVisible(false);

        // Checks if username, password, and confirm password fields are not empty
        if (!(usernameField.getText().isEmpty()) && !(passwordField.getText().isEmpty()) && !(confirmPasswordField.getText().isEmpty())) {
            // Checks if the entered passwords match
            if (passwordField.getText().equals((confirmPasswordField.getText()))) {
                // Sends registration information to the server
                client.sendRegisterInfo(usernameField.getText(), passwordField.getText());

                // Waits for the success status
                while (success <= -1) {
                    System.out.println("looping");
                }

                // Processes based on the success status
                if (success == 1) {
                    setUsername(usernameField.getText());

                    // Navigates to the main screen if successful
                    Parent root = FXMLLoader.load(getClass().getResource("homeScreenMenu.fxml"));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } else if (success == 0) {
                    errorText.setVisible(true);
                    errorText.setText("ERROR: User already exists");
                }
            } else {
                // Displays error if passwords do not match
                System.out.println(passwordField.getText() + "!=" + confirmPasswordField.getText());
                errorText.setVisible(true);
                errorText.setText("ERROR: Passwords do not match");
            }
        } else {
            // Displays error for empty fields
            errorText.setVisible(true);
            errorText.setText("ERROR: Empty Field Detected");
        }

        // Resets success status
        success = -1;
    }

    // Navigates to the login screen
    @FXML
    void gotoLogin(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("loginMenu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Initializes the controller
    public void initialize() {
        client.setController(this);
    }

    // Getter for success
    public int getSuccess() {
        return success;
    }

    // Setter for success
    public void setSuccess(int success) {
        this.success = success;
    }
}
