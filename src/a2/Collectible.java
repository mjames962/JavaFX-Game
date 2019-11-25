package a2;



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
	
	abstract public Item createItem();
	
}
