import java.io.IOException;

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

public class lockMenuController {

    @FXML
    private Button backButtonController;

    @FXML
    private TextField hoursField;

    @FXML
    private Button lockButton;

    @FXML
    private Text lockName;

    @FXML
    private TextField minField;

    @FXML
    private TextField secField;

    @FXML
    private Button updateAutoLockButton;

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

    }

    @FXML
    void updateAutoLock(MouseEvent event) {

    }

}