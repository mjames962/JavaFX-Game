package a2;
/**
 * the item blue key for the players inventory.
 * @author George, Tom
 */
public class BlueKey extends Key {
	
	private static final String SPRITE = "a2/resources/stock photos/BlueKeySprite.png";
	/**
	 * An item of type blue key.
	 */
	public BlueKey() {
		super();
	}
	
	public int getItemID() {
		return 3;
	}
	
	public String getSprite() {
		return SPRITE;
	}

}
