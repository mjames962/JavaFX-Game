package a2;

import java.text.SimpleDateFormat;

public class TimeValue implements Comparable<TimeValue> {

	private Long time;
	
	public TimeValue(Long time) {
		this.time = time;
		// TODO Auto-generated constructor stub
	}
	
	public Long getTime() {
		return time;
	}

	public int compareTo(TimeValue tv) {
		return (int) (time - tv.getTime());
	}
	
	public String toString() {
		return String.format("%02d:%02d:%03d",getMinuites(),getSeconds(),getMsecs());
	}
	
	public int getMinuites() {
		return (int) (time / 60000);
	}
	
	public int getSeconds() {
		return (int) ((time - getMinuites()) / 1000 % 60);
	}
	
	public int getMsecs() {
		
		return (int) ((time - getSeconds()) % 1000);
	}
}
