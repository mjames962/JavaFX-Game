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
		
		Leaderboard lead = new Leaderboard();
		LeaderboardColumn<String> nucolumn = 
				new LeaderboardColumn<>(String.class, "Name");
		LeaderboardColumn<Integer> nucolumn2 = 
				new LeaderboardColumn<>(Integer.class, "Score");
		LeaderboardColumn<Integer> nucolumn3 = 
				new LeaderboardColumn<>(Integer.class, "Third");
		
		lead.addColumn(nucolumn);
		lead.addColumn(nucolumn2);
		lead.addColumn(nucolumn3);
		lead.setSortedColumn("Score");
		LeaderboardEntry nuentry = new LeaderboardEntry(lead);
		nuentry.addData("Name", "Me");
		nuentry.addData("Score", -9999);
		nuentry.addData("Third", -5);
		LeaderboardEntry nuentry2 = new LeaderboardEntry(lead);
		nuentry2.addData("Name", "You");
		nuentry2.addData("Score", 1000);
		nuentry2.addData("Third",-5);
		LeaderboardEntry nuentry3 = new LeaderboardEntry(lead);
		nuentry3.addData("Name", "Who");
		nuentry3.addData("Score", 0);
		nuentry3.addData("Third", -5);
		lead.addEntry(nuentry);
		lead.addEntry(nuentry2);
		lead.addEntry(nuentry3);
		//System.out.println(lead.getEntry(0).getData("Score"));
		lead.display(leaderTable);
        
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
