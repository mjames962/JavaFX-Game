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
	
	/**
	 * Performs action when Create button is clicked.
	 * @param event Checks for the event occurring.
	 */
	@FXML
	private void handleCreateBtn(ActionEvent event) {
		initialiseWindow("resources/fxml docs/CreateUser.fxml");
	}
	
	/**
	 * Performs action when LogIn button is clicked.
	 * @param event Checks for the event occurring.
	 */
	@FXML
	private void handleLogInBtn(ActionEvent event) {
		//Checks if user already exists.
		if (UserData.doesExist(tbox_Username.getText())) {
			initialiseWindow("resources/fxml docs/LevelSelection.fxml");
		} else {
			//Displays alert pop-up box. 
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("User doesn't exist");
			alert.setContentText(null);

			alert.showAndWait();
		}	
	}
	
	/**
	 * Creates new window of format file
	 * @param file Holds the file path of the FXML file.
	 */
	private void initialiseWindow(String file) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().
					getResource(file));     

			AnchorPane windowRoot = (AnchorPane) fxmlLoader.load();          
									
			Scene windowScene = new Scene(windowRoot, 
					Main.CREATE_PROFILE_WINDOW_WIDTH, 
					Main.CREATE_PROFILE_WINDOW_HEIGHT);
		    
			Stage windowStage = new Stage();
			windowStage.setScene(windowScene);
			windowStage.setTitle(Main.CREATE_PROFILE_WINDOW_TITLE);
			

			// This is a modal window meaning that it must be closed before you 
			// can interact with any other window.
			windowStage.initModality(Modality.APPLICATION_MODAL);

			windowStage.showAndWait();
			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
}

