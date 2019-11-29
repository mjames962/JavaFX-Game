package cell;

import a2.Item;
import a2.Player;
import a2.Vector2D;

/**
 * A ground cell that has an item that can be picked up on it.
 * @author george
 *
 */
public abstract class Collectible extends Replaceable {
	
	/**
	 * Creates a new collectible cell.
	 * @param pos The position of the collectible
	 */
	public Collectible(Vector2D pos) {
		super(pos);
	}
	@Override
	public void doAction(Player p) {
		p.pickupItem(createItem());
		turnToGround();
	}
	
	public abstract Item createItem();
	
}
