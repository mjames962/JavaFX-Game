package a2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import java.io.IOException;
import java.nio.file.*;


/**
 * @author Jensen
 *
 */
public class CreateUserController {
	@FXML
	private Button btn_CreateUser;
	@FXML
	private TextField tbox_NewUsername;
	
	@FXML
	private void handleCreateBtn(ActionEvent event) {
		System.out.println("Testing");
		String newUser = tbox_NewUsername.getText();
		if (UserData.doesExist(newUser)) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("User Already Exists");
			alert.setContentText(null);
			alert.showAndWait();
		} else {		
			try {
				System.out.println(newUser);

				Files.write(Paths.get("src/a2/resources/Users.txt"), 
						("\n" + newUser).getBytes(), StandardOpenOption.APPEND);
		        System.out.println("Done");
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
	}
}

