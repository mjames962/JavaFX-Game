package a2;
/**
 * the item red key for the players inventory.
 * @author George, Tom
 */
public class RedKey extends Key {
	
	private static final String SPRITE = "a2/resources/stock photos/RedKeySprite.png";
	/**
	 * An item of type red key.
	 */
	public RedKey() {
		super();
	}
	
	public int getItemID() {
		return 1;
	}
	
	public String getSprite() {
		return SPRITE;
	}
}
