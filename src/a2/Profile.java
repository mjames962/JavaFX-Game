package a2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Holds information about a user and their scores.
 * @author tomwo
 *
 */
public class Profile {

	private File pfile;
	private String name;
	private int highestLevel;
	private ArrayList<Long> bestTimes = new ArrayList<>();
	
	/**
	 * Sets the file that holds the user information. 
	 * @param f file holding user info
	 */
	
	public Profile(File f) {
		this.pfile = f;
	}
	
	/**
	 * Imports information from the user's profile and
	 * the appropriate variables are set to the corresponding
	 * values.
	 * @param in the scanner that will read the file
	 */
	
	private void readFile(Scanner in) {
		name = in.nextLine();
		highestLevel = in.nextInt();
		while (in.hasNext()) {
			long time = in.nextLong();
			bestTimes.add(time);
		}
	}
	
	/**
	 * Sets the highest level the user has reached to a new value.
	 * @param newHigh the new highest level the user has reached
	 */
	
	public void setHighestLevel(int newHigh) {
		highestLevel = newHigh;
	}
	
	
	/**
	 * Checks if the new time is higher than the old highest time for
	 * a specified level.
	 * @param level The level that the new time is being compared to.
	 * @param time The new time the user has just set.
	 * @return if the new time is higher than the old highest time.
	 */
	public boolean isHighestTime(int level, long time) {
		return bestTimes.get(level) > time;
	}
	
	/**
	 * Sets a new best time for a specified level.
	 * @param level the level the new time is being set to.
	 * @param time the new highest time of the level.
	 */
	
	public void setBestTime(int level, long time) {
		if (isHighestTime(level, time)) {
			bestTimes.add(level, time);
		}
	}
	
	/**
	 * Updates user file with changes made since reading
	 * from file.
	 * 
	 * @param file The location of the users file.
	 */
	
	public void updateFile(String file) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			out.write(name);
			out.newLine();
			out.write(highestLevel);
			out.newLine();
			for (int i = 0; i < bestTimes.size(); i++) {
				out.write(Long.toString(bestTimes.get(i)));
				out.newLine();
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			System.out.println("Failed to load file");
		}
		
	}
	
	
	
	
}
