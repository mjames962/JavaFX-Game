package a2;

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
