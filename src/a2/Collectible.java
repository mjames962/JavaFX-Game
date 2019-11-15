package a2;

/**
 * A ground cell that has an item that can be picked up on it.
 * @author george
 *
 */
public abstract class Collectible extends Replaceable {

	
	private Enum item; //TODO Make this an item
	
	/**
	 * Creates a new collectible cell.
	 * @param pos The position of the collectible
	 */
	public Collectible(Vector2D pos) {
		super(pos);
	}
	
	/**
	 * Gets the item of the collectible.
	 * @return the item enum
	 */
	public Enum getItem() {
		return item;
	}

	
}
