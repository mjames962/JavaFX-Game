package a2;

import a2.Player.Item;

/**
 * The key item that the player can pick up that will
 * allow them to open coloured doors.
 * @author tomwo
 *
 */

public class Key extends Collectible {

	private Item item;
	
	/**
	 * Send the position to the cell superclass and
	 * specifies the colour of the key.
	 * 
	 * @param pos Holds the position of the Cell 
	 * @param key The colour of this key
	 */
	public Key(Vector2D pos, Item key) {
		super(pos);
		this.item = key;
	}
	
	
	/**
	 * Returns the colour of the key.
	 * 
	 * @return the colour of the key
	 */
	public Item getItem() {
		return item;
	}
}
