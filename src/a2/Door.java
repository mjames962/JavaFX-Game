package a2;

import a2.Player.Item;

/**
 * class that will hold shared methods between all door types.
 * @author tomwo
 *
 */

public abstract class Door extends Replaceable {
	
	private Item requires;
	
	/**
	 * Send the position to the cell superclass.
	 * 
	 * @param pos Holds the position of the Cell 
	 */
	public Door(Vector2D pos) {
		super(pos);
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
	
	public boolean meetsRequirement(Player ply) {
		return ply.hasItem(requires);
	}
	
}
