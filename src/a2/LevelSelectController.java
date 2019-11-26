package a2;


import cell.Cell;
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
import javafx.stage.Stage;

/**
<<<<<<< HEAD
 * .
 * @author 
 *
=======
 * Displays the current level.
 * @author Jensen Beard, George Williams-Walton, and Darius Thomas
 * @version 1.0
 */
public class LevelSelectController {

	private static final int CANVAS_WIDTH = 350;
	private static final int CANVAS_LENGTH = 350;


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
			createLevelStage(level);
		} else if (currentLevel.equals("Level 2")) {
			level = new Level(
					"src/a2/resources/file formats/testFileFormat2.txt");
			createLevelStage(level);
		} else if (currentLevel.equals("Level 3")) {
			level = new Level(
					"src/a2/resources/file formats/testFileFormat3.txt");
			createLevelStage(level);
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Please select a level");
			alert.setContentText(null);
			alert.showAndWait();
		}	 
	}	

	private void createLevelStage(Level level) {
		Stage newStage = new Stage();
		//newStage.setTitle("Drawing Operations Test");
		Group root = new Group();
		Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_LENGTH);
		root.getChildren().add(canvas);
		newStage.setScene(new Scene(root));
		newStage.show();
		int playerX = level.getPlayer().getVector().getX();
		int playerY = level.getPlayer().getVector().getY();
		GraphicsContext gc = canvas.getGraphicsContext2D();

		for (int x = 0; x < 7; x++) {
			for (int y = 0; y < 7;y++) {
				Cell currentCell = level.getCellAt(x, y);
				Image cellImage = new Image(currentCell.getSprite());
				gc.drawImage(cellImage, currentCell.getX() * 50, currentCell.getY() * 50);
			}
		}      
	}
}