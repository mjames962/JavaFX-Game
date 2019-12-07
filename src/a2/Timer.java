package a2;

import java.lang.System;

/**
 * The timer that will track how long a player takes to complete a level.
 * @author Tom Wood
 * @version 1.0
 */

public class Timer {
	
	private static long lastTime;
	private static boolean running = false;
	private static long savedTime = 0;
	
	/**
	 * Gets the saved time elapsed in the level so far.
	 * @return the saved time spent in the level
	 */
	
	public static long getSavedTime() {
		return savedTime;
	}
	
	/**
	 * Returns if the timer "on".
	 * @return true/false if the timer is incrementing
	 */
	
	public static boolean isRunning() {
		return running;
	}
	
	/**
	 * Checks the current time of the system and sets
	 * the timer to the running status.
	 */
	
	public static void start() {
		lastTime = System.currentTimeMillis();
		running = true;
	}
	
	/**
	 * Checks how long the timer has been running for.
	 * @return The difference in the current system time to the time when
	 *  the timer started 
	 */
	public static long checkTimeElapsed() {
		long curTime = System.currentTimeMillis();
		return curTime + savedTime - lastTime;
	}
	
	/**
	 * Sets the timer running state to false.
	 */
	public static void stop() {
		savedTime = checkTimeElapsed();
		running = false;
	}
	
	/**
	 * sets the time saved in the level to a
	 * new value.
	 * @param save The value savedTime is being set to
	 */
	public static void setSavedTime(long save) {
		savedTime = save;
	}
}