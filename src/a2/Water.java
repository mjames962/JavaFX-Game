package a2;

/**
 * The water cell that will kill the player if
 * they walk on it without flippers.
 * @author tomwo
 *
 */

public class Water extends Cell {
	
	/**
	 * Send the position to the cell superclass.
	 * 
	 * @param pos Holds the position of the Cell 
	 */

	public Water(Vector2D pos) {
		super(pos);
		
	}
}
