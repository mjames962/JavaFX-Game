
import a2.DaggerItem;
import a2.Flippers;
import a2.Item;
import a2.Vector2D;

public class Dagger extends Collectible {
	
	
	protected static final String 
		SPRITE = "a2/resources/stock photos/flippers.png";
	
	
	public Dagger(Vector2D pos) {
		super(pos);
	}
	
	public Item createItem() {
		return new DaggerItem();
	}
	
	public String cellName() {
		return "DaggerCell";
	}
	
	public String getSprite() {
		return SPRITE;
	}
	
	public char getChar() {
		return 'd';
	}

}
