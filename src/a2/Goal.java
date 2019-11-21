package a2;


/**
 * Goal cell that will end the level when walked on
 * by the player.
 * @author tomwo
 *
 */

public class Goal extends Specialised {
	
	/**
	 * Send the position to the cell superclass.
	 * 
	 * @param pos Holds the position of the Cell 
	 */

	public Goal(Vector2D pos) {
		super(pos);
	}
	
	
	/**
	 * This will end the level and timer and if a record time has been reached 
	 * it will update the players save file and leaderboard with the new 
	 * time before creating the next level.
	 * @param ply the player currently on the level
	 * @param user The user profile currently in use
	 * @param lv The level the user is currently on
	 */
	
	public void doAction(Player ply, Profile user, Level lv) {
		Level.readFile("level2.txt");
		long finishTime = Timer.checkTimeElapsed();
		if (user.isHighestTime(lv.getLevelNo(), finishTime)) {
			user.setBestTime(lv.getLevelNo(), finishTime);
		}
		
		//Level call for method to create a new level.
		Timer.stop();
	}
}
