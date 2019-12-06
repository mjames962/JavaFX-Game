package a2;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.sound.sampled.LineUnavailableException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * This Class Controls the actions for the GUI in the Main Menu Window.
 * 
 * @author Jensen, Mitch
 */
public class MainMenuController implements Initializable {
	@FXML
	private Button btn_LogIn;
	@FXML 
	private Button btn_Create;
	@FXML
	private Button btn_Delete;
	@FXML 
	private TextField tbox_Username;
	@FXML 
	private AnchorPane root;
	@FXML
	private ListView<String> lstView_Users;
	@FXML
	private CheckBox cb_ShowUsers;
	
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
			try {
				UserData.setCurrentUser(tbox_Username.getText());
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
	
	@FXML
	/**
	 * Performs action when Delete button is clicked.
	 * @param event checks for the event occurring.
	 */
	private void handleDeleteBtn(ActionEvent event) {
		initialiseWindow("resources/fxml docs/DeleteUser.fxml");
	}
	
	@FXML
	/**
	 * Performs action when Show Users button is toggled.
	 * @param event
	 */
	private void handleCBShowUsers(ActionEvent event) {
		if (lstView_Users.isVisible()) {
			lstView_Users.setVisible(false);
		} else {
			lstView_Users.setVisible(true);

		}
	}

	/**
	 * Creates new window of format file.
	 * @param file Holds the file path of the FXML file.
	 */
	private void initialiseWindow(String file) {
		try {

			AnchorPane window = FXMLLoader.load(getClass().
					getResource(file));  
			root.getChildren().setAll(window);
			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	@Override
	/**
	 * Initialises the main menu window.
	 * @param arg0 needed for JFX
	 * @param arg1 needed for JFX
	 */
	public void initialize(URL arg0, ResourceBundle arg1) {
		for (String user : UserData.readUsers()) {
			lstView_Users.getItems().add(user);
		}
	}
}

