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
	TableView leaderTable;

	
	public void display() {
		
		TableColumn<Profile, String> column1 = new TableColumn<>("Name");
        column1.setCellValueFactory(
        		new Callback<CellDataFeatures<Profile, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Profile, String> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                Profile profile = (Profile) p.getValue();
                return new SimpleStringProperty(profile.getName());
            }
         });
        leaderTable.getColumns().add(column1);
        leaderTable.getItems().add(Profile.getCurrentProfile());
        
        

//        TableColumn<String, Person> column2 = new TableColumn<>("Last Name");
//        column2.setCellValueFactory(new PropertyValueFactory<>("lastName"));
//		columnName.setCellValueFactory(new PropertyValueFactory<>("Name"));
//		columnTime.setCellValueFactory(new PropertyValueFactory<>("Time"));
		//leaderTable.getItems().a
	}
	
	public void leadBut1Press() {
		
	}
	public void leadBut2Press() {
			
	}
	public void leadBut3Press() {
		
	}

}
