package cell;

import a2.Item;
import a2.FireBoots;
import a2.Vector2D;

/**
 * Fire boots cell that will be added to the players inventory
 * when cell is walked on.
 * @author Tom Wood
 * @version 1.2
 */

public class FireBootsCell extends Collectible {
	private static final String SPRITE = 
			"a2/resources/stock photos/fireboots.png";
	
	/**
	 * Send the position to the cell superclass.
	 * 
	 * @param pos Holds the position of the Cell 
	 */
	public FireBootsCell(Vector2D pos) {
		super(pos);
	}
	
	/**
	 * Creates Fire Boots Item and attributes it to the player.
	 * @return the FireBoots item
	 */
	public Item createItem() {
		return new FireBoots();
	}
	
	/**
	 * Getter for the FireBootsCell Sprite.
	 * @return gives the sprite
	 */
	public String getSprite() {
		return SPRITE;
	}
	
	/**
	 * Getter for unique identifying character.
	 * @return gives the unique identifier
	 */
	public char getChar() {
		return 'f';
	}
}
