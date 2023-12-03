import java.io.Serializable;
import java.sql.Time;

public class Schedule implements Serializable {
	private int scheduleId;
	private Time startTime;
	private Time endTime;

	public Schedule(int scheduleId, Time startTime, Time endTime) {
		this.scheduleId = scheduleId;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public Time getStartTime() {
		return startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setStartTime(Time newStartTime) {
		startTime = newStartTime;
	}

	public void setEndTime(Time newEndTime) {
		endTime = newEndTime;
	}
}
