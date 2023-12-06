/*
 * Author: David Loovere
 * Course: ESOF 3050
 * Description: Controller class for the thermostat menu screen in the Smart Home application.
 */

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class thermostatMenuController extends SmartHomeController {

    // FXML elements
    @FXML
    private MenuItem CoolingButton;

    @FXML
    private Button addScheduleButton;

    @FXML
    private MenuButton airCircMode;

    @FXML
    private Button backButtonController;

    @FXML
    private TextField currentTemp;

    @FXML
    private Button decreaseTemp;

    @FXML
    private MenuItem heatingButton;

    @FXML
    private Button increaseTemp;

    @FXML
    private TextField maxTemp;

    @FXML
    private TextField minTemp;

    @FXML
    private MenuItem noButton;

    @FXML
    private Text statusText;

    @FXML
    private MenuButton tempMode;

    @FXML
    private Button thermostatButton;

    @FXML
    private Text thermostatName;

    @FXML
    private Button updateButton;

    @FXML
    private MenuItem yesButton;

    @FXML
    private Button addUserButton;

    // Navigate to the add user screen
    @FXML
    void addUser(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("addDeviceUserMenu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Decrease the temperature when the button is clicked
    @FXML
    void decreaseTemp(MouseEvent event) {
        client.modifyTemperatureNow(getCurrentDeviceName(), Integer.parseInt(currentTemp.getText()) - 1);
        client.requestThermostatStatus(getCurrentDeviceName());
    }

    // Increase the temperature when the button is clicked
    @FXML
    void increaseTemp(MouseEvent event) {
        client.modifyTemperatureNow(getCurrentDeviceName(), Integer.parseInt(currentTemp.getText()) + 1);
        client.requestThermostatStatus(getCurrentDeviceName());
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

    // Open the add schedule menu
    @FXML
    void gotoAddSchedule(MouseEvent event) {
        // Add logic to navigate to the add schedule menu
        // This method will be completed with the specific functionality for adding a schedule
    }

    // Set the thermostat to cooling mode
    @FXML
    void setCooling(ActionEvent event) {
        client.makeThermostatCooling(getCurrentDeviceName());
        client.requestThermostatStatus(getCurrentDeviceName());
    }

    // Set the thermostat to heating mode
    @FXML
    void setHeating(ActionEvent event) {
        client.makeThermostatHeating(getCurrentDeviceName());
        client.requestThermostatStatus(getCurrentDeviceName());
    }

    // Turn off air circulation
    @FXML
    void setNo(ActionEvent event) {
        client.turnOffCirculation(getCurrentDeviceName());
        client.requestThermostatStatus(getCurrentDeviceName());
    }

    // Turn on air circulation
    @FXML
    void setYes(ActionEvent event) {
        client.turnOnCirculation(getCurrentDeviceName());
        client.requestThermostatStatus(getCurrentDeviceName());
    }

    // Toggle the thermostat status (ON/OFF)
    @FXML
    void toggleThermostat(MouseEvent event) {
        if (statusText.getText().equals("ON")) {
            System.out.println("Turning Off");
            client.turnOffThermostat(getCurrentDeviceName());
        }
        if (statusText.getText().equals("OFF")) {
            System.out.println("Turning on ");
            client.turnOnThermostat(getCurrentDeviceName());
        }

        // Update the info and display
        client.requestThermostatStatus(getCurrentDeviceName());
    }

    // Update temperature settings
    @FXML
    void updateTemps(MouseEvent event) {
        client.maintainTemperatureRange(getCurrentDeviceName(), Integer.parseInt(maxTemp.getText()), Integer.parseInt(minTemp.getText()));
        client.modifyTemperatureNow(getCurrentDeviceName(), Integer.parseInt(currentTemp.getText()));
        client.requestThermostatStatus(getCurrentDeviceName());
    }

    // Display thermostat status on the UI
    public void displayThermostatStatus(String _status, int _min, int _max, int _cur, String _isCirc, String _mode) {
        Platform.runLater(() -> {
            System.out.println("Updating thermostat display");
            System.out.println("mode: " + _mode);

            statusText.setText(_status);

            if (_min > 0) {
                minTemp.setText(String.valueOf(_min));
            } else {
                minTemp.setText("0");
            }

            if (_max > 0) {
                maxTemp.setText(String.valueOf(_max));
            } else {
                maxTemp.setText("0");
            }

            if (_cur > 0) {
                currentTemp.setText(String.valueOf(_cur));
                thermostatButton.setText(String.valueOf(_cur) + "°");
            } else {
                currentTemp.setText("0");
                thermostatButton.setText("0°");
            }

            airCircMode.setText(_isCirc);
            tempMode.setText(_mode);

            System.out.println("Update thermostat display worked");
        });
    }

    // Initialize the controller
    public void initialize() {
        thermostatName.setText(getCurrentDeviceName());
        client.setController(this);
        client.requestThermostatStatus(getCurrentDeviceName());
    }

}