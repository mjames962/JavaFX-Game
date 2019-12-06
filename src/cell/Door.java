package cell;

import a2.Player;
import a2.Vector2D;
import a2.Player.Item;

/**
 * Class that will hold shared methods between all door types.
 * @author Tom Wood
 * @version 1.3
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
	/**
	 * Method to deny enemies the ability to walk on the cell.
	 * @return 's false for whether Enemies can walk over it
	 */
	public boolean isWalkable() {
		return false;
	}
	
	/**
	 * Replaces Cell with ground when the player opens the door.
	 * @param ply reference to player
	 */
	public void doAction(Player ply) {
		if (meetsRequirement(ply)) {
			this.turnToGround();
		}
	}
	
	/**
	 * Returns the item the door Needs to open.
	 * @return item the door needs to open.
	 */
	
	public Item getRequires() {
		return requires;
	}
	
	/**
	 * Finds if the player meets the requirement for the door to open.
	 * @param ply The player on the level
	 * @return if the player has the item needed.
	 */
	public abstract boolean meetsRequirement(Player ply);
	
}
