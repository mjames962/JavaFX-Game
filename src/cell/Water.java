package cell;

import a2.Vector2D;

/**
 * The water cell that will kill the player if
 * they walk on it without flippers.
 * @author tomwo
 *
 */

public class Water extends Cell {
	
	protected static final String SPRITE = "a2/resources/stock photos/Water.png";
	
	/**
	 * Send the position to the cell superclass.
	 * 
	 * @param pos Holds the position of the Cell 
	 */

	public Water(Vector2D pos) {
		super(pos);
		
	}
	
	public String cellName() {
		return "Water";
	}
	
	public String getSprite() {
		return SPRITE;
	}
	
	public char getChar() {
		return 'W';
	}
}
