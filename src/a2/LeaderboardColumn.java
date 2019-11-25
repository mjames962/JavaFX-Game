package a2;

import java.util.ArrayList;

public class LeaderboardColumn<T> {
	
	private final Class<T> type;
	private final String name;
	
	public LeaderboardColumn(Class<T> type,String name) {
		this.type = type;
		this.name = name;
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
