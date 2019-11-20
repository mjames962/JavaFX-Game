package a2;

import a2.Player.Item;

public abstract class Door extends Replaceable {

	public Item requires;
	
	public Door(Vector2D pos) {
		super(pos);
	}
	
	public boolean meetsRequirement(Player ply) {
		return ply.hasItem(requires);
	}
	
}
