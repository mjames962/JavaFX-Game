package cell;

import a2.GreenKey;
import a2.Item;
import a2.Vector2D;
/**
 * Ground cell with a collectible green key on it.
 * @author George Williams Walton, Tom Wood
 * @version 1.3
 */
public class GreenKeyCell extends Collectible {
	
	protected static final String SPRITE = 
			"a2/resources/stock photos/GreenKeySprite.png";
	/**
	 * creates the cell at the location.
	 * @param pos holds the Vector2D of the cell
	 */
	public GreenKeyCell(Vector2D pos) {
		super(pos);
	}
	/**
	 * creates the collectible item.
	 * @return returns the newly made item
	 */
	public Item createItem() {
		return new GreenKey();
	}
	/**
	 * Getter for the GreenKeyCell Sprite.
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
		return 'g';
	}

}
