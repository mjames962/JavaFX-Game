package a2;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
 * @author Jensen Beard, Mitch Thomas
 * @version 1.6
 */
public class MainMenuController implements Initializable {
	private static final String DELETEPATH = "resources/fxml docs/DeleteUser.fxml";
	private static final String LEVELPATH = "resources/fxml docs/LevelSelection.fxml";
	private static final String CREATEPATH = "resources/fxml docs/CreateUser.fxml";
	
	@FXML
	private Button btn_LogIn;
	@FXML 
	private Button btn_Create;
	@FXML
	private Button btn_Delete;
	@FXML
	private Button btn_Quit;
	@FXML 
	private TextField tbox_Username;
	@FXML 
	private AnchorPane root;
	@FXML
	private ListView<String> lstView_Users;
	@FXML
	private CheckBox cb_ShowUsers;
		
	/**
	 * Initialises the main menu window.
	 * @param arg0 needed for JFX
	 * @param arg1 needed for JFX
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		for (String user : UserData.readUsers()) {
			lstView_Users.getItems().add(user);
		}
	}
	
	/**
	 * Performs action when Create button is clicked.
	 * @param event Checks for the event occurring.
	 */
	@FXML
	private void handleCreateBtn(ActionEvent event) {
		initialiseWindow(CREATEPATH);
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
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			initialiseWindow(LEVELPATH);
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
	 * Performs action when Delete button is clicked.
	 * @param event checks for the event occurring.
	 */
    @FXML
	private void handleDeleteBtn(ActionEvent event) {
		initialiseWindow(DELETEPATH);
	}
	

	/**
	 * Performs action when Show Users button is toggled.
	 * @param event Contains information about the action being passed
     *              to the method.
	 */
    @FXML
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
			//Erases the current children replacing it with 
			//those found within the fxml, reduces lag.
			AnchorPane window = FXMLLoader.load(getClass().
					getResource(file));  
			root.getChildren().setAll(window);
			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	/**
	 * Quits program.
	 * @param event Quit button click.
	 */
	@FXML
	private void handleQuitBtn(ActionEvent event) {
		System.exit(0);
	}
}