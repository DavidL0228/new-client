import javafx.collections.ObservableList;

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

	//private static ObservableList<Device> devices;

	//public static ObservableList<Device> getDevices() {
	//	return devices;
	//}

	//public void addDevices(String _icon, String _deviceType, String _deviceName, String _status) {
	//	devices.add(new Device(_icon, _deviceType, _deviceName, _status));
	//}
	
	
	
}
