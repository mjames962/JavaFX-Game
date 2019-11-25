package cell;

import a2.RedKey;
import a2.Vector2D;

public class RedKeyCell extends Collectible {

	public RedKeyCell(Vector2D pos) {
		super(pos);
		// TODO Auto-generated constructor stub
	}

	public Item createItem() {
		return new RedKey();
	}

}
