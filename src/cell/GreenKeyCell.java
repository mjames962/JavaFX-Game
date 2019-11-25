package cell;

import a2.GreenKey;
import a2.Vector2D;

public class GreenKeyCell extends Collectible {

	public GreenKeyCell(Vector2D pos) {
		super(pos);
		// TODO Auto-generated constructor stub
	}
	
	public Item createItem() {
		return new GreenKey();
	}

}
