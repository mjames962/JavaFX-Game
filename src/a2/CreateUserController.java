package a2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import java.io.IOException;
import java.nio.file.*;


/**
 * Controls the CreateUser window.
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
				Files.write(Paths.get("src/a2/resources/User files/Users.txt"), 
						("\n" + newUser).getBytes(), StandardOpenOption.APPEND);
				Stage stage = (Stage) btn_CreateUser.getScene().getWindow();
			    stage.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
	}
}

