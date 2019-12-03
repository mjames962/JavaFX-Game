package a2;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class LeaderboardColumn<T> {
	
	private final Class<T> type;
	private final String name;
	private final TableColumn<LeaderboardEntry,T> tcolumn;
	
	public LeaderboardColumn(Class<T> type,String name) {
		this.type = type;
		this.name = name;
		this.tcolumn = new TableColumn<>(name);
       
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<T> getAllData() {
		return null;
	}
	
	public Class<T> getMyType() {
		return type;
	}
	
	public ArrayList<Integer> orderAscending() {
		return null;
		
	}
	
	public ArrayList<LeaderboardEntry> orderDescending() {
		return null;
		
	}

}
