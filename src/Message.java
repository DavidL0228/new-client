import java.io.Serializable;

public class Message implements Serializable {
	/**
	 *
	 */


	private static final long serialVersionUID = 1L;

	final static public String SMART_LIGHT 			= "SMART_LIGHT";
	final static public String SMART_THERMOSTAT 	= "SMART_THERMOSTAT";
	final static public String SMART_LOCK 			= "SMART_LOCK";
	final static public String SMART_FAN 			= "SMART_FAN";
	final static public String SMART_SMOKE_ALARM 	= "SMART_SMOKE_ALARM";

	final static public String FUNCTION_SUCCESSFUL = "FUNCTION_SUCCESSFUL";
	final static public String FUNCTION_FAILED = "FUNCTION_FAILED";

	final static public String REQUEST_CONNECTED_DEVICES = "REQUEST_CONNECTED_DEVICES";
	final static public String NOTIFY_SMOKE_ALARM_USERS 	= "NOTIFY_SMOKE_ALARM_USERS";
	final static public String REQUEST_THERMOSTAT_STATUS = "REQUEST_THERMOSTAT_STATUS";
	final static public String REQUEST_SMOKE_ALARM_STATUS = "REQUEST_SMOKE_ALARM_STATUS";
	final static public String REQUEST_FAN_STATUS = "REQUEST_FAN_STATUS";
	final static public String REQUEST_LIGHT_STATUS = "REQUEST_LIGHT_STATUS";
	final static public String REQUEST_LOCK_STATUS = "REQUEST_LOCK_STATUS";
	final static public String UNLOCK = "UNLOCK";
	final static public String LOCK = "LOCK";
	final static public String ADD_NEW_USER = "ADD_NEW_USER";
	final static public String ESTABLISH_ADMIN = "ESTABLISH_ADMIN";
	final static public String TURN_ON_CIRCULATION = "TURN_ON_CIRCULATION";
	final static public String TURN_OFF_CIRCULATION = "TURN_OFF_CIRCULATION";
	final static public String TURN_ON_THERMOSTAT = "TURN_ON_THERMOSTAT";
	final static public String TURN_OFF_THERMOSTAT = "TURN_OFF_THERMOSTAT";
	final static public String REQUEST_ALARM_HISTORY = "REQUEST_ALARM_HISTORY";
	final static public String TURN_ON_LIGHT = "TURN_ON_LIGHT";
	final static public String TURN_OFF_LIGHT = "TURN_OFF_LIGHT";
	final static public String TURN_ON_FAN = "TURN_ON_FAN";
	final static public String TURN_OFF_FAN = "TURN_OFF_FAN";

	//int int
	final static public String SET_LIGHT_TIMEOUT = "SET_LIGHT_TIMEOUT";
	final static public String SET_LOCK_AFTER_TIME = "SET_LOCK_AFTER_TIME";
	final static public String ADD_NEW_ROOM = "ADD_NEW_ROOM";
	final static public String ADJUST_LIGHT_BRIGHTNESS = "ADJUST_LIGHT_BRIGHTNESS";
	final static public String ADJUST_FAN_SPEED = "ADJUST_FAN_SPEED";
	final static public String ADJUST_FAN_TEMP = "ADJUST_FAN_TEMP";
	final static public String REMOVE_LIGHT_SCHEDULE = "REMOVE_LIGHT_SCHEDULE";
	final static public String REMOVE_FAN_SCHEDULE = "REMOVE_FAN_SCHEDULE";

	//int int int
	final static public String SEND_LIGHT_BY_MOTION_TIME = "SEND_LIGHT_BY_MOTION_TIME";
	final static public String MODIFY_THERMO_TEMP_NOW = "MODIFY_THERMO_TEMP_NOW";

	//int int schedule
	final static public String SEND_FAN_SCHEDULE = "SEND_FAN_SCHEDULE";
	final static public String SEND_LIGHT_SCHEDULE = "SEND_LIGHT_SCHEDULE";

	//int int int int
	final static public String MAINTAIN_TEMP_RANGE = "MAINTAIN_TEMP_RANGE";

	//int int int schedule
	final static public String ADJUST_THERMO_TEMP_SCHEDULE = "ADJUST_THERMO_TEMP_SCHEDULE";

	//String String
	final static public String SEND_LOGIN_INFO = "SEND_LOGIN_INFO";
	final static public String SEND_REGISTER_INFO = "SEND_REGISTER_INFO";

	//no objects
	final static public String REQUEST_LOGOUT = "REQUEST_LOGOUT";
	final static public String FIND_NETWORK_DEVICES = "FIND_NETWORK_DEVICES";
    final static public String RENAME_DEVICE = "RENAME_DEVICE";
	final static public String ADD_NEW_DEVICE = "ADD_NEW_DEVICE";
	final static public String MAKE_THERMOSTAT_COOLING = "MAKE_THERMOSTAT_COOLING";
	final static public String MAKE_THERMOSTAT_HEATING = "MAKE_THERMOSTAT_HEATING";

	private String username;
	private String password;
	private String deviceType;
	private String deviceName;
	private String whichFunction;
	private int[] dataRequired = new int[6];

	Message(String username,
			String password,
			String deviceType,
			String deviceName,
			String whichFunction,
			int firstData )
	{

		this.setUsername(username);
		this.setPassword(password);
		this.setDeviceType(deviceType);
		this.setDeviceName( deviceName );
		this.setWhichFunction(whichFunction);
		this.dataRequired[0] = firstData;

	}
	private void setDeviceName(String deviceName) {
		this.deviceName = deviceName;

	}
	Message(String username,
			String password,
			String deviceType,
			String deviceName,
			String whichFunction,
			int firstData,
			int secondData)
	{

		this(username, password, deviceType, deviceName, whichFunction, firstData);
		this.dataRequired[1] = secondData;

	}
	Message(String username,
			String password,
			String deviceType,
			String deviceName,
			String whichFunction,
			int firstData,
			int secondData,
			int thirdData)
	{

		this(username, password, deviceType, deviceName, whichFunction, firstData, secondData);
		this.dataRequired[2] = thirdData;

	}
	Message(String username,
			String password,
			String deviceType,
			String deviceName,
			String whichFunction,
			int firstData,
			int secondData,
			int thirdData,
			int fourthData)
	{

		this(username, password, deviceType, deviceName, whichFunction, firstData, secondData, thirdData);
		this.dataRequired[3] = fourthData;

	}Message(String username,
			 String password,
			 String deviceType,
			 String deviceName,
			 String whichFunction,
			 int firstData,
			 int secondData,
			 int thirdData,
			 int fourthData,
			 int fifthData)
	{

		this(username, password, deviceType, deviceName, whichFunction, firstData, secondData, thirdData, fourthData);
		this.dataRequired[4] = fifthData;

	}
	Message(String username,
			String password,
			String deviceType,
			String deviceName,
			String whichFunction,
			int firstData,
			int secondData,
			int thirdData,
			int fourthData,
			int fifthData,
			int sixthData)
	{

		this(username, password, deviceType, deviceName, whichFunction, firstData, secondData, thirdData, fourthData, fifthData);
		this.dataRequired[5] = sixthData;

	}


	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getWhichFunction() {
		return whichFunction;
	}
	public void setWhichFunction(String whichFunction) {
		this.whichFunction = whichFunction;
	}

	public int getFirstData() {
		return dataRequired[0];
	}
	public void setFirstData(int firstData) {
		this.dataRequired[0] = firstData;
	}
	public int getSecondData() {
		return dataRequired[1];
	}
	public void setSecondData(int secondData) {
		this.dataRequired[1] = secondData;
	}
	public int getThirdData() {
		return dataRequired[2];
	}
	public void setThirdData(int thirdData) {
		this.dataRequired[2] = thirdData;
	}
	public int getFourthData() {
		return dataRequired[3];
	}
	public void setFourthData(int fourthData) {
		this.dataRequired[3] = fourthData;
	}
	public int getFifthData() {
		return dataRequired[4];
	}
	public void setFifthData(int fifthData) {
		this.dataRequired[4] = fifthData;
	}
	public int getSixthData() {
		return dataRequired[5];
	}
	public void setSixthData(int sixthData) {
		this.dataRequired[5] = sixthData;
	}
	public String getDeviceName() {
		return this.deviceName;
	}
	public void setNameOfDevice( String name ) {
		this.deviceName = name;
	}
}