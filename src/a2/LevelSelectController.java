package a2;


import cell.Cell;
import cell.Wall;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * Displays the current level.
 * @author Jensen Beard, George Williams-Walton and Darius Thomas
 * @version 1.0
 */
public class LevelSelectController {

	@FXML
	private Button btn_LoadLevel;

	@FXML
	private ComboBox<String> cmb_LevelSelect;

	@FXML
	private Canvas canvas1;

	@FXML
	private void handleLoadLevelBtn(ActionEvent event) {

		String currentLevel = cmb_LevelSelect.getValue();
		Level level = null;
		
		if (currentLevel == null) {
			currentLevel = "";
		}
		
		if (currentLevel.equals("Level 1")) {
			level = new Level(
					"src/a2/resources/file formats/testFileFormat1.txt");
			new LevelWindow(level);
		} else if (currentLevel.equals("Level 2")) {
			level = new Level(
					"src/a2/resources/file formats/testFileFormat2.txt");
			new LevelWindow(level);
		} else if (currentLevel.equals("Level 3")) {
			level = new Level(
					"src/a2/resources/file formats/testFileFormat3.txt");
			new LevelWindow(level);
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Please select a level");
			alert.setContentText(null);
			alert.showAndWait();
		}	 
	}	

}