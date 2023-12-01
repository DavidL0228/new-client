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
import javafx.scene.text.Text;

import java.io.IOException;

public class addUserController extends SmartHomeController{

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
    private Text errorText;

    private int success = -1;


    @FXML
    void addUser(MouseEvent event) throws IOException {
        errorText.setVisible(false);
        //put code to login here
        if(passwordField.getText() != confirmPasswordField.getText()){
            errorText.setVisible(true);
            errorText.setText("ERROR: passwords do not match");
        } else {
            client.sendRegisterInfo(usernameField.getText(), passwordField.getText());

            while(success < 0){

            }

        }


        //goes to main if successful
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


    public void initialize() {
        client.setController(this);

    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}
