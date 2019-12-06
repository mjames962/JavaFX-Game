package a2;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents an entry on a given leaderboard.
 * @author George Williams Walton
 * @version 1.7
 */
public class LeaderboardEntry {
	
	private Leaderboard leader = null;
	private HashMap<String, Object> entryData = new HashMap<>(); 
	
	/**
	 * Creates a new leaderboard entry.
	 * @param leader The leaderboard you wish to add to.
	 */
	public LeaderboardEntry(Leaderboard leader) {
		this.leader = leader;
	}
	/**
	 * Attempts to add data to a column.
	 * @param name name of column to add to
	 * @param data the data you wish to set
	 * @throws InvalidParameterException Data given 
	 * 							does not match the type of column.
	 */
	public void addData(String name, Object data) throws 
		InvalidParameterException {
		//Class<?> dataClass = data.getClass();
		LeaderboardColumn<?> column = leader.getColumn(name);
		Class<?> columnClass = column.getMyType();
		if (columnClass.isInstance(data)) {
			entryData.put(column.getName(), data);
		} else {
			throw new InvalidParameterException(""
					+ "Attempt to add data with a different type to the "
					+ "column\n" + "Want:" + columnClass + "|" + "Provided:" + 
						data.getClass());
		}
	}
	/**
	 * Returns true if data has a column entry.
	 * @param column the column of the leaderboard
	 * @return gives the requested data stored at the 
	 * 								specified point in the leaderboard
	 */
	public boolean hasColumn(LeaderboardColumn<?> column) {
		return entryData.containsKey(column.getName());
	}
	
	/**
	 * Gets data of column for this entry.
	 * @param name Name of the column you wish to retrieve
	 * @return The data stored in the column
	 * @throws InvalidParameterException If column name does not exist
	 */
	public Object getData(String name) throws InvalidParameterException {
		if (entryData.containsKey(name)) {
			return entryData.get(name);
		} else {
			throw new InvalidParameterException(""
					+ "Attempt to find a column with no data set! " + name);
		}
		
	}

}
