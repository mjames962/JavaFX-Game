package a2;

import java.util.ArrayList;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

/**
 * Represents a possible column on a leaderboard, restricted to a type.
 * @author George Williams Walton, Tom Wood
 * @version 1.5 
 * @param <T> The type of the data in the column.
 */
public class LeaderboardColumn<T> {
	
	private final Class<T> type;
	private final String name;
	private final TableColumn<LeaderboardEntry, T> tcolumn;
	
	/**
	 * Creates a new column, to be added to a Leaderboard.
	 * @param type The class of the data to be stored
	 * @param name The name of the column
	 */
	public LeaderboardColumn(Class<T> type, String name) {
		this.type = type;
		this.name = name;
		this.tcolumn = new TableColumn<>(name);
		setupFactory();
		setSortable(false);
		
	}
	
	/**
	 * Sets up the cellvaluefactory for TableColumn.
	 */
	private void setupFactory() {
		tcolumn.setCellValueFactory(
	    		new Callback<CellDataFeatures<LeaderboardEntry, T>,
	    							ObservableValue<T>>() {
	    			public ObservableValue<T> call(CellDataFeatures<
		        		LeaderboardEntry, T> p) {
	    				LeaderboardEntry le = (LeaderboardEntry) p.getValue();
	    				return new SimpleObjectProperty(le.getData(name));
	    			}
	    		});
	}
	
	/**
	 * Sets if this column is sortable or not in tableview, defaults to false.
	 * @param sort boolean
	 */
	public void setSortable(boolean sort) {
		this.tcolumn.setSortable(sort);
	}
	
	/**
	 * Sets the columns default sort order. Does not control how user can sort.
	 * @param sort ASCENDING/DESCENDING
	 */
	public void setSortOrder(TableColumn.SortType sort) {
		this.tcolumn.setSortType(sort);
	}
	
	
	/**
	 * Gets if column is sortable or not. Defaults to false.
	 * @return boolean
	 */
	public boolean getSortable() {
		return this.tcolumn.isSortable();
	}
	
	
	/**
	 * Gets javafx TableColumn representation. Should not be needed in most
	 * cases.
	 * @return tcolumn is the table column being added to the table
	 */
	public TableColumn<LeaderboardEntry, T> getTableColumn() {
		return tcolumn;
	}
	
	/**
	 * Gets name of column.
	 * @return Column name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * Returns type of the column as a Class object.
	 * @return the type of class.
	 */
	public Class<T> getMyType() {
		return type;
	}
	

}
