import java.sql.Time;

public class MessageWithSchedule extends Message {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Time start;
	private Time end;

	MessageWithSchedule(
			String username,
			String password,
			String deviceType,
			String deviceName,
			String whichFunction,
			int firstData,
			int secondData,
			int thirdData,
			Time start,
			Time end)
	{

		super(username, password, deviceType, deviceName, whichFunction, firstData, secondData, thirdData);
		this.start = start;
		this.end = end;

	}

	MessageWithSchedule(
			String username,
			String password,
			String deviceType,
			String deviceName,
			String whichFunction,
			int firstData,
			int secondData,
			Time start,
			Time end)
	{

		super(username, password, deviceType, deviceName, whichFunction, firstData, secondData);
		this.start = start;
		this.end = end;

	}

	public MessageWithSchedule(String username, String password, String smartThermostat, String alias,
							   String requestThermostatStatus, int isThermoOn, int tempMin, int tempMax, int tempNow, int isCirc,
							   int mode, Time start, Time end) {
		super(username, password, smartThermostat, alias, requestThermostatStatus, isThermoOn, tempMin, tempMax, tempNow, isCirc, mode);
		this.start = start;
		this.end = end;
	}

	public Time getStart() {
		return start;
	}
	public void setStart(Time start) {
		this.start = start;
	}
	public Time getEnd() {
		return end;
	}
	public void setEnd(Time end) {
		this.end = end;
	}
}