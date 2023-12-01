import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SmartHomeController {

	protected static SmartClient client;

	//when user pick which they device they want to interact with, value is stored here to send to server
	private static String currentDeviceName;

	//username variable used to display the correct name on home screen
	private static String username;

	public static String getCurrentDeviceName() {
		return currentDeviceName;
	}

	public static void setCurrentDeviceName(String currentDeviceName) {
		SmartHomeController.currentDeviceName = currentDeviceName;
	}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		SmartHomeController.username = username;
	}
}
