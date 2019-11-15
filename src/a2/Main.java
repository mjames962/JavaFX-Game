package a2;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

/**
 * The main class for this program.
 * @author Jensen Mitch
 *
 */
public class Main extends Application {
	// Constants for the main window
	private static final int MAIN_WINDOW_WIDTH = 600;
	private static final int MAIN_WINDOW_HEIGHT = 400;
	private static final String WINDOW_TITLE = "PlaceHolder";
	
	@Override
	public void start(Stage primaryStage) {
		try {
			// Load the main scene.
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("C:/Users/Jensen/git/a2/src/a2/resources/MainMenu.fxml"));
			Scene scene = new Scene(root, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
	
			// Place the main scene on stage and show it.
					primaryStage.setScene(scene);
					primaryStage.setTitle(WINDOW_TITLE);
					primaryStage.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			public static void main(String[] args) {
				launch(args);
			}
	}
	

	
