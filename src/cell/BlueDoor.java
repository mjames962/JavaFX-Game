package cell;

import a2.Player;
import a2.Vector2D;
/**
 * Class for a type of coloured key door.
 * @author George, Tom
 */
public class BlueDoor extends Door {
	
	protected static final String SPRITE = "a2/resources/stock photos/BlueDoor.png";
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
		return ply.hasKey('b');
	}
	
	public String cellName() {
		return "BlueDoor";
	}
	
	public String getSprite() {
		return SPRITE;
	}

}
