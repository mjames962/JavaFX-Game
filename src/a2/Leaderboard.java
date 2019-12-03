package a2;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Leaderboard {

	private ArrayList<LeaderboardEntry> entries = new ArrayList<>();
	private HashMap<String,LeaderboardColumn<?>> columns = new HashMap<>();
	private ArrayList<LeaderboardColumn<?>> orderedColumns = new ArrayList<>();
	private TableColumn.SortType sortType = TableColumn.SortType.DESCENDING;
	private LeaderboardColumn<?> sortedColumn;
	

	public void addColumn(LeaderboardColumn<?> c) {
		columns.put(c.getName(), c);
		orderedColumns.add(c);
	}
	
	public HashMap<String,LeaderboardColumn<?>> getColumns() {
		return columns;
	}
	
	public LeaderboardEntry getEntry(int index) {
		
		return entries.get(index);
	}
	
	public boolean validateLeaderboardEntry(LeaderboardEntry le) {
		for (LeaderboardColumn<?> column : columns.values()) {
			if (!le.hasColumn(column)) {
				throw new IllegalStateException(
						"Entry is missing a column value! " + column.getName()
						);
			}
		}
		return true;
	}
	
	public void setSortType(TableColumn.SortType sort) {
		this.sortType = sort;
	}
	
	public LeaderboardColumn<?> getColumn(String columnName) {
		LeaderboardColumn<?> column = columns.get(columnName);
		if (column == null) {
			throw new InvalidParameterException("Column " + columnName + " does not exist!");
		}
		
		return column;
	}
	
	public void setSortedColumn(String lcname) {
		sortedColumn = getColumn(lcname);
		
	}
	
	public TableColumn.SortType getSortType() {
		return this.sortType;
	}
	
	public void addEntry(LeaderboardEntry le) throws IllegalStateException {
		validateLeaderboardEntry(le);
		entries.add(le);
	}
	
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
	
	public static void main(String[] args) {
		
		
		
	}
}
