package a2;
/**
 * ground cell with a collectible blue key on it.
 * @author George, Tom
 */
public class BlueKeyCell extends Collectible {
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

}
