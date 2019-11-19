package a2;

public class ColourDoor extends Door {

	public ColourDoor(Vector2D pos) {
		super(pos);
	}
	
	public boolean meetsRequirement(Key ky) {
		if (ky.getItem() == requires) {
			return true;
		} else {
			return false;
		}
	}
}
