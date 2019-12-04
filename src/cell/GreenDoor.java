package cell;

import a2.Player;
import a2.Vector2D;
/**
 * Class for a type of coloured key door.
 * @author George, Tom
 */
public class GreenDoor extends Door {
	
	protected static final String SPRITE = "a2/resources/stock photos/GreenDoor.png";
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
		return ply.hasKey('g');
	}
	
	public String cellName() {
		return "GreenDoor";
	}
	
	public String getSprite() {
		return SPRITE;
	}
	
	public char getChar() {
		return 'G';
	}

}
