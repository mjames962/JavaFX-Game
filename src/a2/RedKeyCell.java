package a2;

public class RedKeyCell extends Collectible {

	public RedKeyCell(Vector2D pos) {
		super(pos);
		// TODO Auto-generated constructor stub
	}

	public Item createItem() {
		return new RedKey();
	}

}
