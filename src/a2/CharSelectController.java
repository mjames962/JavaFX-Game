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

/**
 * Allows the user to select and image sprite. 
 * @author Jensen
 * @version 1.2
 * 
 */
public class CharSelectController implements Initializable {
	private static final int FILEDELIMETER = 6;
	private static String fileLocation =
			"a2/resources/stock photos/Player1.png";
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
	
	/**
	 * Returns fileLocation.
	 * @return fileLocation the location of the chosen image sprite
	 */
	public static String getCharSprite() {
		return fileLocation;
	}
	
	/**
	 * Sets the sprite the user has selected in the combobox.
	 * @param event Accept button click 
	 * @throws IOException On resource selection.
	 */
	@FXML
	private void handleAcceptBtn(Event event) throws IOException {
		fileSubstring = cmb_CharSelect.getValue();
		if (fileSubstring != null) {
			fileLocation = "a2/resources/stock photos/" 
							+ fileSubstring;
			Alert news = new Alert(AlertType.INFORMATION);
			news.setTitle("Success");
			news.setHeaderText("Player selected");
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
	
	/**
	 * Takes the user back to the Level Select window.
	 * @param event Back button click.
	 * @throws IOException On resource selection.
	 */
	@FXML
	private void handleBackBtn(Event event) throws IOException {
		AnchorPane window = FXMLLoader.load(getClass().
				getResource("resources/fxml docs/LevelSelection.fxml"));  
		charSelection.getChildren().setAll(window);
	}
	
	/**
	 * Sets lbl_Preview to the selected image sprite.
	 * @param event combobox selection.
	 * @throws IOException On resource selection.
	 */
	@FXML
	private void handleCmbSelect(Event event) throws IOException {
		ImageView image = new ImageView("a2/resources/stock photos/" 
						+ cmb_CharSelect.getValue());
		lbl_Preview.setGraphic(image);
	}
	
	/**
	 * Adds file names to the combobox on level load.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		File folder = new File("src/a2/resources/stock photos");
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
			String fileName = file.getName();
			if (file.isFile()) {
				if (fileName.substring(0, FILEDELIMETER)
						.equals("Player")) {			
					cmb_CharSelect.getItems().add(fileName);
				}  	
		    }
		}
	}
}
