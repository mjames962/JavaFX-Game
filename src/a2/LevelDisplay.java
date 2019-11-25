package a2;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * This class is responsible for displaying the current level.
 *  @author Darius Thomas
 *	@version 1.0
 */

public class LevelDisplay extends Application {
	private Button button;
	private final int MAX_HEIGHT = 350;
	private final int MAX_WIDTH = 350;
	
	/**
	 * Loads the level.
	 * @param args arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Creates the level scene.
	 * @param primaryStage the main stage
	 * @return 
	 * @throws Exception error message
	 */
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Title");
		
		button = new Button("Click");
		
		//Handles button presses
		button.setOnAction(e -> {
			System.out.print("Lambda");
			System.out.print("Lamb");
		});
		
		StackPane layout = new StackPane();
		layout.getChildren().add(button);
		
		Scene scene = new Scene(layout, MAX_HEIGHT, MAX_WIDTH);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
