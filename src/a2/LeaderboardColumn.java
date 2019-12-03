package a2;

import java.util.ArrayList;

import javafx.beans.property.SimpleObjectProperty;
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
		setupFactory();
		setSortable(false);
		
	}
	public void setupFactory() {
		tcolumn.setCellValueFactory(
	    		new Callback<CellDataFeatures<LeaderboardEntry, T>, ObservableValue<T>>() {
	        public ObservableValue<T> call(CellDataFeatures<LeaderboardEntry, T> p) {
	            // p.getValue() returns the Person instance for a particular TableView row
	        	LeaderboardEntry le = (LeaderboardEntry) p.getValue();
	            return new SimpleObjectProperty(le.getData(name));
	        }
	     });
	}
	
	public void setSortable(boolean sort) {
		this.tcolumn.setSortable(sort);
	}
	
	public void setSortOrder(TableColumn.SortType sort) {
		this.tcolumn.setSortType(sort);
	}
	
	
	
	public boolean getSortable() {
		return this.tcolumn.isSortable();
	}
	
	
	
	public TableColumn<LeaderboardEntry,T> getTableColumn() {
		return tcolumn;
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
	

}
