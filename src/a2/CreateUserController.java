package a2;

import javafx.event.ActionEvent; 
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


/**
 * Controls the CreateUser window.
 * @author Jensen Beard, Jamie Springett
 * @version 1.4
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
	 * When create user button pressed if valid user create the user and file.
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
			
		} else if (!UserData.validateName(newUser)) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Invalid user name, Please try again");
			alert.setContentText("Usernames must be less than 25 characters "
					+ "\nand only contain letters and numbers");
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
	 * Returns the user to the main menu.
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

