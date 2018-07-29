package Utils;

public class Temporizer {

	private long startTime;

	public boolean checkClock(int seconds) {
		if (startTime == 0)
			startTime = System.currentTimeMillis();
		long currentTime = System.currentTimeMillis();

		long elapsed = currentTime - startTime;
		if (elapsed >= 1000 * seconds) {
			startTime = System.currentTimeMillis();
			return true;
		}
		return false;
	}

}
