import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class addUserController extends SmartHomeController {


    @FXML
    private Button addUserButton;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button gotoLoginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    void addUser(MouseEvent event) throws IOException {
        //put code to login here

        //goes to main if succesful
        Parent root = FXMLLoader.load(getClass().getResource("homeScreenMenu.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void gotoLogin(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("loginMenu.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
