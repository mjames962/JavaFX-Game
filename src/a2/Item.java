package a2;
/**
 * The Superclass for all Items.
 * @author George Williams Walton, Tom Wood
 * @version 1.3
 */
public abstract class Item implements Sprite {
	
	public static final String SPRITE = "a2/resources/stock photos/Wall.png";

	private String sprite;
	/**
	 * Empty Constructor to be overwritten.
	 */
	public Item() {
	}
	/**
	 * Method to be overwritten to return ItemID.
	 * @return gives -1 to prevent Item being instantiated
	 */
	public int getItemID() {
		return -1;
	}


	 /** 
	 * Method to be overwritten to return the Sprite for an item.
	 * @return gives a generic to prevent Item being drawn
	 */
	public String getSprite() {
		return SPRITE;
	}
	
}
