/*
 * Author: David Loovere
 * Course: ESOF 3050
 * Description: Controller class for the light menu in the Smart Home application.
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class lightMenuController extends SmartHomeController {

    // FXML elements
    @FXML
    private TextField hoursField;

    @FXML
    private TextField minField;

    @FXML
    private TextField secField;

    @FXML
    private Button addScheduleButton;

    @FXML
    private Button backButtonController;

    @FXML
    private Slider brightnessSlider;

    @FXML
    private Text brightnessText;

    @FXML
    private Button lightButton;

    @FXML
    private Text lightName;

    @FXML
    private Text status;

    @FXML
    private Button updateTimeoutButton;

    @FXML
    private Button addUserButton;

    @FXML
    private ImageView lightImage;

    // Navigates to the add user screen
    @FXML
    void addUser(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("addDeviceUserMenu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Smoothly adjusts the brightness text to match the slider. Does not send data to the server until release
    @FXML
    void adjustBrightness(MouseEvent event) {
        int sliderValue = (int) brightnessSlider.getValue();
        brightnessText.setText(sliderValue + "%");
    }

    // Code for the back button
    @FXML
    void goBackToMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("homeScreenMenu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Navigates to the schedule menu
    @FXML
    void gotoAddSchedule(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("scheduleMenu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Checks the current state of the light and, on press, changes to the other state
    @FXML
    void toggleLight(MouseEvent event) {
        System.out.println("Light toggle click registered");

        if (status.getText().equals("ON")) {
            System.out.println("Turning Off");
            client.turnOffLight(getCurrentDeviceName());
        }
        if (status.getText().equals("OFF")) {
            System.out.println("Turning on ");
            client.turnOnLight(getCurrentDeviceName());
        }

        // Updates the info and display
        client.requestLightStatus(getCurrentDeviceName());
    }

    // When the slider is released, the new brightness is sent to the server
    @FXML
    void updateBrightness(MouseEvent event) {
        int sliderValue = (int) brightnessSlider.getValue();
        brightnessText.setText(sliderValue + "%");
        System.out.println((int) brightnessSlider.getValue());
        client.adjustLightBrightness(getCurrentDeviceName(), sliderValue);

        client.requestLightStatus(getCurrentDeviceName());
    }

    //updates the light auto time out time
    @FXML
    void updateTimeout(MouseEvent event) {
        //checks inputs are valid
        try {
            if (!(Integer.parseInt(hoursField.getText()) < 0) && !(Integer.parseInt(minField.getText()) < 0) && !(Integer.parseInt(secField.getText()) < 0)) {
                //sets new auto time out time
                int seconds = (Integer.parseInt(hoursField.getText()) * 60 * 60) +
                        (Integer.parseInt(minField.getText()) * 60) +
                        Integer.parseInt(secField.getText());
                System.out.println("New seconds input: " + seconds);
                client.sendLightByMotionTime(getCurrentDeviceName(), seconds, (int) brightnessSlider.getValue());
            }
        } catch(NumberFormatException ex){
            //catches if a user input a number incorrectly
            System.out.println("Incorrect format");
        }
        //refreshes display
        client.requestLightStatus(getCurrentDeviceName());
    }

    // Updates all the display elements of the UI to the data received from the server
    public void displayLightStatus(String _deviceName, String _isLightOn, int intensity,
                                   int timeoutSeconds, int timeoutMins, int timeoutHours) {
        Platform.runLater(() -> {
            System.out.println("Display new light status");
            System.out.println("Timeout" + timeoutHours + ":" + timeoutMins + ":" + timeoutSeconds);
            // Sets the name
            lightName.setText(getCurrentDeviceName());
            // Sets the status
            status.setText(_isLightOn);
            if (_isLightOn.equals("ON")) {
                lightImage.setImage(new Image("icons/Light_On_Bright.png", true));
            } else {
                lightImage.setImage(new Image("icons/light_Off.png", true));
            }

            // Sets the brightness
            brightnessText.setText(intensity + "%");
            brightnessSlider.setValue(intensity);

            // Sets the timeout time
            if (timeoutHours > 0) {
                hoursField.setText(String.valueOf(timeoutHours));
            } else {
                hoursField.setText("0");
            }

            if (timeoutMins > 0) {
                minField.setText(String.valueOf(timeoutMins));
            } else {
                minField.setText("0");
            }

            if (timeoutSeconds > 0) {
                secField.setText(String.valueOf(timeoutSeconds));
            } else {
                secField.setText("0");
            }

            System.out.println("Light intensity: " + intensity);
        });
    }

    // Called when the screen is first shown
    public void initialize() {
        lightName.setText(getCurrentDeviceName());
        client.setController(this);
        client.requestLightStatus(getCurrentDeviceName());
    }
}
