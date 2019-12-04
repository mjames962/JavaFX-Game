package cell;

import a2.Level;
import a2.Player;
import a2.Profile;
import a2.Timer;
import a2.UserData;
import a2.Vector2D;

/**
 * Goal cell that will end the level when walked on
 * by the player.
 * @author tomwo
 *
 */

public class Goal extends Cell {
	protected static final String SPRITE = "a2/resources/stock photos/Goal_Cell.png";
	
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
		lv.readFile("src/a2/resources/file formats/testFileFormat2.txt");
		long finishTime = Timer.checkTimeElapsed();
		if (user.isBestTime(lv.getLevelNo(), finishTime)) {
			user.setBestTime(lv.getLevelNo(), finishTime);
		}
		
		
		Timer.stop();
	}
	@Override
	public void doAction(Player ply) {
		long finishTime = Timer.checkTimeElapsed();
		int levelNum = Level.getCurrentLevel().getLevelNumber();
		UserData.getCurrentUser().setBestTime(levelNum, finishTime);
		UserData.getCurrentUser().setHighestLevel(levelNum);
		Level.getCurrentLevel().loadNextLevel();

	}
	
	public String cellName() {
		return "Goal";
	}
	
	public String getSprite() {
		return SPRITE;
	}
	
	public char getChar() {
		return 'X';
	}
}
