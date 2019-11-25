package a2;

/**
 * A fire cell that will kill the player if they do not
 * Have fire boots in their inventory.
 * @author tomwo
 *
 */

public class Fire extends Cell {
	
	
	/**
	 * Send the position to the cell superclass.
	 * 
	 * @param pos Holds the position of the Cell 
	 */

	public Fire(Vector2D pos) {
		super(pos);
		
	}
	
	public String cellName() {
		return "Fire";
	}
}
