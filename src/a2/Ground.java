package a2;

/**
 * the ground cell that the player and enemies can walk on with no other effect.
 * 
 * @author tomwo
 *
 */

public class Ground extends Cell {

	/**
	 * Send the position to the cell superclass.
	 * 
	 * @param pos
	 *            Holds the position of the Cell
	 */

	public Ground(Vector2D pos) {
		super(pos);

	}

	public String cellName() {
		return "Ground";
	}
}
