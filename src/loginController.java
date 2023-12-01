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


    private int success = -1;

    public void setSuccess(int _success){
        success = _success;
    }

    @FXML
    void attemptLogin(MouseEvent event) throws IOException {
    	//put login check stuff here
        String username = usernameField.getText();
        String password = passwordField.getText();

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        client.sendLoginInfo(username, password);


        while(success < 0){
            //loop
            System.out.println("looping %n ");
        }

        System.out.println("loop done ");

        loginSuccess(stage);
    }

    void loginSuccess(Stage stage) throws IOException {
        System.out.println("login success");

        //sets the local username to the correct one, for use
        setUsername(usernameField.getText());

        Parent root = FXMLLoader.load(getClass().getResource("homeScreenMenu.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

        System.out.println("test");
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

        client = new SmartClient("10.0.0.184",9100);
        client.setController(this);
        try {
            client.openConnection();
        } catch (IOException e) {
           throw new RuntimeException(e);
        }
    }

}
