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
	 */
	
	public void doAction() {
		Level.readFile("level2.txt");
		long finishTime = Timer.checkTimeElapsed();
		//Add time to user profile
		//Level call for method to create a new level.
		Timer.stop();
	}
}
