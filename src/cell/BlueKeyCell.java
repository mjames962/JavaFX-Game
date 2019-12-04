package cell;

import a2.BlueKey;
import a2.Item;
import a2.Vector2D;
/**
 * ground cell with a collectible blue key on it.
 * @author George, Tom
 */
public class BlueKeyCell extends Collectible {
	
	protected static final String SPRITE = "a2/resources/stock photos/BlueKeySprite.png";
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
	
	public String cellName() {
		return "BlueKeyCell";
	}
	
	public String getSprite() {
		return SPRITE;
	}
	
	public char getChar() {
		return 'b';
	}

}
