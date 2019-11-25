package a2;

public class RedDoor extends Door {

	public RedDoor(Vector2D pos) {
		super(pos);
	}
	
	public boolean meetsRequirement(Player ply) {
		return ply.hasItem(p);
		
	}
}
