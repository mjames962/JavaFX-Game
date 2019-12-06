package cell;

import a2.Flippers;
import a2.Item;
import a2.Vector2D;

/**
 * Flipper cell that will be added to the players inventory when the cell
 * is walked on.
 * @author Tom Wood
 * @version 1.1
 */

public class FlippersCell extends Collectible {
	
	protected static final String SPRITE = 
			"a2/resources/stock photos/flippers.png";
	
    /**
	 * Send the position to the cell superclass.
	 * 
	 * @param pos Holds the position of the Cell 
	 */
    
	public FlippersCell(Vector2D pos) {
		super(pos);
	}
	/**
	 * Creates a new Flipper Item and places it in the player inventory.
	 * @return creates and returns the item Flippers
	 */
	public Item createItem() {
		return new Flippers();
	}
	
	/**
	 * Getter for the FlippersCell Sprite.
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
		return 'w';
	}
}
