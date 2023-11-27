import java.io.IOException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientMain {//extends Application {

	/*public void start( Stage stage ) throws Exception {
		Parent root = FXMLLoader.load( getClass().getResource("MessageInterface.fxml") );
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle( "Instant Messenger" );
		stage.show();
	}
	*/
	public static void main(String args) {
		SmartClient client = new SmartClient("196.168.122.25", 6430);
		SmartHomeController controller = new SmartHomeController();
		client.setController(controller);
		try {
			client.openConnection();
		} 
		catch (IOException e) {	e.printStackTrace();	}
		client.sendLoginInfo( "Said", "123" );
		client.requestNetworkDevices();
		while(true);
	}

}
