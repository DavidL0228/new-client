import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.text.Text;

public class homeScreenController extends SmartHomeController {

	@FXML
    private Button addDeviceButton;
	
    @FXML
    private Button addNewRoomButton;

    @FXML
    private TableView<Device> deviceTable;

    @FXML
    private TableColumn<Device, String> iconColumn;

    @FXML
    private TableColumn<Device, String> nameColumn;

    @FXML
    private TableColumn<Device, String> statusColumn;

	@FXML
	private Text welcomeText;

    @FXML
    void addDevice(MouseEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("addDeviceMenu.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	Scene scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }

    @FXML
    void gotoDevice(MouseEvent event) throws IOException {
    	Parent root;
    	Stage stage;
    	Scene scene;
    	
    	switch(deviceTable.getSelectionModel().getSelectedItem().getDeviceType()) {
    	case "light":
			setCurrentDeviceName(deviceTable.getSelectionModel().getSelectedItem().getDeviceName());
    		root = FXMLLoader.load(getClass().getResource("lightMenu.fxml"));
        	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        	scene = new Scene(root);
        	stage.setScene(scene);
        	stage.show();
    		break;
    	case "fan":
			setCurrentDeviceName(deviceTable.getSelectionModel().getSelectedItem().getDeviceName());
    		root = FXMLLoader.load(getClass().getResource("fanMenu.fxml"));
        	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        	scene = new Scene(root);
        	stage.setScene(scene);
        	stage.show();
    		break;
    	case "lock":
			setCurrentDeviceName(deviceTable.getSelectionModel().getSelectedItem().getDeviceName());
    		root = FXMLLoader.load(getClass().getResource("lockMenu.fxml"));
        	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        	scene = new Scene(root);
        	stage.setScene(scene);
        	stage.show();
    		break;
    	case "smokeAlarm":
			setCurrentDeviceName(deviceTable.getSelectionModel().getSelectedItem().getDeviceName());
    		root = FXMLLoader.load(getClass().getResource("smokeAlarmMenu.fxml"));
        	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        	scene = new Scene(root);
        	stage.setScene(scene);
        	stage.show();
    		break;
    	case "thermostat":
			setCurrentDeviceName(deviceTable.getSelectionModel().getSelectedItem().getDeviceName());
    		root = FXMLLoader.load(getClass().getResource("thermostatMenu.fxml"));
        	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        	scene = new Scene(root);
        	stage.setScene(scene);
        	stage.show();
    		break;
    	}
    }
    
    public ObservableList<Device> displayDevices;

	public void setDisplayDevices(ArrayList<Device> devices) {
		displayDevices = FXCollections.observableArrayList();
		System.out.println("device 1 name:" + devices.get(0).getDeviceName());
		this.displayDevices = (ObservableList<Device>) devices;
	}


    public void initialize() {
    	//sets up client
    	client.setController(this);

		client.requestConnectedDevices();

		//System.out.println(displayDevices.getFirst().getDeviceName());

		//displays the proper username
		welcomeText.setText("Welcome Home, " + getUsername());

		//sets up table display
    	iconColumn.setCellValueFactory(new PropertyValueFactory<>("deviceType"));
    	nameColumn.setCellValueFactory(new PropertyValueFactory<>("deviceName"));
    	statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

		//temp, creates temp fake devices
    	displayDevices = FXCollections.observableArrayList();


    	deviceTable.setItems(displayDevices);

    }
    
}
