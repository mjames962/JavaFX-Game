package a2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.TableColumn.SortType;


/**
 * Holds information and methods to interact with user data.
 * @author Jensen, mitch
 * @version 1.4
 *
 */
public class UserData {
	
	public static final String USERS_FILE_LOCATION =
			"src/a2/resources/User files/Users.txt";
	public static final String USER_FOLDER_LOCATION = 
			"src/a2/resources/User files";
	public static final String LEVEL_FOLDER_LOCATION = 
			"src/a2/resources/file formats";
	public static final String IMAGE_FOLDER_LOCATION = 
			"src/a2/resources/stock photos";
	public static final String FXML_FOLDER_LOCATION = 
			"src/a2/resources/fxml docs";
	public static final String LEADERBOARD_FOLDER_LOCATION = 
			"src/a2/resources/Leaderboards";
	private static Profile currentUser;
	
	/**
	 * Gets the current user playing the game.
	 * @return the user playing the game
	 */
	
	public static Profile getCurrentUser() {
		return currentUser;
	}
	
	
	/**
	 * Sets the current user playing the game. 
	 * @param profile The new user playing the game
	 */
	
	private static void setCurrentUser(Profile profile) {
		currentUser = profile;
	}
	
	/**
	 * Finds if a user exists in the system.
	 * @param username the user being checked against the user Files.
	 * @return if the user exists.
	 */
	 
	public static boolean doesExist(String username) {
		File inputFile = new File(USERS_FILE_LOCATION);
		try {
			inputFile.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		Scanner in = null;
		try {
			in = new Scanner(inputFile);
		} catch (FileNotFoundException e) {
			System.out.println("Cannot open Users.txt");
			System.exit(0);
		}

		return doesExist(username, in);
	}
	
	/**
	 * Gets the save file for the user.
	 * @param userName the user you want the save file for.
	 * @return the user's save file.
	 */
	
	public static File getUserFile(String userName) {
		return new File(USER_FOLDER_LOCATION + userName + ".txt");
	}
	
	/**
	 * Reads all user accounts.
	 * @return A list of all users who have created accounts.
	 */
	
	public static ArrayList<String> readUsers() {
		File inputFile = new File(USERS_FILE_LOCATION);
		try {
			inputFile.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		Scanner in = null;
		try {
			in = new Scanner(inputFile);
		} catch (FileNotFoundException e) {
			System.out.println("Cannot open Users.txt");
			System.exit(0);
		}

		return readUsers(in);
	}
	
	/**
	 * 
	 * Reads all user accounts.
	 * @param in The scanner reading the file.
	 * @return A list of all users who have created accounts.
	 */
	
	private static ArrayList<String> readUsers(Scanner in) {
		ArrayList<String> userList = new ArrayList<>();
		
		while (in.hasNextLine()) {
			String fileUser = in.nextLine();
			userList.add(fileUser);
		}
		in.close();
		return userList;
	}
	
	/**
	 * Finds if a user exists in the list of users.
	 * @param username the list you are trying to find.
	 * @param in the file you are reading from.
	 * @return If the user is in the list.
	 */

	private static boolean doesExist(String username, Scanner in) {
		while (in.hasNextLine()) {
			String fileUser = in.nextLine();
			if (fileUser.equals(username)) {
				in.close();
				return true;
			}
		}
		in.close();
		return false;
	}
	
	/**
	 * Gets the level identifier before the level number.
	 * @param fle the File being read
	 * @return the text in the name of the level file before the number.
	 */
	
	public static String getLevelIdentifier(File fle) {
		String fileName = fle.getName();
		return fileName.replaceFirst("\\.txt", "");
	}
	
	
	/**
	 * Creates a user profile.
	 * @param username the name of the profile.
	 * @throws IOException if the program fails to write to a file throw
	 * exception.
	 */
	public static void createUser(String username) throws IOException {
		if (!doesExist(username)) {
			Files.write(Paths.get(USERS_FILE_LOCATION), 
					(username + "\n").getBytes(), StandardOpenOption.APPEND);

			File userFile = new File(USER_FOLDER_LOCATION + "/" +
					username + ".txt");
			Profile profile = new Profile(userFile);
			profile.updateFile();
		}
	}
	
	/**
	 * Deletes user profile.
	 * @param username the username you want to delete from the system.
	 * @throws IOException if the program fails to write to a file throw
	 * exception.
	 */
	
	public static void deleteUser(String username) throws IOException {
		if (doesExist(username)) {
			File userFile = new File(USER_FOLDER_LOCATION + "/" 
					+ username + ".txt");
			userFile.delete();

			File oldUsersFile = new File(USERS_FILE_LOCATION);

			File newUsersFile = new File(USER_FOLDER_LOCATION + "/"
					+ "newUsers.txt");
			newUsersFile.createNewFile();

			Scanner in = new Scanner(oldUsersFile);

			while (in.hasNextLine()) {
				String currentUser = in.nextLine();
				if (!currentUser.equals(username)) {
					Files.write(Paths.get(USER_FOLDER_LOCATION + "/" + 
							"newUsers.txt"), (currentUser + "\n").getBytes(),
							StandardOpenOption.APPEND);
				}
			}

			in.close();

			Files.delete(Paths.get(USERS_FILE_LOCATION));
			newUsersFile.renameTo(new File(USERS_FILE_LOCATION));
			
			deleteAllSaves(username);
		}
	}
	
	/**
	 * Deletes all of the level save files of the parsed in user.
	 * 
	 * @param username the username of the user who we are deleting all save files for
	 */
	private static void deleteAllSaves(String username) {
		File folder = new File(UserData.LEVEL_FOLDER_LOCATION);
		File[] listOfFiles = folder.listFiles();
		
		for (File file : listOfFiles) {
			if (file.getName().startsWith(username + "_")) {
				file.delete();
			}
		}	
	}
	
	/**
	 * Sets the current user profile in use.
	 * @param username the name of the profile.
	 * @throws IllegalStateException If the file cannot be read throw exception
	 * @throws FileNotFoundException If the file is not found throw exception.
	 */
	
	public static void setCurrentUser(String username) throws
			IllegalStateException, FileNotFoundException {
		if (doesExist(username)) {
			File userFile = new File(USER_FOLDER_LOCATION + "/" 
					+ username + ".txt");
			Profile user = new Profile(userFile);
			user.readFile();
			UserData.setCurrentUser(user);
		} else {
			throw new IllegalStateException();
		}
	}
	
	/**
	 * Gets the leaderboard file of a specified level.
	 * @param levelNo the level number of the leaderboard you want to get.
	 * @return the leaderboard file.
	 */
	
	public static File getLeaderboardFile(int levelNo) {
		return new File("src/a2/resources/Leaderboards/LBLevel"
				+ Integer.toString(levelNo) + ".txt");
	}
	
	/**
	 * The level number of a given file.
	 * @param fileName the name of the file.
	 * @return the level number specified by the name. 
	 */
	
	public static int getLevelNumber(String fileName) {
		
		Matcher matcher = Pattern.compile("([0-9]+)").matcher(fileName);
		if (matcher.find()) {
			return Integer.parseInt(matcher.group(1));
		} else {
			return -1;
		}
	}
	
	/**
	 * Reads the leaderboard file.
	 * @param levelNo the level you are reading the file for.
	 * @return the leaderboard holding all data read from the file.
	 * @throws IOException If there is an issue reading from file 
	 * throw exception.
	 */
	
	public static Leaderboard readLeaderboard(int levelNo) throws IOException {
		Scanner leaderboardFile;
		leaderboardFile = new Scanner(getLeaderboardFile(levelNo));
		
		Leaderboard leader = new Leaderboard();
		leader.addColumn(new LeaderboardColumn<>(String.class, "Name"));
		leader.addColumn(new LeaderboardColumn<>(TimeValue.class,
				"Time"));
		//Populate Leaderboard Prev Values

		while (leaderboardFile.hasNext()) {
			LeaderboardEntry le = new LeaderboardEntry(leader);
			le.addData("Name", leaderboardFile.next());
			TimeValue tv = new TimeValue(leaderboardFile.nextLong());
			le.addData("Time", tv);	
			leader.setSortedColumn("Time");
			leader.setSortType(SortType.ASCENDING);
			leader.addEntry(le);
		}
		leaderboardFile.close();
		return leader;
		
	}
	
	/**
	 * Gets the death leaderboard with data from a file.
	 * @param levelNo the level you are getting the deaths for.
	 * @return the leaderboard holding all data read from a file.
	 */
	
	public static Leaderboard getDeathLeaderboard(int levelNo) {
		File deathfile = getDeathFile(levelNo);
		Leaderboard leader = new Leaderboard();
		
		leader.addColumn(new LeaderboardColumn<>(String.class, "Name"));
		leader.addColumn(new LeaderboardColumn<>(Integer.class,
				"Deaths"));
		leader.setSortedColumn("Deaths");
		HashMap<String, Integer> deathmap = readDeaths(deathfile);
		for (String name : deathmap.keySet()) {
			int deaths = deathmap.get(name);
			LeaderboardEntry le = new LeaderboardEntry(leader);
			le.addData("Name", name);
			le.addData("Deaths", deaths);
			leader.addEntry(le);
		}
		return leader;
	}
	
	/**
	 * Gets the death leaderboard File.
	 * @param level the level number you are getting the file for.
	 * @return the file holding the death leaderbaodr for a given level number.
	 */
	
	public static File getDeathFile(int level) {
		File deathfile = new File(UserData.LEADERBOARD_FOLDER_LOCATION +
				"/DTHLevel"
				+ Integer.toString(level) + ".txt");
		if (!deathfile.exists()) {
			try {
				deathfile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("Could not create death leaderboard file!");
				System.exit(-1);
			}
		}
		return deathfile;
	}
	
	/**
	 * Writes deaths to the death leaderboard.
	 * @param map the map containing names and deaths for each user.
	 * @param f the file being written to.
	 */
	
	public static void writeDeaths(HashMap<String, Integer> map, File f) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(f, "UTF-8");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		for (String k : map.keySet()) {
			Integer deaths = map.get(k);
			writer.println(k + " " + deaths.toString() + "\n");
		}
		writer.close();
	}
	
	
	/**
	 * Reads the death leaderboard for a given file.
	 * @param f The file being read from.
	 * @return The map containing the names and death totals for each user in 
	 * the leaderboard.
	 */
	public static HashMap<String, Integer> readDeaths(File f) {
		HashMap<String, Integer> map = new HashMap<>();
		Scanner in = null;
		try {
			in = new Scanner(f);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not read death leaderboard file!");
			System.exit(-1);
		}
		while (in.hasNext()) {
			
			String name = in.next();
			int deaths = in.nextInt();
			map.put(name, deaths);
			in.nextLine();
		}
		return map;
	}
}
