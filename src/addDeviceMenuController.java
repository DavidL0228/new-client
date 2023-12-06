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

    @FXML
    void addDevice(MouseEvent event) {
        errorText.setVisible(false);
        if(deviceSelection == null){
            errorText.setVisible(true);
            errorText.setText("ERROR: No Device Type Selected");
        } else {
            if(deviceNameText.getText().isEmpty()){
                errorText.setVisible(true);
                errorText.setText("ERROR: No Device Name Given");
            } else {
               client.addNewDevice(deviceNameText.getText(), deviceSelection);
           }
        }

    }

    private String deviceSelection;

    @FXML
    void goBackToMain(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("homeScreenMenu.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	Scene scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }

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
