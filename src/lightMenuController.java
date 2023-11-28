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

public class lightMenuController extends SmartHomeController {

	@FXML
    private Button addScheduleButton;

    @FXML
    private Button backButtonController;

    @FXML
    private Slider brightnessSlider;

    @FXML
    private Text brightnessText;

    @FXML
    private TextField hoursField;

    @FXML
    private Button lightButton;

    @FXML
    private Text lightName;

    @FXML
    private TextField minField;

    @FXML
    private TextField secField;

    @FXML
    private Text status;

    @FXML
    private Button updateTimeoutButton;

    //smoothly adjusts the brightness text to match the slider. Does not send data to server
    @FXML
    void adjustBrightness(MouseEvent event) {
    	int sliderValue = (int)brightnessSlider.getValue();
    	brightnessText.setText(sliderValue + "%");
    }

    //code for the back button
    @FXML
    void goBackToMain(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("homeScreenMenu.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	Scene scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }

    @FXML
    void gotoAddSchedule(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("scheduleMenu.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    //checks the current state of the light, and on press changes to the other state
    @FXML
    void toggleLight(MouseEvent event) {

    }

    //when the slider is released, then the new brightness is sent to the server
    @FXML
    void updateBrightness(MouseEvent event) {
    	int sliderValue = (int)brightnessSlider.getValue();
    	brightnessText.setText(sliderValue + "%");
    	System.out.println((int)brightnessSlider.getValue());
    }

    @FXML
    void updateTimeout(MouseEvent event) {
    	
    }
    
    //updates all the display elements of the UI to the data received from the server
    public void displayLightStatus(String _deviceName, String _isLightOn, int intensity, int timeoutSeconds, int timeoutMins, int timeoutHours) {
    	//sets the name
    	lightName.setText(getCurrentDeviceName());
    	//sets the status
    	status.setText("Off");
    	//sets the brightness
    	brightnessText.setText("0%");
    	brightnessSlider.setValue(0);
    	//sets the timeout time
    	hoursField.setText("1");
    	minField.setText("10");
    	secField.setText("10");
    	
    }
    
    //called when screen is first shown
    public void initialize() {
        lightName.setText(getCurrentDeviceName());
    	client.setController(this);
    	client.requestLightStatus(getCurrentDeviceName());
    }
    
}
