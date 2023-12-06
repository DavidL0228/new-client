/*
 * Author: David Loovere
 * Course: ESOF 3050
 * Description: Controller class for the schedule menu screen in the Smart Home application.
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Time;

public class scheduleMenuController extends SmartHomeController{

    // FXML elements
    @FXML
    private Button backButtonController;

    @FXML
    private MenuButton endActionMenu;

    @FXML
    private MenuButton endAmOrPmMenu;

    @FXML
    private TextField endHourField;

    @FXML
    private TextField endMinField;

    @FXML
    private Text title;

    @FXML
    private MenuButton startActionMenu;

    @FXML
    private MenuButton startAmOrPmMenu;

    @FXML
    private TextField startHourField;

    @FXML
    private TextField startMinField;

    @FXML
    private Button updateScheduleButton;

    // Represents the success status of the user addition process
    private int success = -1;

    // Represents the type of the current device
    private String deviceType ="";

    // Navigate back to the device screen
    @FXML
    void goBackToDevice(ActionEvent event) throws IOException {
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
                case Message.SMART_THERMOSTAT:
                    root = FXMLLoader.load(getClass().getResource("thermostatMenu.fxml"));
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

    // Method to handle updating the schedule
    @FXML
    void updateSchedule(MouseEvent event) {
        //gets the device type
        client.getDeviceType(getCurrentDeviceName());

        //gets the values from the textFields, to be put into the start time
        int startHour = Integer.parseInt(startHourField.getText());
        int startMin = Integer.parseInt(startMinField.getText());

        //gets the values from the textFields, to be put into the end time
        int endHour = Integer.parseInt(endHourField.getText());
        int endMin = Integer.parseInt(endMinField.getText());

        //im not sure how to set these up properly, if someone can figure that out that would be great
        Time startTime;
        Time endTime;


        // Wait for the success status
        while(success < 0){
            System.out.println("looping");
        }

        // Check the success status and navigate to the corresponding menu
        if(success == 1) {

            switch(deviceType){
                case Message.SMART_LIGHT:
                    //client.addLightSchedule(getCurrentDeviceName(), 0, startTime, endTime);
                    break;
                case Message.SMART_FAN:
                    //client.sendFanSchedule(getCurrentDeviceName(), 100, startTime, endTime);
                    break;
                case Message.SMART_THERMOSTAT:
                    //client.adjustTemperatureWithSchedule(getCurrentDeviceName(), 0, startTime, endTime);
                    break;
                default:
                    //do nothing
                    break;
            }

        }

        success = -1;
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

    //displays the schedule received from the server
    public void displaySchedule(){
        //needs to receive a start time, and an end time and parse into a min and hour field.

        //set these to correct value
        startHourField.setText("");
        startMinField.setText("");

        endHourField.setText("");
        endMinField.setText("");

    }

    public void initialize(){
        client.setController(this);
        title.setText("Schedule For " + getCurrentDeviceName());
        //client.requestSchedule(getCurrentDevice());
    }

}
