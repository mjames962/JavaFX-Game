package a2;

import a2.Player.Item;

/**
 * Fire boots cell that will be added to the players inventory
 * when cell is walked on.
 * @author tomwo
 *
 */

public class FireBootsCell extends Collectible {
	
	/**
	 * Send the position to the cell superclass.
	 * 
	 * @param pos Holds the position of the Cell 
	 */
	public FireBootsCell(Vector2D pos) {
		super(pos);
	}
	
	public Item createItem() {
		return new FireBoots();
	}
}
