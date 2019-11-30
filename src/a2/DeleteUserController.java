package a2;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class DeleteUserController {
	@FXML
	private Button btn_Delete;
	@FXML 
	private TextField txt_User;
	@FXML
	private AnchorPane DeleteRoot;

		// TODO Auto-generated method stub
		
	/**
	 * Deletes the selected user.
	 * @param event button press
	 * @throws IOException 
	 */
	@FXML
	private void handleDelete(ActionEvent event) throws IOException {
		String name = txt_User.getText();
		if (UserData.doesExist(name)) {
			UserData.deleteUser(name);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Error");
			alert.setHeaderText("User Deleted");
			alert.setContentText(null);
			alert.showAndWait();
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
	 * @param event Checks for an event occurring.
	 * @throws IOException .
	 */
	@FXML
	private void handleBackBtn(ActionEvent event) throws IOException {
		AnchorPane window = FXMLLoader.load(getClass().
				getResource("resources/fxml docs/MainMenu.fxml"));  
		DeleteRoot.getChildren().setAll(window);
	}
}
