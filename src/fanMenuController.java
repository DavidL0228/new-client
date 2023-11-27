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

public class fanMenuController {

	@FXML
    private Button addScheduleButton;

    @FXML
    private Button backButtonController;

    @FXML
    private Button fanButton;

    @FXML
    private Text fanName;

    @FXML
    private Slider speedSlider;

    @FXML
    private Text speedText;

    @FXML
    private Text status;
    
    @FXML
    private Slider tempSlider;

    @FXML
    private Text tempText;

    @FXML
    void adjustSpeed(MouseEvent event) {
    	int sliderValue = (int)speedSlider.getValue();
    	speedText.setText(sliderValue + "%");
    }

    @FXML
    void adjustTemp(MouseEvent event) {
    	int sliderValue = (int)tempSlider.getValue();
    	tempText.setText(sliderValue + "%");
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
    void toggleFan(ActionEvent event) {

    }

    @FXML
    void updateSpeed(MouseEvent event) {
    	int sliderValue = (int)speedSlider.getValue();
    	speedText.setText(sliderValue + "%");
    	System.out.println((int)speedSlider.getValue());
    }

    @FXML
    void updateTemp(MouseEvent event) {
    	int sliderValue = (int)tempSlider.getValue();
    	tempText.setText(sliderValue + "%");
    	System.out.println((int)tempSlider.getValue());
    }
    
    
  //updates all the display elements of the UI to the data received from the server
    public void displayFanStatus() {
    	//sets the name
    	fanName.setText("Bedroom Fan");
    	//sets the status
    	status.setText("Off");
    	//sets the speed
    	speedText.setText("0%");
    	speedSlider.setValue(0);
    	//sets the temperature
    	tempText.setText("0%");
    	tempSlider.setValue(0);	
    }
    
    //called when screen is first shown
    public void initialize() {
    	//client.setController(this);
    	displayFanStatus();	
    }
    
}
