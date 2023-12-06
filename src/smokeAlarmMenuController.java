import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class smokeAlarmMenuController extends SmartHomeController {

    @FXML
    private Text alarmName;

    @FXML
    private Button backButtonController;

    @FXML
    private Text batteryPercent;

    @FXML
    private Button smokeAlarmButton;

    @FXML
    private Text smokeAmount;

    @FXML
    private Text smokeThresholdExceeded;

    @FXML
    private Text statusText;

    @FXML
    void goBackToMain(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("homeScreenMenu.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	Scene scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }

    @FXML
    void toggleSmokeAlarm(MouseEvent event) {

    }

    public void displaySmokeAlarmStatus(String _status, int _smokeAmount, String _smokeThresholdExceeded, int _batteryPercent){
        statusText.setText(_status);
        smokeAmount.setText(String.valueOf(_smokeAmount));
        smokeThresholdExceeded.setText(_smokeThresholdExceeded);
        batteryPercent.setText(String.valueOf(_batteryPercent));
    }

    public void initialize() {
        alarmName.setText(getCurrentDeviceName());
        client.setController(this);
        client.requestSmokeAlarmStatus(getCurrentDeviceName());
    }

}
