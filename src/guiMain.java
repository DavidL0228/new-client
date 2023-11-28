import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class guiMain extends Application {
	public static void main(String[] args) {
		// create an object and call its start method
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("loginMenu.fxml"));

		Scene scene = new Scene(root); // attach scene graph to scene
		stage.setTitle("Smart Home GUI"); // displayed in window's title bar
		stage.setScene(scene); // attach scene to stage
		stage.show(); // display the stage	
	}

}