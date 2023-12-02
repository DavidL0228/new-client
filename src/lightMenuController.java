import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

public class lightMenuController extends SmartHomeController {

    //<editor-fold desc="menu items">
    @FXML
    private MenuItem HButton0;

    @FXML
    private MenuItem HButton1;

    @FXML
    private MenuItem HButton2;

    @FXML
    private MenuItem HButton3;

    @FXML
    private MenuItem HButton4;

    @FXML
    private MenuItem HButton5;

    @FXML
    private MenuItem HButton6;

    @FXML
    private MenuItem HButton7;

    @FXML
    private MenuItem HButton8;

    @FXML
    private MenuItem HButton9;

    @FXML
    private MenuItem MButton0;

    @FXML
    private MenuItem MButton1;

    @FXML
    private MenuItem MButton2;

    @FXML
    private MenuItem MButton3;

    @FXML
    private MenuItem MButton4;

    @FXML
    private MenuItem MButton5;

    @FXML
    private MenuItem MButton6;

    @FXML
    private MenuItem MButton7;

    @FXML
    private MenuItem MButton8;

    @FXML
    private MenuItem MButton9;

    @FXML
    private MenuItem SButton0;

    @FXML
    private MenuItem SButton1;

    @FXML
    private MenuItem SButton2;

    @FXML
    private MenuItem SButton3;

    @FXML
    private MenuItem SButton4;

    @FXML
    private MenuItem SButton5;

    @FXML
    private MenuItem SButton6;

    @FXML
    private MenuItem SButton7;

    @FXML
    private MenuItem SButton8;

    @FXML
    private MenuItem SButton9;
    //</editor-fold>

    @FXML
    private MenuButton hoursField;

    @FXML
    private MenuButton minField;

    @FXML
    private MenuButton secField;

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

    //smoothly adjusts the brightness text to match the slider. Does not send data to server untill release
    @FXML
    void adjustBrightness(MouseEvent event) {
          int sliderValue = (int) brightnessSlider.getValue();
          brightnessText.setText(sliderValue + "%");
    }

    //code for the back button
    @FXML
    void goBackToMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("homeScreenMenu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void gotoAddSchedule(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("scheduleMenu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //checks the current state of the light, and on press changes to the other state
    @FXML
    void toggleLight(MouseEvent event) {
        System.out.println("light toggle click registered");

        if (status.getText().equals("On")) {
            System.out.println("Turning Off");
            client.turnOffLight(getCurrentDeviceName());
        }
        if (status.getText().equals("Off")) {
            System.out.println("Turning on ");
            client.turnOnLight(getCurrentDeviceName());
        }

        //updates the info and display
        client.requestLightStatus(getCurrentDeviceName());
    }

    //when the slider is released, then the new brightness is sent to the server
    @FXML
    void updateBrightness(MouseEvent event) {
        int sliderValue = (int) brightnessSlider.getValue();
        brightnessText.setText(sliderValue + "%");
        System.out.println((int) brightnessSlider.getValue());
        client.adjustLightBrightness(getCurrentDeviceName(), sliderValue);

        client.requestLightStatus(getCurrentDeviceName());
    }


    @FXML
    void updateTimeout(MouseEvent event) {

        int seconds = (h * 60 * 60) + (m * 60) + s;
        client.sendLightByMotionTime(getCurrentDeviceName(), seconds, (int) brightnessSlider.getValue());
    }

    //updates all the display elements of the UI to the data received from the server
    public void displayLightStatus(String _deviceName, String _isLightOn, int intensity, int timeoutSeconds, int timeoutMins, int timeoutHours) {
        System.out.println("Light intensity: " + intensity);

        //sets the name
        lightName.setText(getCurrentDeviceName());
        //sets the status
        status.setText(_isLightOn);
        //sets the brightness
        brightnessText.setText(intensity + "%");
        brightnessSlider.setValue(intensity);
        //sets the timeout time
        hoursField.setText(String.valueOf(timeoutHours));
        minField.setText(String.valueOf(timeoutMins));
        secField.setText(String.valueOf(timeoutSeconds));

        System.out.println("Light intensity: " + intensity);
    }

    //called when screen is first shown
    public void initialize() {
        lightName.setText(getCurrentDeviceName());
        client.setController(this);
        client.requestLightStatus(getCurrentDeviceName());
    }


    //<editor-fold desc="set buttons">
    int h, m, s = 0;

    @FXML
    void setH0(ActionEvent event) {
        h = 0;
        hoursField.setText("0");
    }

    @FXML
    void setH1(ActionEvent event) {
        h = 1;
        hoursField.setText("1");
    }

    @FXML
    void setH2(ActionEvent event) {
        h = 2;
        hoursField.setText("2");
    }

    @FXML
    void setH3(ActionEvent event) {
        h = 3;
        hoursField.setText("3");
    }

    @FXML
    void setH4(ActionEvent event) {
        h = 4;
        hoursField.setText("4");
    }

    @FXML
    void setH5(ActionEvent event) {
        h = 5;
        hoursField.setText("5");
    }

    @FXML
    void setH6(ActionEvent event) {
        h = 6;
        hoursField.setText("6");
    }

    @FXML
    void setH7(ActionEvent event) {
        h = 7;
        hoursField.setText("7");
    }

    @FXML
    void setH8(ActionEvent event) {
        h = 8;
        hoursField.setText("8");
    }

    @FXML
    void setH9(ActionEvent event) {
        h = 9;
        hoursField.setText("9");
    }

    @FXML
    void setM0(ActionEvent event) {
        m = 0;
        minField.setText("0");
    }

    @FXML
    void setM1(ActionEvent event) {
        m = 1;
        minField.setText("1");
    }

    @FXML
    void setM2(ActionEvent event) {
        m = 2;
        minField.setText("2");
    }

    @FXML
    void setM3(ActionEvent event) {
        m = 3;
        minField.setText("3");
    }

    @FXML
    void setM4(ActionEvent event) {
        m = 4;
        minField.setText("4");
    }

    @FXML
    void setM5(ActionEvent event) {
        m = 5;
        minField.setText("5");
    }

    @FXML
    void setM6(ActionEvent event) {
        m = 6;
        minField.setText("6");
    }

    @FXML
    void setM7(ActionEvent event) {
        m = 7;
        minField.setText("7");
    }

    @FXML
    void setM8(ActionEvent event) {
        m = 8;
        minField.setText("8");
    }

    @FXML
    void setM9(ActionEvent event) {
        m = 9;
        minField.setText("9");
    }

    @FXML
    void setS0(ActionEvent event) {
        s = 0;
        secField.setText("0");
    }

    @FXML
    void setS1(ActionEvent event) {
        s = 1;
        secField.setText("1");
    }

    @FXML
    void setS2(ActionEvent event) {
        s = 2;
        secField.setText("2");
    }

    @FXML
    void setS3(ActionEvent event) {
        s = 3;
        secField.setText("3");
    }

    @FXML
    void setS4(ActionEvent event) {
        s = 4;
        secField.setText("4");
    }

    @FXML
    void setS5(ActionEvent event) {
        s = 5;
        secField.setText("5");
    }

    @FXML
    void setS6(ActionEvent event) {
        s = 6;
        secField.setText("6");
    }

    @FXML
    void setS7(ActionEvent event) {
        s = 7;
        secField.setText("7");
    }

    @FXML
    void setS8(ActionEvent event) {
        s = 8;
        secField.setText("8");
    }

    @FXML
    void setS9(ActionEvent event) {
        s = 9;
        secField.setText("9");
    }
    //</editor-fold>

}
