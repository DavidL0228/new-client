/*
 * Author: David Loovere
 * Course: ESOF 3050
 * Description:  This class represents a device in the smart home application
 */

import javafx.scene.control.Button;

// Represents a device in the smart home application
public class Device {

	// Device properties
	private String deviceType;
	private String deviceName;
	private String status;
	private Button newButton;

	// Default constructor initializes device with default values
	public Device() {
		// Set default values
		this.setDeviceType("light");
		this.setDeviceName("default name");
		this.setStatus("Off");

		// Initialize and configure the button
		this.newButton = new Button("-");
		newButton.setStyle("-fx-background-color: red");
		newButton.setOnMouseClicked(event -> {
			System.out.println(deviceName + " button pressed");
		});
	}

	// Constructor with parameters for initializing device properties
	public Device(String _deviceType, String _deviceName, String _status) {
		// Set values based on parameters
		this.setDeviceType(_deviceType);
		this.setDeviceName(_deviceName);
		this.setStatus(_status);

		// Initialize the button
		this.newButton = new Button("-");
	}

	// Getter for deviceName
	public String getDeviceName() {
		return deviceName;
	}

	// Setter for deviceName
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	// Getter for status
	public String getStatus() {
		return status;
	}

	// Setter for status
	public void setStatus(String status) {
		this.status = status;
	}

	// Getter for deviceType
	public String getDeviceType() {
		return deviceType;
	}

	// Setter for deviceType
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	// Getter for newButton
	public Button getNewButton() {
		return newButton;
	}

	// Setter for newButton
	public void setNewButton(Button newButton) {
		this.newButton = newButton;
	}
}
