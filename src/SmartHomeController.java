import javafx.collections.ObservableList;

public class SmartHomeController {

	protected static SmartClient client;

	private static String currentDeviceName;

	public static String getCurrentDeviceName() {
		return currentDeviceName;
	}

	public static void setCurrentDeviceName(String currentDeviceName) {
		SmartHomeController.currentDeviceName = currentDeviceName;
	}

	//private static ObservableList<Device> devices;

	//public static ObservableList<Device> getDevices() {
	//	return devices;
	//}

	//public void addDevices(String _icon, String _deviceType, String _deviceName, String _status) {
	//	devices.add(new Device(_icon, _deviceType, _deviceName, _status));
	//}
	
	
	
}
