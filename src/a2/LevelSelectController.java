package a2;

import java.io.File; 
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;


/**
 * Displays the current level.
 * @author Jensen Beard, George Williams-Walton, Darius Thomas, Mitch Thomas
 * @version 1.4
 */
public class LevelSelectController implements Initializable {
	private static final int IV = 4;
	private Level selectedLevel;
	@FXML
	private Button btn_LoadLevel;
	@FXML
	private ComboBox<String> cmb_LevelSelect;
	@FXML
	private AnchorPane levelBottom;
	@FXML
	private AnchorPane levelScene;
	@FXML
	private Button btn_Log_Out;
	
	/**
	 * Identifies levels the user is allowed to play and 
	 * adds it to the drop down box .
	 * @param arg0 for JFX
	 * @param arg1 for JFX
	 */
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		File folder = new File(UserData.LEVEL_FOLDER_LOCATION);
		File[] listOfFiles = folder.listFiles();
		Profile currentUser = UserData.getCurrentUser();
		
		for (File file : listOfFiles) {
			String fileName = file.getName();

			//check to see if saves belong to current user
			if (fileName.startsWith(currentUser.getName() + "_") 
					|| !fileName.contains("_")) { 
				// gets level number from file name
				fileName.replaceFirst("([0-9]+)\\.txt", "");
				int levelNum;
				Matcher matcher = Pattern.compile("([0-9]+)\\.txt")
						.matcher(fileName);

				if (matcher.find()) {
					levelNum = Integer.parseInt(matcher.group(1));
				} else {
					levelNum = -1;
				}
				if (file.isFile() && UserData.getCurrentUser()
						.getHighestLevel() >= levelNum) {
					cmb_LevelSelect.getItems().add(getLevelIdentifier(fileName));
				}
			}
		}
	}
	
    /**
	 * Removes the .txt from level files.
	 * @param fileName holds the name of the file
	 * @return gives the file name minus .txt extension
	 */
	public String getLevelIdentifier(String fileName) {
		return fileName.replaceFirst("\\.txt", "");
	}
	
	/**
	 * Displays the GameWindow.
	 * @param lvl The level to be drawn
	 * @throws IOException On resource selection.
	 */
	private void displayNewLevel(Level lvl) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().
				getResource("resources/fxml docs/GameWindow.fxml"));
		AnchorPane window = fx.load();
		
	}
	

	/**
	 * Event handling to produce a pop-up alert + throw the error.
	 * @param event the action that instantiates the error throw
	 * @throws IOException when displayNewLevel fails
	 */
    @FXML
	private void handleLoadLevelBtn(ActionEvent event) throws IOException {
		formatLevel();
		if (selectedLevel == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Please Select a Level");
			alert.setContentText(null);
			alert.showAndWait();
		} else {
			displayNewLevel(selectedLevel);
		}	
	}
	
	/**
	 * Formatting level file names to be searched for.
	 */
	private void formatLevel() {
		
		String currentLevel;
		currentLevel = cmb_LevelSelect.getValue();
		if (currentLevel != null) {
			selectedLevel = new Level(
					"src/a2/resources/file formats/" + currentLevel + ".txt");	
		}
	}

	/**
	 * Handles the log out button.
	 * @param event for logging out
	 * @throws IOException thrown in the event of invalid inputs 
	 */
    @FXML
	private void handleLogOutBtn(ActionEvent event) throws IOException {
		AnchorPane window = FXMLLoader.load(getClass().
				getResource("resources/fxml docs/MainMenu.fxml"));  
		levelScene.getChildren().setAll(window);
	}

	/**
	 * Handles Leaderboard Window IO.
	 * @throws IOException thrown in the event of invalid inputs 
	 */
    @FXML
	private void handleLeaderboard() throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().
				getResource("resources/fxml docs/Leaderboard.fxml"));
		AnchorPane window = fx.load();  
		levelScene.getChildren().setAll(window);
		((LeaderboardController) fx.getController()).display();
	}
	
	/**
	 * Switches window to the character selection window.
	 * @param event Character select button click.
	 * @throws IOException On resource selection.
	 */
	@FXML
	private void handleCharSelectBtn(ActionEvent event) throws IOException {
		AnchorPane window = FXMLLoader.load(getClass().
				getResource("resources/fxml docs/characterSelect.fxml"));  
		levelScene.getChildren().setAll(window);	
	}
}