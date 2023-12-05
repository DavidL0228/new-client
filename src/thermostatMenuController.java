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
import java.io.IOException;

public class thermostatMenuController extends SmartHomeController{

    @FXML
    private Button addScheduleButton;

    @FXML
    private Text airCircText;

    @FXML
    private Button backButtonController;

    @FXML
    private Button decreaseTemp;

    @FXML
    private Button increaseTemp;

    @FXML
    private Text statusText;

    @FXML
    private Text tempModeText;

    @FXML
    private Text temperatureText;

    @FXML
    private Button thermostatButton;

    @FXML
    private Text thermostatName;

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
    void toggleSmokeAlarm(MouseEvent event) {

    }


    public void displayThermostatStatus(){

    }

    public void initialize() {
        thermostatName.setText(getCurrentDeviceName());
        client.setController(this);
        client.requestLightStatus(getCurrentDeviceName());
    }
}
