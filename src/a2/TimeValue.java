package a2;

/**
 * Class that holds the timer functions.
 * @author Tom Wood, George Williams Walton
 * @version 1.6
 */
public class TimeValue implements Comparable<TimeValue> {
	
	private static final int MILL = 60;
	private static final int SECOND = 1000;
	private static final int MINUTE = 60000;
	private Long time;
	
	/**
	 * Updates the time parameter.
	 * @param time holds integer for time taken
	 */
	public TimeValue(Long time) {
		this.time = time;
	}
	/**
	 * Getter for the time taken.
	 * @return gives the current time taken
	 */
	public Long getTime() {
		return time;
	}
	
	/**
	 * Abstract Method to compare times.
	 * @param tv holds a numerical value for the time
	 * @return gives the result of the comparison
	 */
	public int compareTo(TimeValue tv) {
		return (int) (time - tv.getTime());
	}
	/**
	 * Returns a string for the timer.
	 * @return the current timer formatted for the user
	 */
	public String toString() {
		return String.format("%02d:%02d:%03d",
				getMinuites(), getSeconds(), getMsecs());
	}
	/**
	 * Getter for time, converted to minutes.
	 * @return gives time in minutes rather than milliseconds
	 */
	public int getMinuites() {
		return (int) (time / MINUTE);
	}
	/**
	 * Getter for time, converted to seconds.
	 * @return gives time in seconds rather than milliseconds
	 */
	public int getSeconds() {
		return (int) ((time - getMinuites()) / SECOND % MILL);
	}
	/**
	 * Getter for time in milliseconds.
	 * @return gives the time in milliseconds
	 */
	public int getMsecs() {
		return (int) ((time - getSeconds()) % SECOND);
	}
}