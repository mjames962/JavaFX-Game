package a2;

/**
 * The Class responsible for the FireBoots Item to 
 * 							be stored in the player's inventory.
 * @author George Williams Walton, Tom Wood
 * @version 1.1
 */
public class FireBoots extends Item {
	private static final int ID = 4;
	private static final String SPRITE = 
			"a2/resources/stock photos/fireboots.png";

	/**
	 * Returns the ItemID for FireBoots.
	 * @return gives the ItemID
	 */
	public int getItemID() {
		return ID;
	}
	/**
	 * Returns the FireBoots sprite.
	 * @return gives the sprite for the Item
	 */
	public String getSprite() {
		return SPRITE;
	}
	
}
