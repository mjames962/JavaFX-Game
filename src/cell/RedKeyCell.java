package cell;

import a2.RedKey;
import a2.Vector2D;
/**
 * ground cell with a collectible red key on it.
 * @author George, Tom
 */
public class RedKeyCell extends Collectible {
	/**
	 * creates the cell at the location.
	 * @param pos holds the Vector2D of the cell
	 */
	public RedKeyCell(Vector2D pos) {
		super(pos);
		// TODO Auto-generated constructor stub
	}
	/**
	 * creates the collectible item.
	 * @return returns the newly made item
	 */
	public Item createItem() {
		return new RedKey();
	}

}
