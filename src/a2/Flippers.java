package a2;

/**
 * The Class responsible for the Flippers Item to 
 * 							be stored in the player's inventory.
 * @author George Williams Walton, Tom Wood
 *
 */
public class Flippers extends Item {
	private static final int ID = 5;
	private static final String SPRITE = 
			"a2/resources/stock photos/flippers.png";

	@Override
	void pickUp(Player p) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Returns the ItemID for Flippers.
	 * @return gives the ItemID
	 */
	public int getItemID() {
		return ID;
	}
	/**
	 * Returns the Flippers sprite.
	 * @return gives the sprite for the Item
	 */
	public String getSprite() {
		return SPRITE;
	}

}
