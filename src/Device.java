import javafx.scene.control.Button;
//class is needed for the table element on main screen.
public class Device {

	private String deviceType;
	private String deviceName;
	private String status;

	private Button newButton;
	
	public Device() {
		this.setDeviceType("light");
		this.setDeviceName("default name");
		this.setStatus("Off");
		this.newButton = new Button("-");
	}
	
	public Device(String _deviceType, String _deviceName, String _status) {
		this.setDeviceType(_deviceType);
		this.setDeviceName(_deviceName);
		this.setStatus(_status);
		this.newButton = new Button("-");
	}



	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public Button getNewButton() {
		return newButton;
	}

	public void setNewButton(Button newButton) {
		this.newButton = newButton;
	}
}
