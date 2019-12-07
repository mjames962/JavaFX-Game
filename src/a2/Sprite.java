package a2;
/**
 * An interface for drawing a sprite.
 * @author George Williams Walton
 * @version 1.2
 */
public interface Sprite {

	String DEFAULT_SPRITE = "a2/resources/stock photos/Wall.png";
	
	/**
	 * Sets a default sprite in the event a resource can't be found.
	 * @return gives the default sprite
	 */
	default String getDefaultSprite() {
		return DEFAULT_SPRITE;
	}
	
	/**
	 * Getter for default sprite.
	 * @return gives the default sprite
	 */
	default String getSprite() {
		return getDefaultSprite();
	}
}
