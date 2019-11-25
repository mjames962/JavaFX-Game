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
 * Displays the current level.
 * @author Jensen Beard and George Williams-Walton
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
		final int MAX_WINDOW_HEIGHT = 300;
		final int MAX_WINDOW_WIDTH = 250;
		Stage newStage = new Stage();
		//s.setTitle("Drawing Operations Test");
        Group root = new Group();
        Canvas canvas = new Canvas(MAX_WINDOW_HEIGHT, MAX_WINDOW_WIDTH);
     
        root.getChildren().add(canvas);
        newStage.setScene(new Scene(root));
        newStage.show();
	}
}
