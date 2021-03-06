package a2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


/**
 * Holds information about a user and their scores.
 * @author Tom Wood
 * @version 1.5
 */
public class Profile {

    private static final int LEADERBOARDMAXLENGTH = 3;
    private static final int FILE_LENGTH = 28;
	private File pfile;
	private String name;
	private int highestLevel;
	private ArrayList<Long> bestTimes = new ArrayList<>();
	private ArrayList<String> usersLB = new ArrayList<>();
	private ArrayList<Long> timesLB = new ArrayList<>();
	private ArrayList<Long> addItems = new ArrayList<>();
	private ArrayList<Long> removeTimes = new ArrayList<>();
	
	
	/**
	 * Sets the file that holds the user information. 
	 * @param f file holding user info
	 */
	
	public Profile(File f) {
		this.pfile = f;
		this.highestLevel = 1;
		
		String filePath = this.pfile.getPath();
		filePath = filePath.substring(FILE_LENGTH, filePath.length());
		this.name = filePath.replaceFirst(".txt", "");
	}
	/**
	 * Returns the highest level a user can play.
	 * @return gives an int for the highest level the user can play
	 */
	public int getHighestLevel() {
		return this.highestLevel;
	}
	
	/**
	 * Imports information from the user's profile and
	 * the appropriate variables are set to the corresponding
	 * values.
	 * @throws FileNotFoundException If the file cannot be found throw exception.
	 */
	public void readFile() throws FileNotFoundException {
		Scanner in = new Scanner(new File(UserData.
				USER_FOLDER_LOCATION + "/" + this.name + ".txt"));
		
		this.name = in.next();
		this.highestLevel = in.nextInt();
		
		while (in.hasNext()) {
			long time = in.nextLong();
			this.bestTimes.add(time);
		}
		
		in.close();
	}
	
	/**
	 * Sets the highest level the user has reached to a new value.
	 * @param level the new highest level the user has reached.
	 */
	
	public void setHighestLevel(int level) {
		if (level == highestLevel) {
			highestLevel++;
		}
		updateFile();
	}
	
	/**
	 * Checks if the new time is higher than the old highest time for
	 * a specified level.
	 * @param level The level that the new time is being compared to.
	 * @param time The new time the user has just set.
	 * @return if the new time is higher than the old highest time.
	 */
	public boolean isBestTime(int level, long time) {
		if (bestTimes.size() <= level) {
			return true;
		} else {
			return bestTimes.get(level - 1) > time; 
		}
	}
	
	/**
	 * Saves the deaths of the player on the current level.
	 * @param level The level the player is on.
	 * @param deaths The number of deaths.
	 */
	
	public void saveDeaths(int level, int deaths) {
		File deathfile = UserData.getDeathFile(level);
		HashMap<String, Integer> deathmap = UserData.readDeaths(deathfile);
		addDeaths(deathmap, deaths);
		UserData.writeDeaths(deathmap, deathfile);
	}
	
	/**
	 * Adds a death to the users death count.
	 * @param deathmap the map of the username and deaths
	 * @param deaths the number of deaths.
	 */
	
	public void addDeaths(HashMap<String, Integer> deathmap, int deaths) {
		if (deathmap.containsKey(name)) {
			if (deathmap.get(name) > deaths) {
				deathmap.put(name, deaths);
			}
		} else {
			deathmap.put(name, deaths);
		}
	}

	/**
	 * Sets a new best time for a specified level.
	 * @param level the level the new time is being set to.
	 * @param time the new highest time of the level.
	 */
	
	public void setBestTime(int level, long time) {
		String filePath = "src/a2/resources/Leaderboards/LBLevel"
				+ Integer.toString(level) + ".txt";
		if (isBestTime(level, time)) {
			if (bestTimes.size() <= level - 1) {
				bestTimes.add(time);
			} else {
				bestTimes.remove(level - 1);
				bestTimes.add(level - 1, time);
			}
			Scanner leaderboardFile = null;
			try {
				leaderboardFile = new Scanner(UserData.
						getLeaderboardFile(level));
			} catch  (IOException e) {
			}
			//Populate Leaderboard Prev Values
			timesLB = new ArrayList<>();
			usersLB = new ArrayList<>();

			while (leaderboardFile.hasNext()) {
				usersLB.add(leaderboardFile.next());
				timesLB.add(leaderboardFile.nextLong());
			}
			
			addItems = new ArrayList<>();
			removeTimes = new ArrayList<>();
			
			
			//Add if applicable
			if (usersLB.size() < LEADERBOARDMAXLENGTH) {
				usersLB.add(name);
				timesLB.add(time);
			} else {
				findLowestTimes(name, time);
				timesLB.removeAll(removeTimes);
				timesLB.addAll(addItems);
			}
			
			leaderboardFile.close();
			
			
			updateFile();
			//Write To file
			writeLeaderboardFile(filePath);
		}
	}
	
	/**
	 * Searches for lowest existing time for a level.
	 * @param curName The name of the current user
	 * @param time the time taken to complete a given level, 
	 * to be uploaded to the leaderboard
	 */
	public void findLowestTimes(String curName, long time) {
		boolean swapped = false;
		String tempUser = null;
		long tempTime = 0;
		for (Long entry : timesLB) {
			if (time < entry && !swapped) {
				int pos = timesLB.indexOf(entry);
			    tempUser = usersLB.get(pos);
				usersLB.remove(pos);
				removeTimes.add(entry);
				tempTime = entry;
				usersLB.add(curName);
				addItems.add(time);
				swapped = true;
			}
		}
		if (swapped) {
			findLowestTimes(tempUser, tempTime);
		}
	}
	/**
	 * Writes the leaderboard to the file.
	 * @param filePath the directory path to hte given file
	 */
	public void writeLeaderboardFile(String filePath) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(filePath, "UTF-8");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		int loopPos = 0;
		for (String eName : usersLB) {
			writer.println(eName + " " + Long.toString(timesLB.get(loopPos)));
			loopPos++;
		}
		writer.close();
	}
	
	/**
	 * Returns the profile name.
	 * @return gives the name
	 */
	public String getName() {
		return name;
	}
	
	
	/**
	 * Updates user file with changes made since reading
	 *											 from file.
	 */	
	public void updateFile() {
		
		String filePath = this.pfile.getPath();
		this.pfile.delete();
		this.pfile = new File(filePath);
		
		try {
			pfile.createNewFile();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(filePath, "UTF-8");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		writer.println(this.name);
		writer.println(this.highestLevel);
        for (Long bestTime : bestTimes) {
            writer.println(Long.toString(bestTime));
        }
		writer.close();	
	}	
}