package cell;

import a2.BlueKey;
import a2.Player;
import a2.Vector2D;
/**
 * Class for a type of coloured key door.
 * @author George Williams Walton, Tom Wood
 * @version 1.1
 */
public class BlueDoor extends Door {
	
	protected static final String SPRITE = 
			"a2/resources/stock photos/BlueDoor.png";
	/**
	 * creates a door at the position.
	 * @param pos holds current Vector2D of the door
	 */
	public BlueDoor(Vector2D pos) {
		super(pos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean meetsRequirement(Player ply) {
		// TODO Auto-generated method stub
		return ply.hasItem(BlueKey.class);
	}
	
	/**
	 * Getter for the class' sprite.
	 * @return gives the sprite for the class
	 */
	public String getSprite() {
		return SPRITE;
	}
	/**
	 * Getter for the map file format for this class.
	 * @return gives the character in level design for a BlueKeyDoor
	 */
	public char getChar() {
		return 'B';
	}

}
