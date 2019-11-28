package a2;

public interface Sprite {

	final String DEFAULT_SPRITE = "a2/resources/stock photos/Wall.png";
	
	default String getDefaultSprite() {
		return DEFAULT_SPRITE;
	}
	
	default String getSprite() {
		return getDefaultSprite();
	};
}
