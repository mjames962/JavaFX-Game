package a2;

abstract public class Item implements Sprite {
	
	public final static String SPRITE = "a2/resources/stock photos/Wall.png";

	
	public Item() {
		// TODO Auto-generated constructor stub
	}
	
	public int getItemID() {
		return -1;
	}

	private String sprite;
	
	public String getSprite() {
		return SPRITE;
	}
	
}
