package a2;
/**
 * the item blue key for the players inventory.
 * @author George Williams Walton, Tom Wood
 */
public class BlueKey extends Key {
	private static final int ID = 3;
	private static final String 
		SPRITE = "a2/resources/stock photos/BlueKeySprite.png";
	/**
	 * An item of type blue key.
	 */
	public BlueKey() {
		super();
	}
	/**
	 * Returns the itemID.
	 * @return gives the itemID of 3
	 */
	public int getItemID() {
		return ID;
	}
	/**
	 * Returns the sprite.
	 * @return gives the sprite
	 */
	public String getSprite() {
		return SPRITE;
	}

}
