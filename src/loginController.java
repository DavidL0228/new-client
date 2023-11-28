import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class loginController extends SmartHomeController {

    @FXML
    private Button addUserButton;

    @FXML
    private Button loginButton;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    void attemptLogin(MouseEvent event) throws IOException {
    	//put login check stuff here
        String username = usernameField.getText();
        String password = passwordField.getText();

        client.sendLoginInfo(username, password);
        client.sendRegisterInfo(username, password);



        //goes to main if successful
        Parent root = FXMLLoader.load(getClass().getResource("homeScreenMenu.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	Scene scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }

    @FXML
    void gotoAddUser(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("addUserMenu.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {
        client = new SmartClient("192.168.89.137",9100);
        client.setController(this);
       // try {
       //     client.openConnection();
        //} catch (IOException e) {
        //    throw new RuntimeException(e);
        //}
    }


}
