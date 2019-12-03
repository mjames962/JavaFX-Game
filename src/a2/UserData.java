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
	
}
