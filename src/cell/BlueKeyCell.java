package cell;

import a2.BlueKey;
import a2.Vector2D;

public class BlueKeyCell extends Collectible {

	public BlueKeyCell(Vector2D pos) {
		super(pos);
		// TODO Auto-generated constructor stub
	}

	
	public Item createItem() {
		// TODO Auto-generated method stub
		return new BlueKey();
	}

}
