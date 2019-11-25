package cell;

import a2.Vector2D;

//TODO are these needed?

/**
 * A cell that blocks the player.
 * @author george
 *
 */
public class Wall extends Cell {
	
	/**
	 * Send the position to the cell superclass.
	 * 
	 * @param pos Holds the position of the Cell 
	 */
	
	public Wall(Vector2D pos) {
		super(pos);
		
	}
	
	public String cellName() {
		return "Wall";
	}
}
