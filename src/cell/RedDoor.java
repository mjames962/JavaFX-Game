package cell;

import a2.Player;
import a2.RedKey;
import a2.Vector2D;
/**
 * Class for a type of coloured key door.
 * @author George, Tom
 */
public class RedDoor extends Door {
	protected static final String SPRITE = "a2/resources/stock photos/RedDoor.png";
	/**
	 * creates a door at the position.
	 * @param pos holds current Vector2D of the door
	 */
	public RedDoor(Vector2D pos) {
		super(pos);
	}
	@Override
	public boolean meetsRequirement(Player ply) {
		return ply.hasKey('r');
		
	}
	
	
	
	public String cellName() {
		return "RedDoor";
	}
	
	public String getSprite() {
		return SPRITE;
	}
	
	public char getChar() {
		return 'R';
	}
}
