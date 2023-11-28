import java.util.ArrayList;


public class MessageWithDevices extends Message {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<String> deviceNames;
	private ArrayList<String> deviceTypes;
	private ArrayList<String> deviceStatuses;


	MessageWithDevices(
			String username,
			String password,
			String deviceType,
			String deviceName,
			String whichFunction,
			int firstData,
			ArrayList<String> deviceNames,
			ArrayList<String> deviceTypes,
			ArrayList<String> deviceStatuses)
	{
		super(username, password, deviceType, deviceName, whichFunction, firstData);
		this.deviceNames = deviceNames;
		this.deviceTypes = deviceTypes;
		this.deviceStatuses = deviceStatuses;
	}

	public ArrayList<String> getDeviceNames() {
		return deviceNames;
	}
	public ArrayList<String> getDeviceTypes() {
		return deviceTypes;
	}
	public ArrayList<String> getDeviceStatuses() {
		return deviceStatuses;
	}
}
