package a2;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Profile {

	private File pfile;
	private String name;
	private int highestLevel;
	private ArrayList<Integer> bestTimes = new ArrayList<>();
	
	public Profile(File f) {
		this.pfile = f;
	}
	
	private void readFile(Scanner in) {
		name = in.nextLine();
		highestLevel = in.nextInt();
		while (in.hasNext()) {
			int time = in.nextInt();
			bestTimes.add(time);
		}
	}
	
	public void setHighestLevel(int newHigh) {
		highestLevel = newHigh;
	}
	
	private boolean isHighestTime(int level, int time) {
		return bestTimes.get(level) > time;
	}
	
	private void setBestTime(int level, int time) {
		if (isHighestTime(level, time)) {
			bestTimes.add(level, time);
		}
	}
	
	
	
	
}
