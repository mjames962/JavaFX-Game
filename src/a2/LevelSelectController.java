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
		Stage s = new Stage();
		//s.setTitle("Drawing Operations Test");
        Group root = new Group();
        Canvas canvas = new Canvas(300, 250);
     
        root.getChildren().add(canvas);
        s.setScene(new Scene(root));
        s.show();
        
		

	}	
	public void drawShapes(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        gc.strokeLine(40, 10, 10, 40);
        gc.fillOval(10, 60, 30, 30);
    }
}
