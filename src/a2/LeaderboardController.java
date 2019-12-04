package a2;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.adapter.JavaBeanStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * Controller for the leaderboards window.
 * @author George Williams Walton, Tom Wood
 * @version 1.8
 */
public class LeaderboardController {
	
	@FXML 
	private Button leadBut1;
	@FXML 
	private Button leadBut2;
	@FXML 
	private Button leadBut3;
	@FXML 
	private ChoiceBox<String> levelSelector;
	@FXML 
	private TableView<LeaderboardEntry> leaderTable;

	
	/**
	 * Displays the leaderboard.
	 */
	public void display() {
		addValuesToSelector();
		Leaderboard lead = UserData.readLeaderboard(1);
		if (lead != null) {
			lead.display(leaderTable);
		}
		hookSelector();
		
        
	}
	
	
	
	public void hookSelector() {
		levelSelector.getSelectionModel().
		selectedItemProperty().addListener(new ChangeListener<String>() {
		      @Override
		      public void changed(ObservableValue<? extends String> observableValue,String oldVal, String newVal) {
		    	  System.out.println(oldVal + newVal);
		    	  System.out.println(UserData.getLevelNumber(newVal));
		    	  changeDisplayedLeaderboard(UserData.getLevelNumber(newVal));
		      }
		});
	}
	
	public void changeDisplayedLeaderboard(int levelNo) {
		Leaderboard lead = UserData.readLeaderboard(levelNo);
		if (lead != null) {
			scrubTableView(leaderTable);
			lead.display(leaderTable);
		}
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
				System.out.println(levelNum);
			} else {
				levelNum = -1;
			}		
			if (file.isFile()) {
		    	levelSelector.getItems().add(UserData.getLevelIdentifier(file));
		    }
		}
	}
	/**
	 * Allows for pressing buttons.
	 */
	public void leadBut1Press() {
		
	}
	/**
	 * Allows for pressing buttons.
	 */
	public void leadBut2Press() {
			
	}
	/**
	 * Allows for pressing buttons.
	 */
	public void leadBut3Press() {
		
	}

}
