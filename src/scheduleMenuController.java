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

public class scheduleMenuController {

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
    private Text lightName;

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

    // Navigate back to the device screen
    @FXML
    void goBackToDevice(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("lightMenu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Method to handle updating the schedule
    @FXML
    void updateSchedule(MouseEvent event) {
        // Add logic to update the schedule based on the user input
        // This method will be completed with the specific functionality for updating schedules
    }

}
