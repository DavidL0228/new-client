/*
 * Author: David Loovere
 * Course: ESOF 3050
 * Description: Controller class for adding a user to a device in the Smart Home application.
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class addDeviceUserMenuController extends SmartHomeController {

    // FXML elements
    @FXML
    private Button addUserButton;

    @FXML
    private Button backButtonController;

    @FXML
    private Text deviceName;

    @FXML
    private Text errorText;

    @FXML
    private TextField usernameText;

    // Represents the success status of the user addition process
    private int success = -1;

    // Represents the type of the current device
    private String deviceType ="";

    // Handles adding a user to a device
    @FXML
    void addUser(MouseEvent event) throws IOException{
        errorText.setVisible(false);

        // Check if a username is entered
        if(usernameText.getText().isEmpty()){
            errorText.setVisible(true);
            errorText.setText("ERROR: Enter A Username");
        } else {
            // Add user to the current device and navigate back to the home screen
            client.addUserToDevice(getCurrentDeviceName(), usernameText.getText());

            Parent root = FXMLLoader.load(getClass().getResource("homeScreenMenu.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    // Handles going back to the main screen after adding a user
    @FXML
    void goBackToMain(ActionEvent event) throws IOException {
        client.getDeviceType(getCurrentDeviceName());

        // Wait for the success status
        while(success < 0){
            System.out.println("looping");
        }

        // Check the success status and navigate to the corresponding menu
        if(success == 1){
            Parent root;
            Stage stage;
            Scene scene;

            switch(deviceType){
                case Message.SMART_LIGHT:
                    root = FXMLLoader.load(getClass().getResource("lightMenu.fxml"));
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    break;
                case Message.SMART_FAN:
                    root = FXMLLoader.load(getClass().getResource("fanMenu.fxml"));
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    break;
                case Message.SMART_LOCK:
                    root = FXMLLoader.load(getClass().getResource("lockMenu.fxml"));
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    break;
                case Message.SMART_THERMOSTAT:
                    root = FXMLLoader.load(getClass().getResource("thermostatMenu.fxml"));
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    break;
                case Message.SMART_SMOKE_ALARM:
                    root = FXMLLoader.load(getClass().getResource("smokeAlarmMenu.fxml"));
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    break;
                default:
                    //do nothing
                    break;
            }

        } else {
            //do nothing
        }
    }

    // Getter for success
    public int getSuccess() {
        return success;
    }

    // Setter for success
    public void setSuccess(int success) {
        this.success = success;
    }

    // Getter for deviceType
    public String getDeviceType() {
        return deviceType;
    }

    // Setter for deviceType
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    // Initializes the controller
    public void initialize(){
        client.setController(this);
        deviceName.setText("Add User to " + getCurrentDeviceName());
    }
}
