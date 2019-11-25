package a2;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * This Class Controls the actions for the GUI in the Main Menu Window.
 * 
 * @author Jensen, Mitch
 */
public class MainMenuController {
	@FXML
	private Button btn_LogIn;
	@FXML 
	private Button btn_Create;
	@FXML 
	private TextField tbox_Username;
	
	@FXML
	private void handleCreateBtn(ActionEvent event) {
		
		try {
			// Create a FXML loader for loading the Edit Country FXML file.
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().
					//
					getResource("src/a2/resources/fxml docs/CreateUser.fxml"));     

			// Run the loader
			AnchorPane editRoot = (AnchorPane) fxmlLoader.load();          
			// Access the controller that was created by the FXML loader
			CreateUserController editController = fxmlLoader.
					<CreateUserController>getController();
			
			// Create a scene based on the loaded FXML scene graph
			Scene editScene = new Scene(editRoot, 
					Main.CREATE_PROFILE_WINDOW_WIDTH, 
					Main.CREATE_PROFILE_WINDOW_HEIGHT);
		    
			// Create a new stage (i.e., window) based on the edit scene
			Stage editStage = new Stage();
			editStage.setScene(editScene);
			editStage.setTitle(Main.CREATE_PROFILE_WINDOW_TITLE);
			
			// Make the stage a modal window.
			// This means that it must be closed before you 
			// can interact with any other window from this application.
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
	
	@FXML
	private void handleLogInBtn(ActionEvent event) {
		if (UserData.doesExist(tbox_Username.getText())) {
			try {
				// Create a FXML loader for loading the Edit Country FXML file.
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().
						getResource("resources/fxml docs/LevelSelection.fxml"));     

				// Run the loader
				AnchorPane editRoot = (AnchorPane) fxmlLoader.load();          
				// Access the controller that was created by the FXML loader
				LevelSelectController editController = fxmlLoader.
						<LevelSelectController>getController();
				
				// Create a scene based on the loaded FXML scene graph
				Scene editScene = new Scene(editRoot, 
						Main.CREATE_PROFILE_WINDOW_WIDTH, 
						Main.CREATE_PROFILE_WINDOW_HEIGHT);
			    
				// Create a new stage (i.e., window) based on the edit scene
				Stage editStage = new Stage();
				editStage.setScene(editScene);
				editStage.setTitle(Main.CREATE_PROFILE_WINDOW_TITLE);
				
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
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("User doesn't exist");
			alert.setContentText(null);

			alert.showAndWait();
		}
		
		
	}
}

