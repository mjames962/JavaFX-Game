package a2;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.adapter.JavaBeanStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

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
	
	
	
	public void hookSelector() {
		levelSelector.getSelectionModel().
		selectedItemProperty().addListener(new ChangeListener<String>() {
		      @Override
		      public void changed(ObservableValue<? extends String> observableValue,String oldVal, String newVal) {
		  
		    	  changeDisplayedLeaderboard(UserData.getLevelNumber(newVal));
		      }
		});
	}
	
	public void changeDisplayedLeaderboard(int levelNo) {
		
		try {
			Leaderboard lead = null;
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
	
	public int getSelectedLevel() {
		return UserData.getLevelNumber(levelSelector.getValue());
	}
	
	public void scrubTableView(TableView tv) {
		tv.getItems().clear();
		tv.getColumns().clear();
		
	}

	public void addValuesToSelector() {
		File folder = new File(UserData.LEVEL_FOLDER_LOCATION);
		File[] listOfFiles = folder.listFiles();
		System.out.println("file testing");
		for (File file : listOfFiles) {
			String fileName = file.getName();
			
			//gets level number from file name
			String levelString = fileName.substring(0, fileName.length() - 4); 
			//String levelNumString = fileName.replaceFirst("([0-9]+)\\.txt", "");
			int levelNum;
			Matcher matcher = Pattern.compile("([0-9]+)\\.txt").matcher(fileName);
			if (matcher.find()) {
				levelNum =  Integer.parseInt(matcher.group(1));
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
	
	public void backButtonPressed() throws IOException {
		AnchorPane window = FXMLLoader.load(getClass().
				getResource("resources/fxml docs/LevelSelection.fxml"));  
		
		AnchorPane ap = (AnchorPane) backButton.getScene().getRoot();
		ap.getChildren().setAll(window);
	}


}
