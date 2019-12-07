package a2;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * Controller for the leaderboards window.
 * @author George Williams Walton, Tom Wood
 * @version 1.8
 */
public class LeaderboardController {
	
	@FXML 
	private Button timeBut;
	@FXML 
	private Button deathBut;
	@FXML 
	private ChoiceBox<String> levelSelector;
	@FXML 
	private TableView<LeaderboardEntry> leaderTable;
	@FXML
	private Button backButton;
	
	private boolean timeSelected = true;
	
	private boolean deathSelected = false;
	
	/**
	 * Displays the leaderboard.
	 */
	public void display() {
		addValuesToSelector();
		try {
			Leaderboard lead = UserData.readLeaderboard(1);
			lead.display(leaderTable);
		} catch (IOException e) {
			System.out.println("Failed to read leaderboard");
			System.exit(-1);
		}
		hookSelector();
		
        
	}
	
	/**
	 * Adds a listener to the levelselector to change leaderboard
	 */
	public void hookSelector() {
		levelSelector.getSelectionModel().
			selectedItemProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue<? extends String> 
						observableValue, String oldVal, String newVal) {
					changeDisplayedLeaderboard(UserData.getLevelNumber(newVal));
				}
			});
	}
	
	/**
	 * Change leaderboard to intended leaderboard to display.
	 * @param levelNo the level you are looking at the leaderboard for
	 */
	
	public void changeDisplayedLeaderboard(int levelNo) {
		
		try {
			Leaderboard lead;
			if (timeSelected) {
				lead = UserData.readLeaderboard(levelNo);
			} else {
				lead = UserData.getDeathLeaderboard(levelNo);
			}
			
			scrubTableView(leaderTable);
			lead.display(leaderTable);
		} catch (IOException e) {
			scrubTableView(leaderTable);
		}
	}
	
	/**
	 * Gets the current level shown by the leaderboard.
	 * @return the level shown by the leaderboard.
	 */
	
	public int getSelectedLevel() {
		return UserData.getLevelNumber(levelSelector.getValue());
	}
	
	/**
	 * Clears the table.
	 * @param tv the table being displayed
	 */
	public void scrubTableView(TableView tv) {
		tv.getItems().clear();
		tv.getColumns().clear();
		
	}
	
	/**
	 * Adds the vales to the options the leaderboard can show.
	 */

	public void addValuesToSelector() {
	    final int FILE_TYPE_LENGTH = 4;
		File folder = new File(UserData.LEVEL_FOLDER_LOCATION);
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
            if (!file.getName().contains("_")) {
                String fileName = file.getName();

                //gets level number from file name
                String levelString = fileName.substring(0, fileName.length() -
                        FILE_TYPE_LENGTH);
                int levelNum;
                Matcher matcher = Pattern.compile("([0-9]+)\\.txt").
                        matcher(fileName);
                if (matcher.find()) {
                    levelNum = Integer.parseInt(matcher.group(1));
                    //System.out.println(levelNum);
                } else {
                    levelNum = -1;
                }
                if (file.isFile()) {
                    String identifier = UserData.getLevelIdentifier(file);
                    levelSelector.getItems().add(identifier);
                    if (levelNum == 1) {
                        levelSelector.setValue(identifier);
                    }
                }
            }
		}
	}
	/**
	 * Allows for pressing buttons.
	 */
	public void timeButPress() {
		if (!timeSelected) {
			deathSelected = false;
			timeSelected = true;
			timeBut.setDefaultButton(true);
			deathBut.setDefaultButton(false);
			changeDisplayedLeaderboard(getSelectedLevel());
		}
	}
	/**
	 * Allows for pressing buttons.
	 */
	public void deathButPress() {
		if (!deathSelected) {
			deathSelected = true;
			timeSelected = false;
			timeBut.setDefaultButton(false);
			deathBut.setDefaultButton(true);
			changeDisplayedLeaderboard(getSelectedLevel());
		}
			
	}
	
	/**
	 * Goes to previous window when back button pressed. 
	 * @throws IOException if there is a failure reading throw exception
	 */
	
	public void backButtonPressed() throws IOException {
		AnchorPane window = FXMLLoader.load(getClass().
				getResource("resources/fxml docs/LevelSelection.fxml"));  
		
		AnchorPane ap = (AnchorPane) backButton.getScene().getRoot();
		ap.getChildren().setAll(window);
	}
}