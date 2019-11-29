package a2;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

	@FXML
	private Button btn_LoadLevel;

	@FXML
	private ComboBox<String> cmb_LevelSelect;

	@FXML
	private AnchorPane levelBottom;
	
	@FXML
	private AnchorPane levelScene;
	
	public void initialise() {
		
	}
	
	@FXML
	private void handleLoadLevelBtn(ActionEvent event) throws IOException {

		String currentLevel = cmb_LevelSelect.getValue();
		Level level = null;
		
		if (currentLevel == null) {
			currentLevel = "";
		}
		
		if (currentLevel.equals("Level 1")) {
			level = new Level(
					"src/a2/resources/file formats/testFileFormat1.txt");
			AnchorPane window = FXMLLoader.load(getClass().
					getResource("resources/fxml docs/GameWindow.fxml"));  
			levelScene.getChildren().setAll(window);
		} else if (currentLevel.equals("Level 2")) {
			level = new Level(
					"src/a2/resources/file formats/testFileFormat2.txt");
			AnchorPane window = FXMLLoader.load(getClass().
					getResource("resources/fxml docs/GameWindow.fxml"));  
			levelScene.getChildren().setAll(window);
		} else if (currentLevel.equals("Level 3")) {
			level = new Level(
					"src/a2/resources/file formats/testFileFormat3.txt");
			AnchorPane window = FXMLLoader.load(getClass().
					getResource("resources/fxml docs/GameWindow.fxml"));  
			levelScene.getChildren().setAll(window);
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Please select a level");
			alert.setContentText(null);
			alert.showAndWait();
		}	 
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Label motd;
		try {
			motd = new Label(MOTD.getMOTD());
			levelBottom.getChildren().add(motd);
			motd.setWrapText(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		
	}	

}