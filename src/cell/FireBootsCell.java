package cell;

import a2.Item;
import a2.FireBoots;
import a2.Player;
import a2.Vector2D;
import a2.Player.Item;

/**
 * Fire boots cell that will be added to the players inventory
 * when cell is walked on.
 * @author tomwo
 *
 */

public class FireBootsCell extends Collectible {
	private static final String SPRITE = "a2/resources/stock photos/fireboots.png";
	
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
	
	public String getSprite() {
		return SPRITE;
	}
	
	public String cellName() {
		return "FireBootsCell";
	}
}
