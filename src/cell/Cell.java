package cell;

import a2.Player;
import a2.Sprite;
import a2.Vector2D;

/**
 * Holds the Cell Superclass.
 * @author jensen
 *
 */
public abstract class Cell implements Sprite {
	private Vector2D pos;
	
	
	/**
	 * Creates a new cell.
	 * @param vec The initial position of the cell
	 */
	public Cell(Vector2D vec) {
		pos = vec;
	}
	
	/**
	 * Get the x coordinate.
	 * @return the x coordinate
	 */
	public int getX() {
		return pos.getX();
	}
	
	public boolean isWalkable() {
		return true;
	}
	
	public void doAction(Player ply) {
		
	}
	
	/**
	 * Get the y coordinate of the cell.
	 * @return the y coordinate
	 */
	public int getY() {
		return pos.getY();
	}
	
	/**
	 * Gets the position vector of the cell.
	 * @return the position vector
	 */
	public Vector2D getPos() {
		return pos;
	}
	
	/**
	 * Get the sprite (placeholder).
	 * @return the sprite being used
	 */
	
	public String cellName() {
		return "";
	}
	
	public String getSprite() {
		return DEFAULT_SPRITE;
	}
	
	
//	public void draw() {
//		
//	}

}
