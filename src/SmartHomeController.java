import javafx.collections.ObservableList;

public class SmartHomeController {

	SmartClient client;
	
	private static ObservableList<Device> devices;

	public static ObservableList<Device> getDevices() {
		return devices;
	}

	//public void addDevices(String _icon, String _deviceType, String _deviceName, String _status) {
	//	devices.add(new Device(_icon, _deviceType, _deviceName, _status));
	//}
	
	
	
}
