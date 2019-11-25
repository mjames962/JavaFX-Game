package a2;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;

public class LeaderboardEntry {
	
	private Leaderboard leader = null;
	private HashMap<String,Object> entryData = new HashMap<>(); 
	
	public LeaderboardEntry(Leaderboard leader) {
		this.leader = leader;
	}
	
	public void addData(String name, Object data) throws InvalidParameterException {
		//Class<?> dataClass = data.getClass();
		LeaderboardColumn column = leader.getColumns().get(name);
		if (column == null) {
			throw new NullPointerException("Column does not exist!");
		}
		Class<?> columnClass = column.getMyType();
		if (columnClass.isInstance(data)) {
			entryData.put(column.getName(), data);
		} else {
			throw new InvalidParameterException(""
					+ "Attempt to add data with a different type to the column\n" + 
					"Want:" + columnClass + "|" + "Provided:" + data.getClass());
		}
	}
	
	public void addDatas(HashMap<String,Object> data) {
		for (String name : data.keySet()) {
			addData(name,data.get(name));
		}
	}
	
	public Object getData(String name) throws InvalidParameterException {
		if (entryData.containsKey(name)) {
			return entryData.get(name);
		} else {
			throw new InvalidParameterException(""
					+ "Attempt to a non existent column! " + name);
		}
		
	}

}
