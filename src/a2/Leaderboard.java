package a2;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * A representation of a leaderboard, with the ability to display 
 * 												to a javafx TableView.
 * @author george
 * @version 2.3
 */
public class Leaderboard {

	private ArrayList<LeaderboardEntry> entries = new ArrayList<>();
	private HashMap<String, LeaderboardColumn<?>> columns = new HashMap<>();
	private ArrayList<LeaderboardColumn<?>> orderedColumns = new ArrayList<>();
	private TableColumn.SortType sortType = TableColumn.SortType.DESCENDING;
	private LeaderboardColumn<?> sortedColumn;
	
	/**
	 * Adds a LeaderboardColumn to the leaderboard.
	 * @param c LeaderboardColumn to add
	 */
	public void addColumn(LeaderboardColumn<?> c) {
		columns.put(c.getName(), c);
		orderedColumns.add(c);
	}
	
	/**
	 * Gets columns as a name,column hashmap.
	 * @return columns
	 */
	public HashMap<String, LeaderboardColumn<?>> getColumns() {
		return columns;
	}
	
	/**
	 * Gets leaderboard entry at index. 
	 * Indexes are based on the order you put the entries in.
	 * @param index Index of entry you wish to retrieve
	 * @return LeaderboardEntry requested
	 */
	public LeaderboardEntry getEntry(int index) {
		
		return entries.get(index);
	}
	
	/**
	 * Checks all columns exist in an entry before adding it to the leaderboard.
	 * @param le the LeaderboardEntry to check.
	 * @throws IllegalStateException Entry is missing a column value
	 */
	private void checkAllColumnsPresent(LeaderboardEntry le) 
			throws IllegalStateException {
		for (LeaderboardColumn<?> column : columns.values()) {
			if (!le.hasColumn(column)) {
				throw new IllegalStateException(
						"Entry is missing a column value! " + column.getName()
						);
			}
		}
	}
	/**
	 * Sets sort type of the initially sorted column.
	 * @param sort ASCENDING/DESCENDING
	 */
	public void setSortType(TableColumn.SortType sort) {
		this.sortType = sort;
	}
	
	/**
	 * Gets the column with given column name.
	 * @param columnName Name of column
	 * @return The requested LeaderboardColumn
	 * @throws InvalidParameterException If a column with the given 
	 * 												name does not exist
	 */
	public LeaderboardColumn<?> getColumn(String columnName) 
			throws InvalidParameterException {
		LeaderboardColumn<?> column = columns.get(columnName);
		if (column == null) {
			throw new InvalidParameterException("Column " + 
				columnName + " does not exist!");
		}
		
		return column;
	}
	
	/**
	 * Sets an initially sorted column, with default DESCENDING order.
	 * @param lcname The name of the column to be sorted on load
	 */
	public void setSortedColumn(String lcname) {
		sortedColumn = getColumn(lcname);
		
	}
	/**
	 * Gets the sort type of the initially sorted column.
	 * @return The sort type ASCENDING/DESCENDING 
	 */
	public TableColumn.SortType getSortType() {
		return this.sortType;
	}
	
	/**
	 * Adds a LeaderboardEntry to the leaderboard.
	 * @param le LeaderboardEntry to add.
	 */
	public void addEntry(LeaderboardEntry le) {
		checkAllColumnsPresent(le);
		entries.add(le);
	}
	/**
	 * Attempts to display leaderboard onto a given javafx TableView.
	 * @param tv Given TableView
	 */
	public void display(TableView<LeaderboardEntry> tv) {
		
		for (LeaderboardColumn<?> column : orderedColumns) {
			tv.getColumns().add(column.getTableColumn());
			if (column.equals(sortedColumn)) {
				column.setSortable(true);
				tv.getSortOrder().add(column.getTableColumn());
				
				column.setSortOrder(getSortType());
				
			}
		}
		for (LeaderboardEntry entry : entries) {
			tv.getItems().add(entry);
		}
		tv.sort();
	}

}
