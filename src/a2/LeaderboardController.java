package a2;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.adapter.JavaBeanStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * Controller for the leaderboards window.
 * @author George Williams Walton, Tom Wood
 * @version 1.8
 */
public class LeaderboardController {
	
	@FXML 
	private Button leadBut1;
	@FXML 
	private Button leadBut2;
	@FXML 
	private Button leadBut3;
	@FXML 
	private ChoiceBox levelSelector;
	@FXML 
	private TableView<LeaderboardEntry> leaderTable;

	
	/**
	 * Displays the leaderboard.
	 */
	public void display() {
		
		Leaderboard lead = UserData.readLeaderboard(1);
		if (lead != null) {
			lead.display(leaderTable);
		}
        
	}
	/**
	 * Allows for pressing buttons.
	 */
	public void leadBut1Press() {
		
	}
	/**
	 * Allows for pressing buttons.
	 */
	public void leadBut2Press() {
			
	}
	/**
	 * Allows for pressing buttons.
	 */
	public void leadBut3Press() {
		
	}

}
