package a2;

import a2.Player.Item;

/**
 * Flipper cell that will be added to the players inventory when the cell
 * is walked on.
 * @author tomwo
 *
 */

public class Flippers extends Collectible {

    private Item item = Item.FLIPPERS;
	

    /**
	 * Send the position to the cell superclass.
	 * 
	 * @param pos Holds the position of the Cell 
	 */
    
	public Flippers(Vector2D pos) {
		super(pos);
	}
}
