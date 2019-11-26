package cell;

import a2.Flippers;
import a2.Item;
import a2.Vector2D;

/**
 * Flipper cell that will be added to the players inventory when the cell
 * is walked on.
 * @author tomwo
 *
 */

public class FlippersCell extends Collectible {
	
    /**
	 * Send the position to the cell superclass.
	 * 
	 * @param pos Holds the position of the Cell 
	 */
    
	public FlippersCell(Vector2D pos) {
		super(pos);
	}
	
	public Item createItem() {
		return new Flippers();
	}
	
	public String cellName() {
		return "FlippersCell";
	}
}
