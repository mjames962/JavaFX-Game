package a2;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;

public class MainMenuController {
	@FXML
	private Button btn_LogIn;
	@FXML 
	private Button btn_Create;
	@FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TextField tbox_Username;



	public void initialise() {
		 	assert btn_Create != null : "fx:id=\"btn_Create\" was not injected: check your FXML file 'MainMenu.fxml'.";
	        assert btn_LogIn != null : "fx:id=\"btn_LogIn\" was not injected: check your FXML file 'MainMenu.fxml'.";
	        
		btn_Create.setOnAction(e -> {
			handleCreateBtn();	
		});
	}
	
	private void handleCreateBtn() {
		try {
			// Create a FXML loader for loading the Edit Country FXML file.
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/CreateUser.fxml"));     

			// Run the loader
			BorderPane editRoot = (BorderPane) fxmlLoader.load();          
			// Access the controller that was created by the FXML loader
			//CreateUserController editController = fxmlLoader.<CreateUserController>getController();
			
			// Create a scene based on the loaded FXML scene graph
			Scene editScene = new Scene(editRoot, Main.CREATE_PROFILE_WINDOW_WIDTH, Main.CREATE_PROFILE_WINDOW_HEIGHT);
		    
			// Create a new stage (i.e., window) based on the edit scene
			Stage editStage = new Stage();
			editStage.setScene(editScene);
			editStage.setTitle(Main.CREATE_PROFILE_WINDOW_TITLE);
			
			// Make the stage a modal window.
			// This means that it must be closed before you can interact with any other window from this application.
			editStage.initModality(Modality.APPLICATION_MODAL);
			
			// Show the edit scene and wait for it to be closed
			editStage.showAndWait();
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Test");
			// Quit the program (with an error code)
			System.exit(-1);
		}
	}
}

