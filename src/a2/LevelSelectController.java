package a2;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cell.Cell;
import cell.Wall;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Displays the current level.
 * @author Jensen Beard, George Williams-Walton and Darius Thomas
 * @version 1.0
 */
public class LevelSelectController implements Initializable {
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
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
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
			} else {
				levelNum = -1;
			}		
			if (file.isFile() && UserData.getCurrentUser().getHighestLevel() >= levelNum) {
		    	cmb_LevelSelect.getItems().add(getLevelIdentifier(fileName));
		    }
		}
		
	}
	
	
	private void displayNewLevel(Level lvl) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().
				getResource("resources/fxml docs/GameWindow.fxml"));
		AnchorPane window = fx.load();
		
		//Stage stage = (Stage) levelScene.getScene().getWindow();
		
		
		//levelScene.getChildren().setAll(window);

		
	}
	
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
	
	private void formatLevel() throws FileNotFoundException{
		
		String currentLevel;
		currentLevel = cmb_LevelSelect.getValue();
		
		//File levelLeaderboard = new File("src/a2/resources/file formats/LB"
		//		 + currentLevel + ".txt");
		if(currentLevel != null) {
			selectedLevel = new Level(
					"src/a2/resources/file formats/"+ currentLevel + ".txt");	
		}
		
	}
	
	public String getLevelIdentifier(String fileName) {
		
		return fileName.replaceFirst("\\.txt", "");
	}
	@FXML
	private void handleLogOutBtn(ActionEvent event) throws IOException {
		AnchorPane window = FXMLLoader.load(getClass().
				getResource("resources/fxml docs/MainMenu.fxml"));  
		levelScene.getChildren().setAll(window);
	}
	@FXML
	private void handleLeaderboard() throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().
				getResource("resources/fxml docs/Leaderboard.fxml"));
		AnchorPane window = fx.load();  
		levelScene.getChildren().setAll(window);
		((LeaderboardController) fx.getController()).display();
	}
}
