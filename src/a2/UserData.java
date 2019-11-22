package a2;

import java.io.File;
import java.io.FileNotFoundException;
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
		File inputFile = new File("src/a2/resources/Users.txt");

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
	
	
	//user file writer
	public void createUser(String username) {
	}
	
	//create user individual file
	
	//update users individual file.
	
	//read users individual file
}
