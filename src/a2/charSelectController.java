package a2;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class charSelectController implements Initializable{
	//to use replace file path with 
	private static String fileLocation = "a2/resources/stock photos/Player1.png";
	private String fileSubstring;
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
	private void handleAcceptBtn(Event event) throws IOException {
		fileSubstring = cmb_CharSelect.getValue();
		if (fileSubstring != null) {
			fileLocation = "a2/resources/stock photos/" 
							+ fileSubstring;
			Alert news = new Alert(AlertType.INFORMATION);
			news.setTitle("Success");
			news.setHeaderText("User Created");
			news.setContentText(null);
			news.showAndWait();
			
			AnchorPane window = FXMLLoader.load(getClass().
					getResource("resources/fxml docs/LevelSelection.fxml"));  
			charSelection.getChildren().setAll(window);
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("If you do not pick an option the "
					+ "default character will be selected");
			alert.setContentText(null);
			alert.showAndWait();
		}
		
	}
	
	@FXML
	private void handleBackBtn(Event event) throws IOException {
		AnchorPane window = FXMLLoader.load(getClass().
				getResource("resources/fxml docs/LevelSelection.fxml"));  
		charSelection.getChildren().setAll(window);
	}
	
	@FXML
	private void handleCmbSelect(Event event) throws IOException {
		ImageView image = new ImageView("a2/resources/stock photos/" 
						+ cmb_CharSelect.getValue());
		lbl_Preview.setGraphic(image);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		File folder = new File("src/a2/resources/stock photos");
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
			String fileName = file.getName();
			if (file.isFile()) {
				if (fileName.substring(0,6).equals("Player")) {			
					cmb_CharSelect.getItems().add(fileName);
				}  	
		    }
		}
	}
}
