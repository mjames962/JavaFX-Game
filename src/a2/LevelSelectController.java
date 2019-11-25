package a2;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

/**
 * .
 * @author Darius Thomas
 *
 */
public class LevelSelectController {
	
	@FXML
	private Button btn_LoadLevel;
	
	@FXML
	private ComboBox<String> cmb_LevelSelect;
	
	@FXML
	private Canvas levelCanvas;
	
	@FXML
	private void handleLoadLevelBtn(ActionEvent event) {
		Stage mainStage = null;
		//Scene levelScene = new Scene(LevelDisplay.start(mainStage));
	}
	
}
