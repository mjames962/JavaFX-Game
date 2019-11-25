package cell;

import a2.Player;
import a2.Vector2D;

public class BlueDoor extends Door {

	public BlueDoor(Vector2D pos) {
		super(pos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean meetsRequirement(Player ply) {
		// TODO Auto-generated method stub
		return ply.hasItem(p);
	}

}
