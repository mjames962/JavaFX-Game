package a2;
/**
 * the item green key for the players inventory.
 * @author George Williams Walton, Tom Wood
 */
public class GreenKey extends Key {
	
	private static final String SPRITE = 
			"a2/resources/stock photos/GreenKeySprite.png";
	/**
	 * An item of type green key.
	 */
	public GreenKey() {
		super();
	}
	/**
	 * Returns the ItemID.
	 * @return gives the itemID of 3
	 */
	public int getItemID() {
		return 2;
	}
	/**
	 * Returns the sprite.
	 * @return gives the sprite
	 */
	public String getSprite() {
		return SPRITE;
	}
}
