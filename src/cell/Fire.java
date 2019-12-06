package cell;

import a2.Vector2D;

/**
 * A fire cell that will kill the player if they do not
 * Have fire boots in their inventory.
 * @author tomwo
 *
 */

public class Fire extends Cell {
	
	protected final static String SPRITE = "a2/resources/stock photos/Fire.png";
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
	
	public String getSprite() {
		return SPRITE;
	}
	
	public char getChar() {
		return 'F';
	}
}
