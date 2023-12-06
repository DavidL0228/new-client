import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class thermostatMenuController extends SmartHomeController{

    @FXML
    private MenuItem CoolingButton;

    @FXML
    private Button addScheduleButton;

    @FXML
    private MenuButton airCircMode;

    @FXML
    private Button backButtonController;

    @FXML
    private TextField currentTemp;

    @FXML
    private Button decreaseTemp;

    @FXML
    private MenuItem heatingButton;

    @FXML
    private Button increaseTemp;

    @FXML
    private TextField maxTemp;

    @FXML
    private TextField minTemp;

    @FXML
    private MenuItem noButton;

    @FXML
    private Text statusText;

    @FXML
    private MenuButton tempMode;

    @FXML
    private Button thermostatButton;

    @FXML
    private Text thermostatName;

    @FXML
    private Button updateButton;

    @FXML
    private MenuItem yesButton;

    @FXML
    void decreaseTemp(MouseEvent event) {
        client.modifyTemperatureNow(getCurrentDeviceName(), Integer.parseInt(currentTemp.getText())-1);
        client.requestThermostatStatus(getCurrentDeviceName());
    }

    @FXML
    void increaseTemp(MouseEvent event) {
        client.modifyTemperatureNow(getCurrentDeviceName(), Integer.parseInt(currentTemp.getText())+1);
        client.requestThermostatStatus(getCurrentDeviceName());
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
    void gotoAddSchedule(MouseEvent event) {

    }


    @FXML
    void setCooling(ActionEvent event) {

    }

    @FXML
    void setHeating(ActionEvent event) {

    }

    @FXML
    void setNo(ActionEvent event) {
        client.turnOffCirculation(getCurrentDeviceName());
        client.requestThermostatStatus(getCurrentDeviceName());
    }

    @FXML
    void setYes(ActionEvent event) {
        client.turnOnCirculation(getCurrentDeviceName());
        client.requestThermostatStatus(getCurrentDeviceName());
    }

    @FXML
    void toggleThermostat(MouseEvent event) {
        if (statusText.getText().equals("ON")) {
            System.out.println("Turning Off");
            client.turnOffThermostat(getCurrentDeviceName());
        }
        if (statusText.getText().equals("OFF")) {
            System.out.println("Turning on ");
            client.turnOnThermostat(getCurrentDeviceName());
        }

        //updates the info and display
        client.requestThermostatStatus(getCurrentDeviceName());
    }

    @FXML
    void updateTemps(MouseEvent event) {
        client.maintainTemperatureRange(getCurrentDeviceName(), Integer.parseInt(minTemp.getText()), Integer.parseInt(maxTemp.getText()));
        client.modifyTemperatureNow(getCurrentDeviceName(), Integer.parseInt(currentTemp.getText()));
        client.requestThermostatStatus(getCurrentDeviceName());
    }


    public void displayThermostatStatus(String _status, int _min, int _max, int _cur, String _isCirc, String _mode){
        statusText.setText(_status);

        minTemp.setText(String.valueOf(_min));
        maxTemp.setText(String.valueOf(_max));

        currentTemp.setText(String.valueOf(_cur));
        thermostatButton.setText(String.valueOf(_cur) + "°");

        airCircMode.setText(_isCirc);
        tempMode.setText(_mode);

    }

    public void initialize(){
        thermostatName.setText(getCurrentDeviceName());
        client.setController(this);
        client.requestThermostatStatus(getCurrentDeviceName());
    }

}
