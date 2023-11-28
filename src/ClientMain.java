import java.io.IOException;

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
		//client.sendLoginInfo( "Said", "123", event);
		//client.requestNetworkDevices();
		while(true);
	}

}
