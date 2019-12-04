package a2;
/**
 * the item red key for the players inventory.
 * @author George Williams Walton, Tom Wood
 * @version 1.1
 */
public class RedKey extends Key {
	
	private static final String SPRITE = 
			"a2/resources/stock photos/RedKeySprite.png";
	/**
	 * An item of type red key.
	 */
	public RedKey() {
		super();
	}
	/**
	 * Returns the itemID.
	 * @return gives the itemID of 3
	 */
	public int getItemID() {
		return 1;
	}
	/**
	 * Returns the sprite.
	 * @return gives the sprite
	 */
	public String getSprite() {
		return SPRITE;
	}
}
