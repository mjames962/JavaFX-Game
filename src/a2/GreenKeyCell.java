package a2;
/**
 * ground cell with a collectible green key on it.
 * @author George, Tom
 */
public class GreenKeyCell extends Collectible {
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
		// TODO Auto-generated method stub
		return new GreenKey();
	}

}
