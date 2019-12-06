package a2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;


/**
 * Controls the CreateUser window.
 * @author Jensen Beard, Jamie Springett
 *@version 1.4
 */

public class CreateUserController {
	@FXML
	private Button btn_CreateUser;
	@FXML
	private TextField tbox_NewUsername;
	@FXML
	private AnchorPane createScene;
	@FXML
	private AnchorPane window;
	
	/**
	 * Handles click event for create button.
	 * @param event Checks for an event occurring.
	 * @throws IOException .
	 */
	@FXML
	private void handleCreateBtn(ActionEvent event) throws IOException {
		String newUser = tbox_NewUsername.getText();
		
		
		if (UserData.doesExist(newUser)) {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("User Already Exists");
			alert.setContentText(null);
			alert.showAndWait();
			
		} else {
			
			
			try {
				UserData.createUser(newUser);
				
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(-1);
			}
			Alert news = new Alert(AlertType.INFORMATION);
			news.setTitle("Success");
			news.setHeaderText("User Created");
			news.setContentText(null);
			news.showAndWait();
			
			AnchorPane window = FXMLLoader.load(getClass().
					getResource("resources/fxml docs/MainMenu.fxml"));  
			createScene.getChildren().setAll(window);

			
		}	
	}
	
	/**
	 * Handles back button event.
	 * @param event Checks for an event occurring.
	 * @throws IOException .
	 */
	@FXML
	private void handleBackBtn(ActionEvent event) throws IOException {
		AnchorPane window = FXMLLoader.load(getClass().
				getResource("resources/fxml docs/MainMenu.fxml"));  
		createScene.getChildren().setAll(window);
	}
}

