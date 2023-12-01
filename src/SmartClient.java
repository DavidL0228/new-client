import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;

public class SmartClient extends AbstractClient {

	String username;
	String password;
	SmartHomeController controller;
	boolean isAuthenticated = false;

	public SmartClient(String host, int port) {
		super(host, port);
		// TODO Auto-generated constructor stub
	}

	public void setController(SmartHomeController controller) {
		this.controller = controller;
	}



	@Override
	protected void handleMessageFromServer(Object msg) {
		// TODO Auto-generated method stub
		if( msg instanceof MessageWithDevices ) {
			MessageWithDevices message = (MessageWithDevices)msg;
			// display devices on network
			ArrayList<Device> devices = new ArrayList<Device>();
			int index = 0;
			for( String i : message.getDeviceNames() ) {
				Device d = new Device();
				d.setDeviceName(i);
				d.setDeviceType(message.getDeviceTypes().get(index) );
				d.setStatus(message.getDeviceStatuses().get(index));
				devices.add(d);
			}
			String function = message.getWhichFunction();
			if(function.equals(Message.FIND_NETWORK_DEVICES)) {
				( (homeScreenController) controller ).displayAllDevices( devices );
			}
			else if (function.equals(Message.REQUEST_CONNECTED_DEVICES)) {
				//controller.displayConnectedDevices( devices );
			}

		}
		else {
			Message message = ( Message )msg;
			String function = message.getWhichFunction();
			if( function.equals(Message.REQUEST_FAN_STATUS) ) {
				String isFanOn = message.getFirstData() == 1 ? "YES": "NO";
				int fanSpeed = message.getSecondData();
				int fanTemp = message.getThirdData();
				Schedule schedule = ((MessageWithSchedule)message).getSchedule();
				//controller.displayFanStatus( message.getFirstData() );
			}
			else if( function.equals(Message.REQUEST_LIGHT_STATUS) ) {
				String isLightOn = message.getFirstData() == 1 ? "YES": "NO";
				int intensity = message.getSecondData();
				int timeout = message.getThirdData();
				int timeoutMins = timeout / 60;
				int timeoutHours = timeoutMins / 60;
				int timeoutSeconds = timeout - (timeoutMins * 60) - (timeoutHours * 3600);
				Schedule schedule = ((MessageWithSchedule)message).getSchedule();
				//controller.displayLightStatus( message.getDeviceName(), isLightOn, intensity, timeoutSeconds, timeoutMins, timeoutHours );
			}
			else if( function.equals(Message.REQUEST_LOCK_STATUS) ) {
				String isLocked = message.getFirstData() == 1 ? "ON": "OFF";
				int duration = message.getSecondData();
				//controller.displayLockStatus( message.getFirstData() );
			}
			else if( function.equals(Message.REQUEST_SMOKE_ALARM_STATUS) ) {
				int percentage = message.getFirstData();
				String isSoundFunctional = message.getSecondData() == 1 ? "YES": "NO";
				String isDetectorFunctional = message.getThirdData() == 1 ? "YES": "NO";
				int smokeAmount = message.getFourthData();
				String isSmokeTooMuch = message.getFifthData() == 1 ? "YES": "NO";

				//controller.displayLockStatus( message.getFirstData() );
			}
			else if( function.equals(Message.REQUEST_THERMOSTAT_STATUS) ) {
				String isThermoOn = message.getFirstData() == 1 ? "YES": "NO";
				int min = message.getSecondData();
				int max = message.getThirdData();
				int now = message.getFourthData();
				String isCirc = message.getFifthData() == 1 ? "YES": "NO";
				String mode = message.getSixthData() == 1 ? "Heating": "Cooling";
				Schedule schedule = ((MessageWithSchedule)message).getSchedule();
				//controller.displayThermostatStatus( message.getFirstData() );
			}
			else if ( function.equals("") ) {
				System.out.printf( "Success! %n" );
				if(controller instanceof loginController) {
					try {( (loginController)controller ).loginSuccess(event);}
					catch(IOException error) {}
				}
			}

		}
	}
	private MouseEvent event;
	public void sendLoginInfo(String username, String password, MouseEvent event) {
		this.username = username;
		this.password = password;
		Message msg = new Message(username,
				password,
				"",
				"",
				Message.SEND_LOGIN_INFO,
				-1);
		try 				  {	super.sendToServer(msg); }
		catch (IOException e) {	e.printStackTrace();	 }
		this.event = event;
		isAuthenticated = true;
	}

	public void requestThermostatStatus( String deviceName ) {
		if( !isAuthenticated ) {return;}
		Message msg = new Message(this.username,
				this.password,
				Message.SMART_THERMOSTAT,
				deviceName,
				Message.REQUEST_THERMOSTAT_STATUS,
				-1);
		try 				  {	super.sendToServer(msg); }
		catch (IOException e) {	e.printStackTrace();	 }
	}

	public void requestSmokeAlarmStatus( String deviceName ) {
		if( !isAuthenticated ) {return;}
		Message msg = new Message(this.username,
				this.password,
				Message.SMART_SMOKE_ALARM,
				deviceName,
				Message.REQUEST_SMOKE_ALARM_STATUS,
				-1);
		try 				  {	super.sendToServer(msg); }
		catch (IOException e) {	e.printStackTrace();	 }
	}

	public void requestFanStatus( String deviceName ) {
		Message msg = new Message(this.username,
				this.password,
				Message.SMART_FAN,
				deviceName,
				Message.REQUEST_FAN_STATUS,
				-1);
		try 				  {	super.sendToServer(msg); }
		catch (IOException e) {	e.printStackTrace();	 }
	}

	public void requestLightStatus( String deviceName ) {
		Message msg = new Message(this.username,
				this.password,
				Message.SMART_LIGHT,
				deviceName,
				Message.REQUEST_LIGHT_STATUS,
				-1);
		try 				  {	super.sendToServer(msg); }
		catch (IOException e) {	e.printStackTrace();	 }
	}

	public void requestLockStatus( String deviceName) {
		Message msg = new Message(this.username,
				this.password,
				Message.SMART_LOCK,
				deviceName,
				Message.REQUEST_LOCK_STATUS,
				-1);
		try 				  {	super.sendToServer(msg); }
		catch (IOException e) {	e.printStackTrace();	 }
	}

	public void sendFanSchedule(String deviceName, int fanSpeed, Schedule schedule) {
		MessageWithSchedule msg = new MessageWithSchedule(this.username,
				this.password,
				Message.SMART_FAN,
				deviceName,
				Message.SEND_FAN_SCHEDULE,
				fanSpeed, -1, -1,
				schedule);
		try					  {	super.sendToServer(msg);	}
		catch (IOException e) {	e.printStackTrace();		}
	}

	public void sendLightByMotionTime(String deviceName, int seconds, int intensity) {
		Message msg = new Message(this.username,
				this.password,
				Message.SMART_FAN,
				deviceName,
				Message.SEND_FAN_SCHEDULE,
				seconds, intensity);
		try					  {	super.sendToServer(msg);	}
		catch (IOException e) {	e.printStackTrace();		}
	}

	public void sendRegisterInfo( String username, String password ) {
		this.username = username;
		this.password = password;

		Message msg = new Message(username,
				password,
				"",
				"",
				Message.SEND_REGISTER_INFO,
				-1);
		try 				  {	super.sendToServer(msg); }
		catch (IOException e) {	e.printStackTrace();	 }
		isAuthenticated = true;
	}

	public void requestLogout() {
		this.username = "";
		this.password = "";
		this.isAuthenticated = false;
		Message msg = new Message(username,
				password,
				"",
				"",
				Message.REQUEST_LOGOUT,
				-1);
		try 				  {	super.sendToServer(msg); }
		catch (IOException e) {	e.printStackTrace();	 }
	}

	public void setLockAfterTime( String deviceName, int timeToWait ) {

		Message msg = new Message(username,
				password,
				Message.SMART_LOCK,
				deviceName,
				Message.SET_LOCK_AFTER_TIME,
				timeToWait);
		try 				  {	super.sendToServer(msg); }
		catch (IOException e) {	e.printStackTrace();	 }
	}

	public void lock( String deviceName ) {

		Message msg = new Message(username,
				password,
				Message.SMART_LOCK,
				deviceName,
				Message.LOCK,
				-1);
		try 				  {	super.sendToServer(msg); }
		catch (IOException e) {	e.printStackTrace();	 }
	}

	public void unlock( String deviceName ) {

		Message msg = new Message(username,
				password,
				Message.SMART_LOCK,
				deviceName,
				Message.UNLOCK,
				-1);
		try 				  {	super.sendToServer(msg); }
		catch (IOException e) {	e.printStackTrace();	 }
	}

	public void addNewUser( String deviceName) {

		Message msg = new Message(username,
				password,
				"",
				deviceName,
				Message.ADD_NEW_USER,
				-1);
		try 				  {	super.sendToServer(msg); }
		catch (IOException e) {	e.printStackTrace();	 }
	}

	public void establishNewAdmin( String deviceName ) {

		Message msg = new Message(username,
				password,
				"",
				deviceName,
				Message.ESTABLISH_ADMIN,
				-1);
		try 				  {	super.sendToServer(msg); }
		catch (IOException e) {	e.printStackTrace();	 }
	}

	public void adjustTemperatureWithSchedule(String deviceName, int temperature, Schedule schedule) {
		MessageWithSchedule msg = new MessageWithSchedule(username,
				password,
				Message.SMART_THERMOSTAT,
				deviceName,
				Message.ADJUST_THERMO_TEMP_SCHEDULE,
				temperature, -1, -1,
				schedule);
		try 				  {	super.sendToServer(msg); }
		catch (IOException e) {	e.printStackTrace();	 }
	}

	public void modifyTemperatureNow( String deviceName, int temperature) {
		Message msg = new Message(username,
				password,
				Message.SMART_THERMOSTAT,
				deviceName,
				Message.MODIFY_THERMO_TEMP_NOW,
				temperature);
		try 				  {	super.sendToServer(msg); }
		catch (IOException e) {	e.printStackTrace();	 }
	}

	public void maintainTemperatureRange(String deviceName, int high, int low) {
		Message msg = new Message(username,
				password,
				Message.SMART_THERMOSTAT,
				deviceName,
				Message.MODIFY_THERMO_TEMP_NOW,
				high, low);
		try 				  {	super.sendToServer(msg); }
		catch (IOException e) {	e.printStackTrace();	 }
	}

	public void turnOnCirculation(String deviceName) {
		Message msg = new Message(username, password,
				Message.SMART_THERMOSTAT,
				deviceName,
				Message.TURN_ON_CIRCULATION,
				-1);
		try 				  {	super.sendToServer(msg); }
		catch (IOException e) {	e.printStackTrace();	 }
	}

	public void turnOffCirculation(String deviceName) {
		Message msg = new Message(username, password,
				Message.SMART_THERMOSTAT,
				deviceName,
				Message.TURN_OFF_CIRCULATION,
				-1);
		try 				  {	super.sendToServer(msg); }
		catch (IOException e) {	e.printStackTrace();	 }
	}

	public void turnOnThermostat(String deviceName) {
		Message msg = new Message(username, password,
				Message.SMART_THERMOSTAT,
				deviceName,
				Message.TURN_ON_CIRCULATION,
				-1);
		try 				  {	super.sendToServer(msg); }
		catch (IOException e) {	e.printStackTrace();	 }
	}

	public void turnOffThermostat(String deviceName) {
		Message msg = new Message(username, password,
				Message.SMART_THERMOSTAT,
				deviceName,
				Message.TURN_OFF_CIRCULATION,
				-1);
		try 				  {	super.sendToServer(msg); }
		catch (IOException e) {	e.printStackTrace();	 }
	}

	public void requestAlarmHistory(String deviceName) {
		Message msg = new Message(username, password,
				Message.SMART_SMOKE_ALARM,
				deviceName,
				Message.REQUEST_ALARM_HISTORY,
				-1);
		try 				  {	super.sendToServer(msg); }
		catch (IOException e) {	e.printStackTrace();	 }
	}

	public void turnOnLight(String deviceName) {
		Message msg = new Message(username, password,
				Message.SMART_LIGHT,
				deviceName,
				Message.TURN_ON_LIGHT,
				-1);
		try 				  {	super.sendToServer(msg); }
		catch (IOException e) {	e.printStackTrace();	 }
	}

	public void turnOffLight(String deviceName) {
		Message msg = new Message(username, password,
				Message.SMART_LIGHT,
				deviceName,
				Message.TURN_OFF_LIGHT,
				-1);
		try 				  {	super.sendToServer(msg); }
		catch (IOException e) {	e.printStackTrace();	 }
	}

	public void turnOnFan(String deviceName) {
		Message msg = new Message(username, password,
				Message.SMART_FAN,
				deviceName,
				Message.TURN_ON_FAN,
				-1);
		try 				  {	super.sendToServer(msg); }
		catch (IOException e) {	e.printStackTrace();	 }
	}

	public void turnOffFan(String deviceName) {
		Message msg = new Message(username, password,
				Message.SMART_FAN,
				deviceName,
				Message.TURN_OFF_FAN,
				-1);
		try 				  {	super.sendToServer(msg); }
		catch (IOException e) {	e.printStackTrace();	 }
	}

	public void adjustLightBrightness(String deviceName, int brightness) {
		Message msg = new Message(username, password,
				Message.SMART_LIGHT,
				deviceName,
				Message.ADJUST_LIGHT_BRIGHTNESS,
				brightness);
		try 				  {	super.sendToServer(msg); }
		catch (IOException e) {	e.printStackTrace();	 }
	}

	public void adjustFanSpeed(String deviceName, int speed) {
		Message msg = new Message(username, password,
				Message.SMART_FAN,
				deviceName,
				Message.ADJUST_FAN_SPEED,
				speed);
		try 				  {	super.sendToServer(msg); }
		catch (IOException e) {	e.printStackTrace();	 }
	}

	public void adjustFanTemperature(String deviceName, int temp) {
		Message msg = new Message(username, password,
				Message.SMART_FAN,
				deviceName,
				Message.ADJUST_FAN_TEMP,
				temp);
		try 				  {	super.sendToServer(msg); }
		catch (IOException e) {	e.printStackTrace();	 }
	}

	public void requestNetworkDevices() {
		Message msg = new Message(username, password,
				"",
				"",
				Message.FIND_NETWORK_DEVICES,
				-1);
		try 				  {	super.sendToServer(msg); }
		catch (IOException e) {	e.printStackTrace();	 }
	}

	public void requestConnectedDevices() {
		Message msg = new Message(username, password,
				"",
				"",
				Message.REQUEST_CONNECTED_DEVICES,
				-1);
		try 				  {	super.sendToServer(msg); }
		catch (IOException e) {	e.printStackTrace();	 }
	}

}