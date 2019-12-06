package cell;

import a2.DaggerItem;
import a2.Item;
import a2.Vector2D;

public class DaggerCell extends Collectible {
	
	protected static final String 
		SPRITE = "a2/resources/stock photos/Dagger.png";		
		
	public DaggerCell(Vector2D pos) {
		super(pos);		}
		
	public Item createItem() {
		return new DaggerItem();
	}
		
	public String getSprite() {
		return SPRITE;
	}
		
	public char getChar() {
		return 's';
	}

}
