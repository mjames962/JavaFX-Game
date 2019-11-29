package a2;
/**
 * the item green key for the players inventory.
 * @author George, Tom
 */
public class GreenKey extends Key {
	
	private static final String SPRITE = "a2/resources/stock photos/GreenKeySprite.png";
	/**
	 * An item of type green key.
	 */
	public GreenKey() {
		super();
	}
	
	public int getItemID() {
		return 2;
	}
		
	public String getSprite() {
		return SPRITE;
	}
}
