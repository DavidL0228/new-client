import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import javafx.stage.Stage;

public class SmartClient extends AbstractClient {

	String username;
	String password;
	static SmartHomeController controller;
	boolean isAuthenticated = false;
	Stage stage;

	MouseEvent event;

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
		System.out.printf("Message From Server : %s %n", ((Message)msg).getWhichFunction() );
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
				System.out.println("device "+ i +" name:" + d.getDeviceType());
				index++;
			}
			String function = message.getWhichFunction();
			if(function.equals(Message.FIND_NETWORK_DEVICES)) {

				((homeScreenController)controller).setDisplayDevices(devices);
			}
			else if (function.equals(Message.REQUEST_CONNECTED_DEVICES)) {
				((homeScreenController)controller).setDisplayDevices( devices );
			}

		}
		else {
			Message message = ( Message )msg;
			String function = message.getWhichFunction();
			if( function.equals(Message.REQUEST_FAN_STATUS) ) {
				String isFanOn = message.getFirstData() == 1 ? "On": "Off";
				int fanSpeed = message.getSecondData();
				int fanTemp = message.getThirdData();
				//Schedule schedule = ((MessageWithSchedule)message).getSchedule();
				try {
					System.out.printf("Fan Updated :) %n");
					((fanMenuController)controller).displayFanStatus( message.getDeviceName(), isFanOn, fanSpeed, fanTemp);

				}
				catch(Exception e) { System.out.printf("Not in Fan Menu %n"); }
			}
			else if( function.equals(Message.REQUEST_LIGHT_STATUS) ) {
				System.out.println("Light status message received");
				String isLightOn = message.getFirstData() == 1 ? "On": "Off";
				int intensity = message.getSecondData();
				int timeout = message.getThirdData();
				int timeoutMins = timeout / 60;
				int timeoutHours = timeoutMins / 60;
				timeoutMins = timeoutMins - (timeoutHours * 60);
				int timeoutSeconds = timeout - (timeoutMins * 60) - (timeoutHours * 3600);
				//Schedule schedule = ((MessageWithSchedule)message).getSchedule();

				try {
					System.out.printf("Light Updated :) %n");
					( (lightMenuController)controller).displayLightStatus( message.getDeviceName(), isLightOn, intensity, timeoutSeconds, timeoutMins, timeoutHours );
				}
				catch(Exception e) { System.out.printf("Not in Light Menu %n"); }

			}
			else if( function.equals(Message.REQUEST_LOCK_STATUS) ) {
				String isLocked = message.getFirstData() == 1 ? "Locked": "Unlocked";
				int duration = message.getSecondData();
				int durationMins = duration / 60;
				int durationHours = durationMins / 60;
				durationMins = durationMins - (durationHours * 60);
				int durationSeconds = duration - (durationMins * 60) - (durationHours * 3600);
				try {
					((lockMenuController)controller).displayLockStatus( isLocked, durationSeconds, durationMins, durationHours );
				}
				catch(Exception e) { System.out.printf("Not in Lock Menu %n"); }
			}
			else if( function.equals(Message.REQUEST_SMOKE_ALARM_STATUS) ) {
				int percentage = message.getFirstData();
				String isSoundFunctional = message.getSecondData() == 1 ? "YES": "NO";
				String isDetectorFunctional = message.getThirdData() == 1 ? "YES": "NO";
				int smokeAmount = message.getFourthData();
				String isSmokeTooMuch = message.getFifthData() == 1 ? "YES": "NO";
				try {
					((smokeAlarmMenuController)controller).displaySmokeAlarmStatus( message.getFirstData() );
				}
				catch(Exception e) { System.out.printf("Not in Smoke Alarm Menu %n"); }
				//controller.displayLockStatus( message.getFirstData() );
			}
			else if( function.equals(Message.REQUEST_THERMOSTAT_STATUS) ) {
				String isThermoOn = message.getFirstData() == 1 ? "YES": "NO";
				int min = message.getSecondData();
				int max = message.getThirdData();
				int now = message.getFourthData();
				String isCirc = message.getFifthData() == 1 ? "YES": "NO";
				String mode = message.getSixthData() == 1 ? "Heating": "Cooling";
				//Schedule schedule = ((MessageWithSchedule)message).getSchedule();
				//controller.displayThermostatStatus( message.getFirstData() );
			}
			else if(function.equals(Message.NOTIFY_SMOKE_ALARM_USERS)) {

			}
			else if ( function.equals(Message.FUNCTION_SUCCESSFUL) ) {
				System.out.printf( "Success! %n" );
				if(controller instanceof loginController) {

						System.out.println("calling login successful ");
						( (loginController)controller ).setSuccess(1);
					System.out.println("success set to 1 ");

				}
				if(controller instanceof addUserController) {
					( (addUserController)controller ).setSuccess(1);
				}
			}
			else if ( function.equals(Message.FUNCTION_FAILED) ) {
				System.out.printf( "Failed! %n" );
				if(controller instanceof loginController) {

					System.out.println("calling login failed ");
					( (loginController)controller ).setSuccess(0);
					System.out.println("success set to 0 ");

				}
			}

		}
	}

	public void sendLoginInfo(String username, String password) {
		//this.stage = stage;
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

	public void sendFanSchedule(String deviceName, int fanSpeed, Time start, Time end) {
		MessageWithSchedule msg = new MessageWithSchedule(this.username,
				this.password,
				Message.SMART_FAN,
				deviceName,
				Message.SEND_FAN_SCHEDULE,
				fanSpeed, -1, -1,
				start,
				end);
		try					  {	super.sendToServer(msg);	}
		catch (IOException e) {	e.printStackTrace();		}
	}

	public void sendLightByMotionTime(String deviceName, int seconds, int intensity) {
		System.out.println("Updating light motion time");
		Message msg = new Message(this.username,
				this.password,
				Message.SMART_LIGHT,
				deviceName,
				Message.SEND_LIGHT_BY_MOTION_TIME,
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

	public void setLightTimeout( String deviceName, int timeToWait ) {

		Message msg = new Message(username,
				password,
				Message.SMART_LIGHT,
				deviceName,
				Message.SET_LIGHT_TIMEOUT,
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

	public void adjustTemperatureWithSchedule(String deviceName, int temperature, Time start, Time end) {
		MessageWithSchedule msg = new MessageWithSchedule(username,
				password,
				Message.SMART_THERMOSTAT,
				deviceName,
				Message.ADJUST_THERMO_TEMP_SCHEDULE,
				temperature, -1, -1,
				start,
				end);
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
				Message.MAINTAIN_TEMP_RANGE,
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
				Message.TURN_ON_THERMOSTAT,
				-1);
		try 				  {	super.sendToServer(msg); }
		catch (IOException e) {	e.printStackTrace();	 }
	}

	public void turnOffThermostat(String deviceName) {
		Message msg = new Message(username, password,
				Message.SMART_THERMOSTAT,
				deviceName,
				Message.TURN_OFF_THERMOSTAT,
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

	public void removeFanSchedule(String deviceName) {
		Message msg = new Message(username, password,
				Message.SMART_FAN,
				deviceName,
				Message.REMOVE_FAN_SCHEDULE,
				-1);
		try 				  {	super.sendToServer(msg); }
		catch (IOException e) {	e.printStackTrace();	 }
	}

	public void addLightSchedule(String deviceName, int brightness, Time start, Time end) {
		MessageWithSchedule msg = new MessageWithSchedule(username,
				password,
				Message.SMART_LIGHT,
				deviceName,
				Message.SEND_LIGHT_SCHEDULE,
				brightness, -1, -1,
				start,
				end);
		try 				  {	super.sendToServer(msg); }
		catch (IOException e) {	e.printStackTrace();	 }
	}

	public void removeLightSchedule(String deviceName) {
		Message msg = new Message(username, password,
				Message.SMART_LIGHT,
				deviceName,
				Message.REMOVE_LIGHT_SCHEDULE,
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

	public void renameDevice(String deviceName, String newName) {
		MessageWithName msg = new MessageWithName(username,
				password,
				"",
				deviceName,
				Message.RENAME_DEVICE,
				newName);
		try 				  {	super.sendToServer(msg); }
		catch (IOException e) {	e.printStackTrace();	 }
	}

	public void addNewDevice(String deviceName, String deviceType) {
		Message msg = new Message(username, password,
				deviceType,
				deviceName,
				Message.ADD_NEW_DEVICE,
				-1);
		try 				  {	super.sendToServer(msg); }
		catch (IOException e) {	e.printStackTrace();	 }
	}

	public void deleteDevice(String deviceName) {
		Message msg = new Message(username, password,
				"",
				deviceName,
				Message.DELETE_DEVICE,
				-1);
		try 				  {	super.sendToServer(msg); }
		catch (IOException e) {	e.printStackTrace();	 }
	}

	public void makeThermostatCooling(String deviceName) {
		Message msg = new Message(username, password,
				Message.SMART_THERMOSTAT,
				deviceName,
				Message.MAKE_THERMOSTAT_COOLING,
				-1);
		try 				  {	super.sendToServer(msg); }
		catch (IOException e) {	e.printStackTrace();	 }
	}

	public void makeThermostatHeating(String deviceName) {
		Message msg = new Message(username, password,
				Message.SMART_THERMOSTAT,
				deviceName,
				Message.MAKE_THERMOSTAT_HEATING,
				-1);
		try 				  {	super.sendToServer(msg); }
		catch (IOException e) {	e.printStackTrace();	 }
	}

	public void addUserToDevice(String deviceName) {
		Message msg = new Message(username, password,
				"", deviceName,
				Message.ADD_USER_TO_DEVICE, -1);
		try 				  {	super.sendToServer(msg); }
		catch (IOException e) {	e.printStackTrace();	 }
	}

	public void modifySmokeAmount(String deviceName, int smokeAmount) {
		Message msg = new Message(username, password,
				Message.SMART_SMOKE_ALARM, deviceName,
				Message.MODIFY_SMOKE_AMOUNT, smokeAmount);
		try 				  {	super.sendToServer(msg); }
		catch (IOException e) {	e.printStackTrace();	 }
	}

	public void getDeviceType(String deviceName) {
		Message msg = new Message(username, password,
				"", deviceName,
				Message.GET_DEVICE_TYPE, -1);
		try 				  {	super.sendToServer(msg); }
		catch (IOException e) {	e.printStackTrace();	 }
	}

}