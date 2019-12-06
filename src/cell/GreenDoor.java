package cell;

import a2.GreenKey;
import a2.Player;
import a2.Vector2D;
/**
 * Class for a type of coloured key door.
 * @author George Williams Walton, Tom Wood
 * @version 1.3
 */
public class GreenDoor extends Door {
	
	protected static final String SPRITE = 
			"a2/resources/stock photos/GreenDoor.png";
	/**
	 * creates a door at the position.
	 * @param pos holds current Vector2D of the door
	 */
	public GreenDoor(Vector2D pos) {
		super(pos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean meetsRequirement(Player ply) {
		return ply.hasItem(GreenKey.class);
	}
	
	/**
	 * Getter for the GreenDoor Sprite.
	 * @return gives the sprite
	 */
	public String getSprite() {
		return SPRITE;
	}
	/**
	 * Getter for unique identifying character.
	 * @return gives the unique identifier
	 */
	public char getChar() {
		return 'G';
	}

}
