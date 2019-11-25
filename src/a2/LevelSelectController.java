package a2;


import java.io.File;
import java.io.IOException;

import cell.Cell;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
<<<<<<< HEAD
 * .
 * @author 
 *
=======
 * Displays the current level.
 * @author Jensen Beard and George Williams-Walton
 * @version 1.0
>>>>>>> branch 'master' of https://gitlab.com/cs230g34/a2.git
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
		
		Level level = new Level("src/a2/resources/file formats/testFileFormat1.txt");
		createLevelStage(level);
	}	
	
	private void createLevelStage(Level level) {
		Stage s = new Stage();
		//s.setTitle("Drawing Operations Test");
        Group root = new Group();
        Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_LENGTH);
        root.getChildren().add(canvas);
        s.setScene(new Scene(root));
        s.show();
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
	
	
	private void setCellTypes() {
		
	}
}
