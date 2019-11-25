package cell;

import a2.Player;
import a2.Vector2D;
/**
 * Class for a type of coloured key door.
 * @author George, Tom
 */
public class RedDoor extends Door {
	/**
	 * creates a door at the position.
	 * @param pos holds current Vector2D of the door
	 */
	public RedDoor(Vector2D pos) {
		super(pos);
	}
	
	public boolean meetsRequirement(Player ply) {
		return ply.hasItem(p);
		
	}
}
