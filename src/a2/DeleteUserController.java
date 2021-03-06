package a2;

import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

/**
 * Controls the DeleteUser window.
 * @author Jensen Beard, Jamie Springett
 * @version 1.3 
 */
public class DeleteUserController {
	private static final String MAINPATH = "resources/fxml docs/MainMenu.fxml";
	
	@FXML
	private Button btn_Delete;
	@FXML
	private Button btn_Back;
	@FXML 
	private TextField txt_User;
	@FXML
	private AnchorPane DeleteRoot;
		
	/**
	 * Deletes the selected user.
	 * @param event button press
	 * @throws IOException If file can't be found throw exception.
	 */
	@FXML
	private void handleDelete(ActionEvent event) throws IOException {
		String name = txt_User.getText();
		if (UserData.doesExist(name)) {
			Alert confirm = new Alert(AlertType.CONFIRMATION);
			confirm.setTitle("Wait");
			confirm.setHeaderText("Are you sure?");
			confirm.setContentText(null);
			Optional<ButtonType> result = confirm.showAndWait();
			
			if (result.get() == ButtonType.OK) {
				UserData.deleteUser(name);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Error");
				alert.setHeaderText("User Deleted");
				alert.setContentText(null);
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Error");
				alert.setHeaderText("User Not Deleted");
				alert.setContentText(null);
				alert.showAndWait();
			}

		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("User Not Found");
			alert.setContentText(null);
			alert.showAndWait();
		}
	}
	/**
	 * Handles back button event.
	 * @param event Back button click.
	 * @throws IOException On resource selection. 
	 */
	@FXML
	private void handleBackBtn(ActionEvent event) throws IOException {
		AnchorPane window = FXMLLoader.load(getClass().
				getResource(MAINPATH));  
		DeleteRoot.getChildren().setAll(window);
	}
}