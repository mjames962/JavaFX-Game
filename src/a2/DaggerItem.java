package a2;

/**
 * Class for the dagger item.
 * @author Tom Wood
 * version 1.2
 */
public class DaggerItem extends Item {

	
	private static final int ID = 10;
	private static final String 
		SPRITE = "a2/resources/stock photos/Dagger.png";
	/**
	 * An item of type blue key.
	 */
	public DaggerItem() {
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
