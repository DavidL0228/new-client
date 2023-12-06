/*
 * Author: David Loovere
 * Course: ESOF 3050
 * Description: Controller class for the fan menu in the Smart Home application.
 */

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class fanMenuController extends SmartHomeController {

    // FXML elements
    @FXML
    private Button addScheduleButton;

    @FXML
    private Button backButtonController;

    @FXML
    private Button fanButton;

    @FXML
    private Text fanName;

    @FXML
    private Button highButton;

    @FXML
    private Button lowButton;

    @FXML
    private Button mediumButton;

    @FXML
    private Text speedText;

    @FXML
    private Text status;

    @FXML
    private Slider tempSlider;

    @FXML
    private Text tempText;

    @FXML
    private Button addUserButton;

    // Navigates to the add user screen
    @FXML
    void addUser(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("addDeviceUserMenu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Updates the displayed temperature based on the slider value
    @FXML
    void adjustTemp(MouseEvent event) {
        int sliderValue = (int) tempSlider.getValue();
        tempText.setText(sliderValue + "°");
    }

    // Navigates back to the main screen
    @FXML
    void goBackToMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("homeScreenMenu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Placeholder for future functionality
    @FXML
    void gotoAddSchedule(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("scheduleMenu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Sets the fan speed to low
    @FXML
    void setLow(MouseEvent event) {
        if (status.getText().equals("ON")) {
            client.adjustFanSpeed(getCurrentDeviceName(), 1);
            client.requestFanStatus(getCurrentDeviceName());
        } else {
            // Do nothing
        }
    }

    // Sets the fan speed to medium
    @FXML
    void setMedium(MouseEvent event) {
        if (status.getText().equals("ON")) {
            client.adjustFanSpeed(getCurrentDeviceName(), 2);
            client.requestFanStatus(getCurrentDeviceName());
        } else {
            // Do nothing
        }
    }

    // Sets the fan speed to high
    @FXML
    void setHigh(MouseEvent event) {
        if (status.getText().equals("ON")) {
            client.adjustFanSpeed(getCurrentDeviceName(), 3);
            client.requestFanStatus(getCurrentDeviceName());
        } else {
            // Do nothing
        }
    }

    // Toggles the fan on or off
    @FXML
    void toggleFan(ActionEvent event) {
        if (status.getText().equals("ON")) {
            System.out.println("Turning Off");
            client.turnOffFan(getCurrentDeviceName());
        }
        if (status.getText().equals("OFF")) {
            System.out.println("Turning on ");
            client.turnOnFan(getCurrentDeviceName());
            client.adjustFanSpeed(getCurrentDeviceName(), 1);
        }

        client.requestFanStatus(getCurrentDeviceName());
    }

    // Updates the temperature based on the slider value
    @FXML
    void updateTemp(MouseEvent event) {
        int sliderValue = (int) tempSlider.getValue();
        tempText.setText(sliderValue + "°");
        System.out.println((int) tempSlider.getValue());
        client.adjustFanTemperature(getCurrentDeviceName(), sliderValue);
        client.requestFanStatus(getCurrentDeviceName());
    }

    // Updates all the display elements of the UI to the data received from the server
    public void displayFanStatus(String _fanName, String _status, int _fanSpeed, int _temperature) {
        Platform.runLater(() -> {
            // Sets the name
            fanName.setText(_fanName);
            // Sets the status
            status.setText(_status);
            // Sets the speed
            System.out.println("Fan speed:" + _fanSpeed);
            if (_fanSpeed == 1) {
                speedText.setText("Low");
            } else if (_fanSpeed == 2) {
                speedText.setText("Medium");
            } else if (_fanSpeed == 3) {
                speedText.setText("High");
            } else if (_fanSpeed == 0) {
                speedText.setText("Unset");
            } else {
                speedText.setText("bruh");
            }

            // Sets the temperature
            tempText.setText(_temperature + "°");
            tempSlider.setValue(_temperature);
        });
    }

    // Called when the screen is first shown
    public void initialize() {
        fanName.setText(getCurrentDeviceName());
        client.setController(this);
        client.requestFanStatus(getCurrentDeviceName());
    }
}
