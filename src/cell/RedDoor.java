package cell;

import a2.Player;
import a2.Vector2D;

public class RedDoor extends Door {

	public RedDoor(Vector2D pos) {
		super(pos);
	}
	
	public boolean meetsRequirement(Player ply) {
		return ply.hasItem(p);
		
	}
}
