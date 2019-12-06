package cell;

import a2.MusicPlayer;
import a2.Player;
import a2.Vector2D;

/**
 * class that will hold shared methods between all door types.
 * @author tomwo
 *
 */

public abstract class Door extends Replaceable {
	
	
	
	/**
	 * Send the position to the cell superclass.
	 * 
	 * @param pos Holds the position of the Cell 
	 */
	public Door(Vector2D pos) {
		super(pos);
	}

	public boolean isWalkable() {
		return false;
	}
	
	public void doAction(Player ply) {
		if (meetsRequirement(ply)) {
			this.turnToGround();
			String audioFilePath =
					"src\\a2\\resources\\Sound bytes/Door unlock.wav";
			MusicPlayer doorClip = new MusicPlayer(audioFilePath);
			doorClip.play();
		}
	}
	
	/**
	 * Finds if the player meets the requirement for the door to open.
	 * @param ply The player on the level
	 * @return if the player has the item needed.
	 */
	
	public abstract boolean meetsRequirement(Player ply);
	
}
