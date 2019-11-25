package a2;

public class GreenKeyCell extends Collectible {

	public GreenKeyCell(Vector2D pos) {
		super(pos);
		// TODO Auto-generated constructor stub
	}
	
	public Item createItem() {
		return new GreenKey();
	}

}
