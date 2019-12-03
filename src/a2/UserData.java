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
	//Users.txt file reader
	//check if username exists in Users.txt
	public static boolean doesExist(String username) {
		File inputFile = new File("src/a2/resources/User files/Users.txt");
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
		return new File("src/a2/resources/User files/" + userName + ".txt");
	}
	
	public static ArrayList<String> readUsers() {
		File inputFile = new File("src/a2/resources/User files/Users.txt");
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
			Files.write(Paths.get("src/a2/resources/User files/Users.txt"), 
					(username + "\n").getBytes(), StandardOpenOption.APPEND);

			File userFile = new File("src/a2/resources/User files/" + username + ".txt");
			Profile profile = new Profile(userFile);
			profile.updateFile();
		}
	}
	
	public static void deleteUser(String username) throws IOException {
		if (doesExist(username)) {
			File userFile = new File("src/a2/resources/User files/" + username + ".txt");
			userFile.delete();

			File oldUsersFile = new File("src/a2/resources/User files/Users.txt");

			File newUsersFile = new File("src/a2/resources/User files/newUsers.txt");
			newUsersFile.createNewFile();

			Scanner in = new Scanner(oldUsersFile);

			while (in.hasNextLine()) {
				String currentUser = in.nextLine();
				if (!currentUser.equals(username)) {
					Files.write(Paths.get("src/a2/resources/User files/newUsers.txt"), (currentUser + "\n").getBytes(),
							StandardOpenOption.APPEND);
				}
				
			}
			
			in.close();
			
			
			Files.delete(Paths.get("src/a2/resources/User files/Users.txt"));
			newUsersFile.renameTo(new File("src/a2/resources/User files/Users.txt"));
			
		}
	}
	
}
