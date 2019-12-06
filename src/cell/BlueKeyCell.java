package cell;

import a2.BlueKey;
import a2.Item;
import a2.Vector2D;
/**
 * ground cell with a collectible blue key on it.
 * @author George Williams Walton, Tom Wood
 * @version 1.1
 */
public class BlueKeyCell extends Collectible {
	
	protected static final String SPRITE = 
			"a2/resources/stock photos/BlueKeySprite.png";
	/**
	 * creates the cell at the location.
	 * @param pos holds the Vector2D of the cell
	 */
	public BlueKeyCell(Vector2D pos) {
		super(pos);
		// TODO Auto-generated constructor stub
	}

	/**
	 * creates the collectible item.
	 * @return returns the newly made item
	 */
	public Item createItem() {
		// TODO Auto-generated method stub
		return new BlueKey();
	}
	/**
	 * Holds the name of the cell as a string.
	 * @return gives the string name of the cell
	 */
	public String cellName() {
		return "BlueKeyCell";
	}
	/**
	 * Getter for the class' sprite.
	 * @return gives the sprite for the class
	 */
	public String getSprite() {
		return SPRITE;
	}
	/**
	 * Getter for the map file format for this class.
	 * @return gives the character in level design for a Blue Key
	 */	
	public char getChar() {
		return 'b';
	}

}
