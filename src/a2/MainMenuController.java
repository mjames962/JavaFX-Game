package a2;


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
 * @author Jensen, Mitch
 */
public class MainMenuController implements Initializable{
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
			new Profile(UserData.getUserFile(tbox_Username.getText()));
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
	private void handleDeleteBtn(ActionEvent event) {
		initialiseWindow("resources/fxml docs/DeleteUser.fxml");
	}
	
	@FXML
	private void handleCBShowUsers(ActionEvent event) {
		if (lstView_Users.isVisible()) {
			lstView_Users.setVisible(false);
		} else {
			lstView_Users.setVisible(true);
			
		}
		
	}
	
	/**
	 * .
	 * Creates new window of format file
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
	public void initialize(URL arg0, ResourceBundle arg1) {
		for(String user : UserData.readUsers()) {
			lstView_Users.getItems().add(user);
		}
	}
}

