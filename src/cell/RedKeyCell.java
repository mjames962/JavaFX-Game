package cell;

import a2.Item;
import a2.RedKey;
import a2.Vector2D;
/**
 * Ground cell with a collectible red key on it.
 * @author George Williams Walton, Tom Wood
 * @version 1.3
 */
public class RedKeyCell extends Collectible {
	
	protected static final String SPRITE = 
			"a2/resources/stock photos/RedKeySprite.png";
	/**
	 * creates the cell at the location.
	 * @param pos holds the Vector2D of the cell
	 */
	public RedKeyCell(Vector2D pos) {
		super(pos);
	}
	/**
	 * creates the collectible item.
	 * @return returns the newly made item
	 */
	public Item createItem() {
		return new RedKey();
	}
	/**
	 * Getter for the RedDoorCell Sprite.
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
		return 'r';
	}

}
