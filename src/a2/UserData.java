package a2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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
	
	private static boolean doesExist(String username, Scanner in) {
		while (in.hasNextLine()) {
			String fileUser = in.nextLine();
			if (fileUser.equals(username)) {
				return true;
			}
		}
		return false;
	}
	
	
	// user file writer
	public static void createUser(String username) throws IOException {
		if (!doesExist(username)) {
			Files.write(Paths.get("src/a2/resources/User files/Users.txt"), 
					("\n" + username).getBytes(), StandardOpenOption.APPEND);

			File userFile = new File("src/a2/resources/User files/" + username + ".txt");
			Profile profile = new Profile(userFile);
			profile.updateFile();
		}
	}
	
	//create user individual file
	
	//update users individual file.
	
	//read users individual file
	public static void deleteUser(String username) {
		if (doesExist(username)) {
			
		}
	}
	
}
