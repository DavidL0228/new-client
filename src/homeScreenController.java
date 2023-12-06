/*
 * Author: David Loovere
 * Course: ESOF 3050
 * Description: Controller class for the home screen of the Smart Home application.
 */

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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.text.Text;

public class homeScreenController extends SmartHomeController {

	// FXML elements
	@FXML
	private Button logout;

	@FXML
    private Button addDeviceButton;

    @FXML
    private TableView<Device> deviceTable;

    @FXML
    private TableColumn<Device, String> iconColumn;

    @FXML
    private TableColumn<Device, String> nameColumn;

    @FXML
    private TableColumn<Device, String> statusColumn;

	@FXML
	private TableColumn<Device, Button> delColumn;

	@FXML
	private Text welcomeText;


	// Handles adding a new device
    @FXML
    void addDevice(MouseEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("addDeviceMenu.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	Scene scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }

	// Handles navigation to device-specific menus
    @FXML
    void gotoDevice(MouseEvent event) throws IOException {
    	Parent root;
    	Stage stage;
    	Scene scene;

		//gets the current clicked row, and goes to relevant GUI page from that
    	switch(deviceTable.getSelectionModel().getSelectedItem().getDeviceType()) {
    	case "SMART_LIGHT":
			setCurrentDeviceName(deviceTable.getSelectionModel().getSelectedItem().getDeviceName());
    		root = FXMLLoader.load(getClass().getResource("lightMenu.fxml"));
        	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        	scene = new Scene(root);
        	stage.setScene(scene);
        	stage.show();
    		break;
    	case "SMART_FAN":
			setCurrentDeviceName(deviceTable.getSelectionModel().getSelectedItem().getDeviceName());
    		root = FXMLLoader.load(getClass().getResource("fanMenu.fxml"));
        	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        	scene = new Scene(root);
        	stage.setScene(scene);
        	stage.show();
    		break;
    	case "SMART_LOCK":
			setCurrentDeviceName(deviceTable.getSelectionModel().getSelectedItem().getDeviceName());
    		root = FXMLLoader.load(getClass().getResource("lockMenu.fxml"));
        	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        	scene = new Scene(root);
        	stage.setScene(scene);
        	stage.show();
    		break;
    	case "SMART_SMOKE_ALARM":
			setCurrentDeviceName(deviceTable.getSelectionModel().getSelectedItem().getDeviceName());
    		root = FXMLLoader.load(getClass().getResource("smokeAlarmMenu.fxml"));
        	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        	scene = new Scene(root);
        	stage.setScene(scene);
        	stage.show();
    		break;
    	case "SMART_THERMOSTAT":
			setCurrentDeviceName(deviceTable.getSelectionModel().getSelectedItem().getDeviceName());
    		root = FXMLLoader.load(getClass().getResource("thermostatMenu.fxml"));
        	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        	scene = new Scene(root);
        	stage.setScene(scene);
        	stage.show();
    		break;
    	}
    }



	//observable list variable
    public ObservableList<Device> displayDevices = FXCollections.observableArrayList();

	// Sets the list of devices to be displayed
	public void setDisplayDevices(ArrayList<Device> devices) {
		System.out.println("setDisplayDevices Called");

		//sets observable list to the data
		this.displayDevices.addAll(devices);


		System.out.println("device 1 name:" + displayDevices.get(0).getDeviceName());
	}

	//Handles logout attempt
	@FXML
	void attemptLogout(MouseEvent event) throws IOException {
		client.requestLogout();

		Parent root = FXMLLoader.load(getClass().getResource("loginMenu.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	// Initializes the controller
    public void initialize() {
    	//sets up client
    	client.setController(this);

		//request device list from server
		client.requestConnectedDevices();


		//displays the proper username
		welcomeText.setText("Welcome Home, " + getUsername());

		//sets up table display
    	iconColumn.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceType"));
    	nameColumn.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceName"));
    	statusColumn.setCellValueFactory(new PropertyValueFactory<Device, String>("status"));
		delColumn.setCellValueFactory(new PropertyValueFactory<Device, Button>("newButton"));

		//displays table
    	deviceTable.setItems(displayDevices);

    }
    
}
