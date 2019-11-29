package a2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import cell.Wall;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class gameWindowController implements Initializable {
	public static final int LEVEL_WIDTH = 300;
	public static final int LEVEL_LENGTH = 300;
	
	private Stage levelStage;
	private Group root;
	private Canvas canvas;
	private Scene scene;
	private GraphicsContext gc;
	private Level level; 
	
	@FXML 
	private AnchorPane gamePane;
	
	@FXML
	private BorderPane gameBorderPane;
	
	public void initialise() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.root = new Group();
        this.canvas = new Canvas(LEVEL_WIDTH, LEVEL_LENGTH);
        root.getChildren().add(canvas);
        gameBorderPane.setCenter(root);
		
        gc = canvas.getGraphicsContext2D();
        
        Image wallImage = new Image(Wall.SPRITE);
		gc.drawImage(wallImage, 50, 
				50);
        
		
		
		// TODO Auto-generated method stub
		
	}	
	
}
