package a2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * 
 * @author Jensen, mitch
 *
 */
public class UserData {
	
	final public static String USERS_FILE_LOCATION = "src/a2/resources/User files/Users.txt";
	final public static String USER_FOLDER_LOCATION = "src/a2/resources/User files";
	final public static String LEVEL_FOLDER_LOCATION = "src/a2/resources/file formats";
	final public static String IMAGE_FOLDER_LOCATION = "src/a2/resources/stock photos";
	final public static String FXML_FOLDER_LOCATION = "src/a2/resources/fxml docs";
	
	private static Profile currentUser; 
	
	public static Profile getCurrentUser() {
		return currentUser;
	}
	
	
	
	private static void setCurrentUser(Profile profile) {
		currentUser = profile;
	}
	 
	 public static boolean doesExist(String username) {
		File inputFile = new File(USERS_FILE_LOCATION);
		try {
			inputFile.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
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
	
	public static File getUserFile(String userName) {
		return new File(USER_FOLDER_LOCATION + userName + ".txt");
	}
	
	public static ArrayList<String> readUsers() {
		File inputFile = new File(USERS_FILE_LOCATION);
		try {
			inputFile.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
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
	
	private static ArrayList<String> readUsers(Scanner in) {
		ArrayList<String> userList = new ArrayList<String>();
		
		while (in.hasNextLine()) {
			String fileUser = in.nextLine();
			userList.add(fileUser);
		}
		in.close();
		return userList;
	}

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
	
	
	// user file writer
	public static void createUser(String username) throws IOException {
		if (!doesExist(username)) {
			Files.write(Paths.get(USERS_FILE_LOCATION), 
					(username + "\n").getBytes(), StandardOpenOption.APPEND);

			File userFile = new File(USER_FOLDER_LOCATION + "/" + username + ".txt");
			Profile profile = new Profile(userFile);
			profile.updateFile();
		}
	}
	
	public static void deleteUser(String username) throws IOException {
		if (doesExist(username)) {
			File userFile = new File(USER_FOLDER_LOCATION + "/" + username + ".txt");
			userFile.delete();

			File oldUsersFile = new File(USERS_FILE_LOCATION);

			File newUsersFile = new File(USER_FOLDER_LOCATION + "/" + "newUsers.txt");
			newUsersFile.createNewFile();

			Scanner in = new Scanner(oldUsersFile);

			while (in.hasNextLine()) {
				String currentUser = in.nextLine();
				if (!currentUser.equals(username)) {
					Files.write(Paths.get(USER_FOLDER_LOCATION + "/" + "newUsers.txt"), (currentUser + "\n").getBytes(),
							StandardOpenOption.APPEND);
				}
			}

			in.close();

			Files.delete(Paths.get(USERS_FILE_LOCATION));
			newUsersFile.renameTo(new File(USERS_FILE_LOCATION));
		}
	}
	
	public static void setCurrentUser(String username) throws IllegalStateException, FileNotFoundException {
		if (doesExist(username)) {
			File userFile = new File(USER_FOLDER_LOCATION + "/" + username + ".txt");
			Profile user = new Profile(userFile);
			user.readFile();
			UserData.setCurrentUser(user);
		} else {
			throw new IllegalStateException();
		}
	}
	
	public static File getLeaderboardFile(int levelNo) {
		return new File("src/a2/resources/Leaderboards/LBLevel"
				+ Integer.toString(levelNo) + ".txt");
	}
	
	public static Leaderboard readLeaderboard(int levelNo) {
		Scanner leaderboardFile = null;
		try {
			leaderboardFile = new Scanner(getLeaderboardFile(levelNo));
		} catch  (IOException e) {
			System.out.println("Failed to load file");
		}
		Leaderboard leader = new Leaderboard();
		leader.addColumn(new LeaderboardColumn<String>(String.class, "Name"));
		leader.addColumn(new LeaderboardColumn<TimeValue>(TimeValue.class, "Time"));
		//Populate Leaderboard Prev Values

		while (leaderboardFile.hasNext()) {
			LeaderboardEntry le = new LeaderboardEntry(leader);
			le.addData("Name", leaderboardFile.next());
			TimeValue tv = new TimeValue(leaderboardFile.nextLong());
			le.addData("Time", tv);	
			leader.setSortedColumn("Time");
			leader.addEntry(le);
		}
		return leader;
		
	}
	
	
	
}
