package a2;

import java.io.File;
import java.io.IOException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class UserMainTest {

	public static void main(String[] args) {

	Level level = new Level(Level.LEVEL_STORAGE + "/Level2.txt");
	
	Profile currentUser = new Profile(new File(UserData.USER_FOLDER_LOCATION + "/Mitch.txt"));
	
	try {
		level.saveLevelProgress(currentUser);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	Level levelSave = new Level(Level.LEVEL_STORAGE + "/Mitch_Level2.txt");
	
	
	}

}
