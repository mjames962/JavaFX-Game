package a2;

/**
 * Represents the a door of a specific colour. 
 * @author Tom Wood
 *
 */

public class ColourDoor extends Door {
	
	
	/**
	 * Send the position to the cell superclass.
	 * 
	 * @param pos Holds the position of the Cell 
	 */

	public ColourDoor(Vector2D pos) {
		super(pos);
	}
	
	/**
	 * Finds if the user has the colour of the key that matches
	 * the door.
	 * @param ky The key that the program will compare with the required key 
	 * @return whether or not the key matches the required key
	 */
	
	public boolean meetsRequirement(Key ky) {
		if (ky.getItem() == getRequires()) {
			return true;
		} else {
			return false;
		}
	}
	
	public String cellName() {
		return "ColourDoor";
	}
}
