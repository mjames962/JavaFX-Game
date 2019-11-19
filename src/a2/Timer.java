package a2;

import java.lang.System;

public class Timer {
	
	private static long lastTime;
	private static boolean running = false;
	
	public static void start() {
		lastTime = System.currentTimeMillis();
		running = true;
	}
	
	public static long checkTimeElapsed() {
		long curTime = System.currentTimeMillis();
		return curTime - lastTime;
	}
	
	public static void stop() {
		running = false;
	}

}
