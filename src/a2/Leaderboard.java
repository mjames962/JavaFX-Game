package a2;

import java.util.ArrayList;
import java.util.HashMap;

public class Leaderboard {

	private ArrayList<LeaderboardEntry> entries = new ArrayList<>();
	private HashMap<String,LeaderboardColumn> columns = new HashMap<>();
	

	public void addColumn(LeaderboardColumn<?> c) {
		columns.put(c.getName(), c);
		
	}
	
	public HashMap<String,LeaderboardColumn> getColumns() {
		return columns;
	}
	
	public LeaderboardEntry getEntry(int index) {
		return entries.get(index);
	}
	
	
	
	public void restrictEntries(int restrict) {
		
	}
	
	public void addEntry(LeaderboardEntry le) {
		entries.add(le);
	}
	
	public void display() {
		
	}
	
	public static void main(String[] args) {
		Leaderboard lead = new Leaderboard();
		LeaderboardColumn<Integer> nucolumn = new LeaderboardColumn<>(Integer.class,"Score");
		lead.addColumn(nucolumn);
		LeaderboardEntry nuentry = new LeaderboardEntry(lead);
		nuentry.addData("Score", -9999);
		lead.addEntry(nuentry);
		System.out.println(lead.getEntry(0).getData("Score"));
		
		
	}
}
