package a2;

import java.lang.System;

public class Timer {
	
	long lastTime;
	boolean running;
	
	public Timer() {
		running = false;
	}
	
	public void start() {
		lastTime = System.currentTimeMillis();
		running = true;
	}
	
	public long checkTimeElapsed() {
		long curTime = System.currentTimeMillis();
		return curTime - lastTime;
	}
	
	public void stop() {
		running = false;
	}

}
