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

public class LeaderboardController {
	
	@FXML 
	Button leadBut1;
	@FXML 
	Button leadBut2;
	@FXML 
	Button leadBut3;
	@FXML 
	ChoiceBox levelSelector;
	@FXML 
	TableView<LeaderboardEntry> leaderTable;

	
	
	public void display() {
		
		Leaderboard lead = UserData.readLeaderboard(1);
		if (lead != null) {
			lead.display(leaderTable);
		}
        
	}
	
	public void leadBut1Press() {
		
	}
	public void leadBut2Press() {
			
	}
	public void leadBut3Press() {
		
	}

}
