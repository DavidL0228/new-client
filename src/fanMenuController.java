import java.io.IOException;

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

public class fanMenuController extends SmartHomeController{

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

   // @FXML
    //void adjustSpeed(MouseEvent event) {
    	//int sliderValue = (int)speedSlider.getValue();
    	//speedText.setText(sliderValue + "%");
    //}

    @FXML
    void adjustTemp(MouseEvent event) {
    	int sliderValue = (int)tempSlider.getValue();
    	tempText.setText(sliderValue + "°");
    }
    
    @FXML
    void goBackToMain(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("homeScreenMenu.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	Scene scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }

    @FXML
    void gotoAddSchedule(MouseEvent event) {

    }

    @FXML
    void setLow(MouseEvent event) {
        client.adjustFanSpeed(getCurrentDeviceName(), 1);
        client.requestFanStatus(getCurrentDeviceName());
    }

    @FXML
    void setMedium(MouseEvent event) {
        client.adjustFanSpeed(getCurrentDeviceName(), 2);
        client.requestFanStatus(getCurrentDeviceName());
    }
    @FXML
    void setHigh(MouseEvent event){
        client.adjustFanSpeed(getCurrentDeviceName(), 3);
        client.requestFanStatus(getCurrentDeviceName());
    }

    @FXML
    void toggleFan(ActionEvent event) {
        if (status.getText().equals("On")) {
            System.out.println("Turning Off");
            client.turnOffFan(getCurrentDeviceName());
        }
        if (status.getText().equals("Off")) {
            System.out.println("Turning on ");
            client.turnOnFan(getCurrentDeviceName());
            client.adjustFanSpeed(getCurrentDeviceName(), 1);
        }

        client.requestFanStatus(getCurrentDeviceName());
    }

   // @FXML
    //void updateSpeed(MouseEvent event) {
    	//int sliderValue = (int)speedSlider.getValue();
    	//speedText.setText(sliderValue + "%");
    	//System.out.println((int)speedSlider.getValue());
        //client.adjustFanSpeed(getCurrentDeviceName(), sliderValue);
        //client.requestFanStatus(getCurrentDeviceName());
    //}

    @FXML
    void updateTemp(MouseEvent event) {
    	int sliderValue = (int)tempSlider.getValue();
    	tempText.setText(sliderValue + "°");
    	System.out.println((int)tempSlider.getValue());
        client.adjustFanTemperature(getCurrentDeviceName(), sliderValue);
        client.requestFanStatus(getCurrentDeviceName());
    }
    
    
  //updates all the display elements of the UI to the data received from the server
    public void displayFanStatus(String _fanName, String _status, int _fanSpeed, int _temperature) {
    	//sets the name
    	fanName.setText(_fanName);
    	//sets the status
    	status.setText(_status);
    	//sets the speed
        System.out.println("Fan speed:" + _fanSpeed);
    	if(_fanSpeed == 1){
            speedText.setText("Low");
        }else if(_fanSpeed == 2){
            speedText.setText("Medium");
        }else if(_fanSpeed == 3){
            speedText.setText("High");
        }else if(_fanSpeed == 0){
            speedText.setText("Unset");
        } else {
            speedText.setText("bruh");
        }

    	//sets the temperature
    	tempText.setText(_temperature + "°");
    	tempSlider.setValue(_temperature);
    }
    
    //called when screen is first shown
    public void initialize() {
        fanName.setText(getCurrentDeviceName());
        client.setController(this);
        client.requestFanStatus(getCurrentDeviceName());
    }
    
}
