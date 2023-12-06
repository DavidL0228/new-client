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

    @FXML
    void addUser(MouseEvent event) throws IOException{
        errorText.setVisible(false);

        if(usernameText.getText().isEmpty()){
            errorText.setVisible(true);
            errorText.setText("ERROR: Enter A Username");
        } else {
            client.addUserToDevice(getCurrentDeviceName(), usernameText.getText());

            Parent root = FXMLLoader.load(getClass().getResource("homeScreenMenu.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }

    private int success = -1;

    private String deviceType ="";

    @FXML
    void goBackToMain(ActionEvent event) throws IOException {
        client.getDeviceType(getCurrentDeviceName());

        while(success < 0){
            System.out.println("looping");
        }

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


        Parent root = FXMLLoader.load(getClass().getResource("lightMenu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public void initialize(){
        client.setController(this);
        deviceName.setText("Add User to " + getCurrentDeviceName());
    }
}
