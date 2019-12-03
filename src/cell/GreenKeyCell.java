package cell;

import a2.GreenKey;
import a2.Item;
import a2.Vector2D;
/**
 * ground cell with a collectible green key on it.
 * @author George, Tom
 */
public class GreenKeyCell extends Collectible {
	
	protected static final String SPRITE = "a2/resources/stock photos/GreenKeySprite.png";
	/**
	 * creates the cell at the location.
	 * @param pos holds the Vector2D of the cell
	 */
	public GreenKeyCell(Vector2D pos) {
		super(pos);
		// TODO Auto-generated constructor stub
	}
	/**
	 * creates the collectible item.
	 * @return returns the newly made item
	 */
	public Item createItem() {
		return new GreenKey();
	}
	
	public String cellName() {
		return "GreenKeyCell";
	}
	
	public String getSprite() {
		return SPRITE;
	}

}
