package a2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
    private int leaderboardMaxLength = 3;
	private ArrayList<String> usersLB = new ArrayList<>();
	private ArrayList<Long> timesLB = new ArrayList<>();
	
	/**
	 * Sets the file that holds the user information. 
	 * @param f file holding user info
	 */
	
	public Profile(File f) {
		this.pfile = f;
		this.highestLevel = 1;
		
		String filePath = this.pfile.getPath();
		filePath = filePath.substring(28, filePath.length());
		this.name = filePath.replaceFirst(".txt", "");
	}
	
	public int getHighestLevel() {
		return this.highestLevel;
	}
	
	/**
	 * Imports information from the user's profile and
	 * the appropriate variables are set to the corresponding
	 * values.
	 * @param in the scanner that will read the file
	 * @throws FileNotFoundException 
	 */
	public void readFile() throws FileNotFoundException {
		Scanner in = new Scanner(new File(UserData.USER_FOLDER_LOCATION + "/" + this.name + ".txt"));
		
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
	 * @param newHigh the new highest level the user has reached
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
	 * Sets a new best time for a specified level.
	 * @param level the level the new time is being set to.
	 * @param time the new highest time of the level.
	 */
	
	public void setBestTime(int level, long time) {
		String filePath = "src/a2/resources/file formats/LBLevel"
				+ Integer.toString(level) + ".txt";
		if (isBestTime(level, time)) {
			if (bestTimes.size() == level - 1) {
				bestTimes.add(time);
			} else {
				bestTimes.remove(level - 1);
				bestTimes.add(level - 1, time);
			}
			Scanner leaderboardFile = null;
			try {
				leaderboardFile = new Scanner(new File(filePath));
			} catch  (IOException e) {
				System.out.println("Failed to load file");
			}
			//Populate Leaderboard Prev Values

			while (leaderboardFile.hasNext()) {
				usersLB.add(leaderboardFile.next());
				timesLB.add(leaderboardFile.nextLong());
			}
			
			
			//Add if applicable
			if (usersLB.size() < leaderboardMaxLength) {
				usersLB.add(name);
				timesLB.add(time);
			} else {
				findLowestTimes(name, time);
			}
			
			leaderboardFile.close();
			
			
			updateFile();
			//Write To file
			writeLeaderboardFile(filePath);
		}
	}
	
	public void findLowestTimes(String curName, long time) {
		for (Long entry : timesLB) {
			if (time < entry) {
				int pos = timesLB.indexOf(entry);
				String tempUser = usersLB.get(pos);
				usersLB.remove(pos);
				long tempTime = entry; 
				timesLB.remove(entry);
				usersLB.add(curName);
				timesLB.add(time);
				findLowestTimes(tempUser, tempTime);
			}
		}
	}
	
	public void writeLeaderboardFile(String filePath) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(filePath, "UTF-8");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int loopPos = 0;
		for (String eName : usersLB) {
			writer.println(eName + " " + Long.toString(timesLB.get(loopPos)));
			loopPos++;
		}
		writer.close();
	}
	
	
	public String getName() {
		return name;
	}
	
	
	/**
	 * Updates user file with changes made since reading
	 * from file.
	 * 
	 * @param file The location of the users file.
	 */
	
	public void updateFile() {
		
		String filePath = this.pfile.getPath();
		this.pfile.delete();
		this.pfile = new File(filePath);
		
		try {
			pfile.createNewFile();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(filePath, "UTF-8");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		writer.println(this.name);
		writer.println(this.highestLevel);
		for (int i = 0; i < bestTimes.size(); i++) {
			writer.println(Long.toString(bestTimes.get(i)));
		}
		writer.close();
		
	}
	
	
	
	
}
