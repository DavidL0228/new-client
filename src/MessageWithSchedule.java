
public class MessageWithSchedule extends Message {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Schedule schedule;
	
	MessageWithSchedule(
			String username, 
			String password, 
			String deviceType, 
			int deviceID, 
			String whichFunction, 
			int firstData,
			int secondData,
			int thirdData,
			Schedule schedule) 
	{
		
		super(username, password, deviceType, deviceID, whichFunction, firstData, secondData, thirdData);
		this.schedule = schedule;
		
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
}
