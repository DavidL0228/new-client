import java.util.ArrayList;

public class MessageWithDevices extends Message {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<SmartDevice> networkDevices;
	
	MessageWithDevices(
			String username, 
			String password, 
			String deviceType, 
			int deviceID, 
			String whichFunction,
			int firstData,
			ArrayList<SmartDevice> networkDevices) 
	{
		super(username, password, deviceType, deviceID, whichFunction, firstData);
		this.networkDevices = networkDevices;
	}

	public ArrayList<SmartDevice> getDevices() {
		return networkDevices;
	}
	
	
}
