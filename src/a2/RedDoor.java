package a2;
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
	@Override
	public boolean meetsRequirement(Player ply) {
		// TODO Auto-generated method stub
		return ply.hasItem(p);
		
	}
}
