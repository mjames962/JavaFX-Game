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
 * @author Darius Thomas
 *
 */
public class LevelSelectController {
	private Stage stage;
	
	@FXML
	private Button btn_LoadLevel;
	
	@FXML
	private ComboBox<String> cmb_LevelSelect;
	
    @FXML
    private Canvas canvas1;
	
	@FXML
	private void handleLoadLevelBtn(ActionEvent event) {
		
		try {
			// Create a FXML loader for loading the Edit Country FXML file.
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().
					getResource("resources/Canvas.fxml"));     

			// Run the loader
			AnchorPane editRoot = (AnchorPane) fxmlLoader.load();          
			
			
			// Create a scene based on the loaded FXML scene graph
			Scene editScene = new Scene(editRoot, 
					Main.CREATE_PROFILE_WINDOW_WIDTH, 
					Main.CREATE_PROFILE_WINDOW_HEIGHT);
		    
			// Create a new stage (i.e., window) based on the edit scene
			Stage editStage = new Stage();
			editStage.setScene(editScene);
			editStage.setTitle(Main.CREATE_PROFILE_WINDOW_TITLE);
			Canvas canvas1 = new Canvas(300, 250);
	        GraphicsContext gc = canvas1.getGraphicsContext2D();
	        drawShapes(gc);
			
			// Make the stage a modal window.
			// This means that it must be closed before you can interact 
			// with any other window from this application.
			editStage.initModality(Modality.APPLICATION_MODAL);
			
			// Show the edit scene and wait for it to be closed
			editStage.showAndWait();
			
		} catch (IOException e) {
			e.printStackTrace();
			// Quit the program (with an error code)
			System.exit(-1);
			
		}

	}	
	public void drawShapes(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        gc.strokeLine(40, 10, 10, 40);
        gc.fillOval(10, 60, 30, 30);
    }
}
