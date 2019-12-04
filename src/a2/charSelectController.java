package a2;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class charSelectController implements Initializable{
	//to use replace file path with 
	private static String fileLocation = "a2/resources/stock photos/Player1.png";
	
	@FXML
	private AnchorPane charSelection;
	
	@FXML
	private ComboBox<String> cmb_CharSelect;
	
	@FXML
	private Button btn_Accept;
	
	@FXML
	private Button btn_Back;
	
	@FXML
	private Label lbl_Preview;
	
	public static String getCharSprite() {
		return fileLocation;
	}
	
	@FXML
	private void handleAcceptBtn(Event event) {
		
	}
	
	@FXML
	private void handleBackBtn(Event event) throws IOException {
		AnchorPane window = FXMLLoader.load(getClass().
				getResource("resources/fxml docs/LevelSelection.fxml"));  
		charSelection.getChildren().setAll(window);
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		File folder = new File("src/a2/resources/stock photos/PlayerSprites");
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
			String fileName = file.getName();
			if (file.isFile()) {
		    	cmb_CharSelect.getItems().add(fileName);
		    }
		}
	}
}
