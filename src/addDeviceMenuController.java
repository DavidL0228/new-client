/*
 * Author: David Loovere
 * Course: ESOF 3050
 * Description: Controller class for adding a new device in the Smart Home application.
 */

import java.io.IOException;

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
import javafx.stage.Stage;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;

public class addDeviceMenuController extends SmartHomeController {

    // FXML elements
    @FXML
    private MenuButton deviceTypeMenu;

    @FXML
    private Button addDeviceButton;

    @FXML
    private MenuItem alarmButton;

    @FXML
    private Text errorText;

    @FXML
    private Button backButtonController;

    @FXML
    private TextField deviceNameText;

    @FXML
    private MenuItem fanButton;

    @FXML
    private MenuItem lightButton;

    @FXML
    private MenuItem lockButton;

    @FXML
    private MenuItem thermostatButton;

    // Handles adding a new device
    @FXML
    void addDevice(MouseEvent event) {
        errorText.setVisible(false);

        // Check if a device type is selected
        if (deviceSelection == null) {
            errorText.setVisible(true);
            errorText.setText("ERROR: No Device Type Selected");
        } else {
            // Check if a device name is provided
            if (deviceNameText.getText().isEmpty()) {
                errorText.setVisible(true);
                errorText.setText("ERROR: No Device Name Given");
            } else {
                // Add the new device using the provided name and type
                client.addNewDevice(deviceNameText.getText(), deviceSelection);
            }
        }
    }

    private String deviceSelection;

    // Handles going back to the main screen
    @FXML
    void goBackToMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("homeScreenMenu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Methods to handle selecting different device types
    @FXML
    void selectAlarm(ActionEvent event) {
        deviceSelection = "smokeAlarm";
        deviceTypeMenu.setText("Smoke Alarm");
    }

    @FXML
    void selectFan(ActionEvent event) {
        deviceSelection = "fan";
        deviceTypeMenu.setText("Fan");
    }

    @FXML
    void selectLight(ActionEvent event) {
        deviceSelection = Message.SMART_LIGHT;
        deviceTypeMenu.setText("Light");
    }

    @FXML
    void selectLock(ActionEvent event) {
        deviceSelection = "lock";
        deviceTypeMenu.setText("Lock");
    }

    @FXML
    void selectThermostat(ActionEvent event) {
        deviceSelection = "thermostat";
        deviceTypeMenu.setText("Thermostat");
    }
}
