import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class lockMenuController extends SmartHomeController{

    @FXML
    private Button backButtonController;

    @FXML
    private TextField hoursField;

    @FXML
    private Button lockButton;

    @FXML
    private Text lockName;

    @FXML
    private ImageView lockImage;

    @FXML
    private TextField minField;

    @FXML
    private TextField secField;

    @FXML
    private Text statusText;

    @FXML
    private Button updateAutoLockButton;

    @FXML
    private Button addUserButton;

    @FXML
    void addUser(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("addDeviceUserMenu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void goBackToMain(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("homeScreenMenu.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	Scene scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }

    @FXML
    void toggleLock(MouseEvent event) {
        if (statusText.getText().equals("Locked")) {
            System.out.println("unLocking...");
            client.unlock(getCurrentDeviceName());
        }
        if (statusText.getText().equals("Unlocked")) {
            System.out.println("locking...");
            client.lock(getCurrentDeviceName());
        }

        //updates the info and display
        client.requestLockStatus(getCurrentDeviceName());
    }

    @FXML
    void updateAutoLock(MouseEvent event) {
        int seconds = (Integer.parseInt(hoursField.getText()) * 60 * 60) + (Integer.parseInt(minField.getText()) * 60) + Integer.parseInt(secField.getText());
        client.setLockAfterTime(getCurrentDeviceName(), seconds);
        client.requestLockStatus(getCurrentDeviceName());
    }

    public void displayLockStatus(String _status, int _seconds, int _min, int _hours){
        Platform.runLater(()-> {
            System.out.println("Current Lock Status: " + _status);
            statusText.setText(_status);
            if(_status.equals("Locked")){
                lockImage.setImage(new Image("icons/lock.png"));
            } else {
                lockImage.setImage(new Image("icons/unlock.png"));
            }

            secField.setText(String.valueOf(_seconds));
            minField.setText(String.valueOf(_min));
            hoursField.setText(String.valueOf(_hours));
        });
    }

    public void initialize() {
        lockName.setText(getCurrentDeviceName());
        client.setController(this);
        client.requestLockStatus(getCurrentDeviceName());
    }

}