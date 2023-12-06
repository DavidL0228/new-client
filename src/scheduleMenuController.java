/*
 * Author: David Loovere
 * Course: ESOF 3050
 * Description: Controller class for the schedule menu screen in the Smart Home application.
 */

import javafx.application.Platform;
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
    private TextField endHourField;

    @FXML
    private TextField endMinField;

    @FXML
    private TextField endSecField;

    @FXML
    private TextField endValue;

    @FXML
    private Text endValueText;

    @FXML
    private TextField startHourField;

    @FXML
    private TextField startMinField;

    @FXML
    private TextField startSecField;

    @FXML
    private TextField startValue;

    @FXML
    private Text startValueText;

    @FXML
    private Text title;

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

        try{
            //gets the values from the textFields, to be put into the start time
            int startHour = Integer.parseInt(startHourField.getText());
            int startMin = Integer.parseInt(startMinField.getText());
            int startSec = Integer.parseInt(startSecField.getText());

            //gets the values from the textFields, to be put into the end time
            int endHour = Integer.parseInt(endHourField.getText());
            int endMin = Integer.parseInt(endMinField.getText());
            int endSec = Integer.parseInt(endSecField.getText());

            //checks for negative values
            if (!(startHour < 0) && !(startMin < 0) && !(startSec < 0) && !(endHour < 0) && !(endMin < 0) && !(endSec < 0)) {
                //sets the time variable
                Time startTime = new Time(startHour, startMin, startSec);
                Time endTime = new Time(endHour, endMin, endSec);

                // Wait for the success status
                while (success < 0) {
                    System.out.println("looping");
                }

                // Check the success status and navigate to the corresponding menu
                if (success == 1) {
                    switch (deviceType) {
                        case Message.SMART_LIGHT:
                            client.addLightSchedule(getCurrentDeviceName(), 100, startTime, endTime);
                            break;
                        case Message.SMART_FAN:
                            //client.sendFanSchedule(getCurrentDeviceName(), 100, startTime, endTime);
                            break;
                        case Message.SMART_THERMOSTAT:
                            //client.adjustTemperatureWithSchedule(getCurrentDeviceName(), 0, startTime, endTime);
                            break;
                        default:
                            System.out.println("device type not found");
                            break;
                    }

                }

                success = -1;
            }
        } catch(NumberFormatException ex){
            //catches incorrect inputs
            System.out.println("Incorrect Format");
        }
        //refreshes client
        client.requestSchedule(getCurrentDeviceName());
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
    public void displaySchedule(int startHour, int startMinute, int startSecond, int endHour, int endMinute, int endSecond){

        Platform.runLater(() -> {
            System.out.println("start time: " + startHour + ":" + startMinute);
            //sets start time display
            startHourField.setText(String.valueOf(startHour));
            startMinField.setText(String.valueOf(startMinute));
            startSecField.setText(String.valueOf(startSecond));

            //sets end time display
            endHourField.setText(String.valueOf(endHour));
            endMinField.setText(String.valueOf(endMinute));
            endSecField.setText(String.valueOf(endSecond));
        });
    }

    public void initialize(){
        client.setController(this);
        title.setText("Schedule For " + getCurrentDeviceName());
        client.requestSchedule(getCurrentDeviceName());
    }

}
