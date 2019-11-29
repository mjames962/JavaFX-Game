
package a2;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


/**
 * The main class for this program.
 * 
 * @author Jensen Mitch
 *
 */

public class Main extends Application {

	public static final int LEVEL_LOADER_WINDOW_WIDTH = 900;
	public static final int LEVEL_LOADER_WINDOW_HEIGHT = 500;
	public static final String LEVEL_LOADER_WINDOW_TITLE = "Placeholder";
	
	public static final int CREATE_PROFILE_WINDOW_WIDTH = 900;
	public static final int CREATE_PROFILE_WINDOW_HEIGHT = 500;
	public static final String CREATE_PROFILE_WINDOW_TITLE = "Create a Profile";
	
	private static final int MAIN_WINDOW_WIDTH = 900;
	private static final int MAIN_WINDOW_HEIGHT = 500;
	private static final String WINDOW_TITLE = "Placeholder";

	
	
	/**
	 * Loads the main window of the game.
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			// Load the main scene.
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass()
					.getResource("resources/fxml docs/MainMenu.fxml"));
			root.setId("pane");
			Scene scene = new Scene(root, MAIN_WINDOW_WIDTH,
					MAIN_WINDOW_HEIGHT);
			scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
			// Place the main scene on stage and show it.
			primaryStage.setScene(scene);
			primaryStage.setTitle(WINDOW_TITLE);
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * The main method of the program.
	 * 
	 * @param args arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
