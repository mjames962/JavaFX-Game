package a2;


import java.io.IOException;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * .
 * @author 
 *
 */
public class LevelSelectController {
	private Stage stage;
	
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
		Level level = new Level("resources/file formats/testFileFormat1.txt");
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
        int playerX = Level.getPlayer().getVector().getX();
        int playerY = Level.getPlayer().getVector().getY();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(level.getCellAt(playerX, playerY).getSprite(), playerX, playerY);
        
	}
	
	
	private void setCellTypes() {
		
	}
	
}
