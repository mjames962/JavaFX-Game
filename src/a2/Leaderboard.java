package a2;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.control.TableView;

public class Leaderboard {

	private ArrayList<LeaderboardEntry> entries = new ArrayList<>();
	private HashMap<String,LeaderboardColumn<?>> columns = new HashMap<>();
	

	public void addColumn(LeaderboardColumn<?> c) {
		columns.put(c.getName(), c);
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
	
	
	public void addEntry(LeaderboardEntry le) throws IllegalStateException {
		validateLeaderboardEntry(le);
		entries.add(le);
	}
	
	public void display(TableView<LeaderboardEntry> tv) {
		
		for (LeaderboardColumn<?> column : columns.values()) {
			tv.getColumns().add(column.getTableColumn());
		}
		for (LeaderboardEntry entry : entries) {
			tv.getItems().add(entry);
		}
	}
	
	public static void main(String[] args) {
		
		
		
	}
}
