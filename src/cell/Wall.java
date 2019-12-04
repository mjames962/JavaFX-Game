package cell;

import a2.Vector2D;

//TODO are these needed?

/**
 * A cell that blocks the player.
 * @author george
 *
 */
public class Wall extends Cell {
	
	public final static String SPRITE = "a2/resources/stock photos/Wall.png";
	
	
	/**
	 * Send the position to the cell superclass.
	 * 
	 * @param pos Holds the position of the Cell 
	 */
	
	public Wall(Vector2D pos) {
		super(pos);
		
	}
	
	@Override
	public boolean isWalkable() {
		return false;
	}
	
	public String cellName() {
		return "Wall";
	}
	@Override
	public String getSprite() {
		return SPRITE;
	}
	
	public char getChar() {
		return '#';
	}
}
