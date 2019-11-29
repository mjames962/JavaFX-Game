package a2;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
	 */
	@FXML
	private void HandleDelete(ActionEvent event) {
		String name = txt_User.getText();
		if (UserData.doesExist(name)) {
			System.out.print("True");
		} else {
			System.out.print("False");
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

