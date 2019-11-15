package a2;
/**
 * A cell that teleports the player from one teleporter to another.
 * @author george
 *
 */
public class Teleporter extends Specialised {

	
	
	/**
	 * Create a new teleporter linked to another.
	 * @param pos Position of the teleporter
	 * @param linkedTP The linked teleporter
	 */
	public Teleporter(Vector2D pos, Vector2D linkedTP) {
		super(pos);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Create a new teleporter without a link, to be created later.
	 * @param pos Position of the teleporter
	 */
	public Teleporter(Vector2D pos) {
		super(pos);
	}

	
	/**
	 * Teleport the player.
	 */
	@Override
	public void doAction() {
		//Get player here

	}
	
	
	
}
