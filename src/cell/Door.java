package cell;

import a2.Player;
import a2.Vector2D;
import a2.Player.Item;

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
			System.out.println("wwoooasdas");
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
