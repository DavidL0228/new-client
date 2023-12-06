/*
 * Author: David Loovere
 * Course: ESOF 3050
 * Description: Controller class for the smoke alarm menu screen in the Smart Home application.
 */

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class smokeAlarmMenuController extends SmartHomeController {

    // FXML elements
    @FXML
    private Text alarmName;

    @FXML
    private Button backButtonController;

    @FXML
    private Text batteryPercent;

    @FXML
    private Button smokeAlarmButton;

    @FXML
    private Text smokeAmount;

    @FXML
    private Text smokeThresholdExceeded;

    @FXML
    private Text statusText;

    @FXML
    private Button addUserButton;

    @FXML
    private Button resetButton;

    @FXML
    private Button testButton;

    // Navigate to the add user screen
    @FXML
    void addUser(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("addDeviceUserMenu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Navigate back to the main screen
    @FXML
    void goBackToMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("homeScreenMenu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Method to handle toggling the smoke alarm
    @FXML
    void toggleSmokeAlarm(MouseEvent event) {
        // Add logic to toggle the smoke alarm based on user input
        // This method will be completed with the specific functionality for toggling the smoke alarm
    }

    // Method to handle resetting the smoke alarm
    @FXML
    void resetSmokeAlarm(MouseEvent event) {
        client.modifySmokeAmount(getCurrentDeviceName(), 0);
        client.requestSmokeAlarmStatus(getCurrentDeviceName());
    }

    // Method to handle testing the smoke alarm
    @FXML
    void testSmokeAlarm(MouseEvent event) {
        client.modifySmokeAmount(getCurrentDeviceName(), 100);
        client.requestSmokeAlarmStatus(getCurrentDeviceName());
    }

    // Method to update the display with smoke alarm status
    public void displaySmokeAlarmStatus(String _status, int _smokeAmount, String _smokeThresholdExceeded, int _batteryPercent) {
        Platform.runLater(() -> {
            statusText.setText(_status);
            smokeAmount.setText(String.valueOf(_smokeAmount));
            smokeThresholdExceeded.setText(_smokeThresholdExceeded);
            batteryPercent.setText(String.valueOf(_batteryPercent));
        });
    }

    // Initialize the controller
    public void initialize() {
        alarmName.setText(getCurrentDeviceName());
        client.setController(this);
        // Request initial smoke alarm status when the screen is first shown
        client.requestSmokeAlarmStatus(getCurrentDeviceName());
    }
}
