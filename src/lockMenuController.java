/*
 * Author: David Loovere
 * Course: ESOF 3050
 * Description: Controller class for the lock menu in the Smart Home application.
 */

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

import java.io.IOException;

public class lockMenuController extends SmartHomeController {

    // FXML elements
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

    // Navigates to the add user screen
    @FXML
    void addUser(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("addDeviceUserMenu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Navigates back to the main screen
    @FXML
    void goBackToMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("homeScreenMenu.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Toggles the lock status
    @FXML
    void toggleLock(MouseEvent event) {
        if (statusText.getText().equals("Locked")) {
            System.out.println("Unlocking...");
            client.unlock(getCurrentDeviceName());
        }
        if (statusText.getText().equals("Unlocked")) {
            System.out.println("Locking...");
            client.lock(getCurrentDeviceName());
        }

        // Updates the info and display
        client.requestLockStatus(getCurrentDeviceName());
    }

    // Updates the auto-lock time
    @FXML
    void updateAutoLock(MouseEvent event) {
        int seconds = (Integer.parseInt(hoursField.getText()) * 60 * 60) +
                (Integer.parseInt(minField.getText()) * 60) +
                Integer.parseInt(secField.getText());
        client.setLockAfterTime(getCurrentDeviceName(), seconds);
        client.requestLockStatus(getCurrentDeviceName());
    }

    // Updates all the display elements of the UI to the data received from the server
    public void displayLockStatus(String _status, int _seconds, int _min, int _hours) {
        Platform.runLater(() -> {
            System.out.println("Current Lock Status: " + _status);
            statusText.setText(_status);
            if (_status.equals("Locked")) {
                lockImage.setImage(new Image("icons/lock.png"));
            } else {
                lockImage.setImage(new Image("icons/unlock.png"));
            }

            secField.setText(String.valueOf(_seconds));
            minField.setText(String.valueOf(_min));
            hoursField.setText(String.valueOf(_hours));
        });
    }

    // Called when the screen is first shown
    public void initialize() {
        lockName.setText(getCurrentDeviceName());
        client.setController(this);
        client.requestLockStatus(getCurrentDeviceName());
    }
}
