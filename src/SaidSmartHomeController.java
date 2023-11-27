import java.util.ArrayList;

public class SaidSmartHomeController {

	public void displayFanStatus(int firstData) {
		// TODO Auto-generated method stub
		System.out.printf("Fan On : %s %n", (firstData != 0 ? "Yes" : "No") );
	}

	public void displayAllDevices(ArrayList<SmartDevice> devices) {
		// TODO Auto-generated method stub
		for( SmartDevice i : devices ) {
			System.out.printf("%d %n", i.getDeviceID());
		}
	}

	public void displayConnectedDevices(ArrayList<SmartDevice> devices) {
		// TODO Auto-generated method stub
		for( SmartDevice i : devices ) {
			System.out.printf("%d %n", i.getDeviceID());
		}
	}

	public void displayLightStatus(int firstData) {
		// TODO Auto-generated method stub
		System.out.printf("Light On : %s %n", (firstData != 0 ? "Yes" : "No") );
	}

	public void displayLockStatus(int firstData) {
		// TODO Auto-generated method stub
		System.out.printf("Lock On : %s %n", (firstData != 0 ? "Yes" : "No") );
	}

	public void displaySmokeAlarmStatus(int firstData, int secondData, int thirdData) {
		// TODO Auto-generated method stub
		System.out.printf("Smoke Alarm Percent : %s %n", (firstData) );
		System.out.printf("Smoke Alarm Sound   : %s %n", (secondData != 0 ? "Yes" : "No") );
		System.out.printf("Smoke Alarm On      : %s %n", (firstData != 0 ? "Yes" : "No") );
	}

	public void displayThermostatStatus(int firstData) {
		// TODO Auto-generated method stub
		System.out.printf("Thermostat On : %s %n", (firstData != 0 ? "Yes" : "No") );
	}

}
