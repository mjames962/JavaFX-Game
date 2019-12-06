package cell;

import a2.DaggerItem;
import a2.Item;
import a2.Vector2D;
/**
 * Class for the Cell holding the dagger item.
 * @author Tom Wood
 * @version 1.1
 */
public class DaggerCell extends Collectible {
	
	protected static final String 
		SPRITE = "a2/resources/stock photos/Dagger.png";		
	
	/**
	 * Holds position of the cell as a Vector2D.
	 * @param pos holds the cell position
	 */
	public DaggerCell(Vector2D pos) {
		super(pos);		}
	/**
	 * Creates and returns the item to player inventory.
	 * @return gives the dagger item	
	 */
	public Item createItem() {
		return new DaggerItem();
	}
	/**
	 * Returns the dagger cell sprite.
	 * @return gives the class sprite	
	 */
	public String getSprite() {
		return SPRITE;
	}
	/**
	 * Getter for the dagger cell char.
	 * @return the character for the cell	
	 */
	public char getChar() {
		return 's';
	}

}
