package cell;

import a2.Vector2D;

/**
 * Holds the Cell Superclass.
 * @author jensen
 *
 */
public abstract class Cell {
	private Vector2D pos;
	private String sprite = "a2/resources/stock photos/Ground_Cell.png"; //placeholder
	
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
	public String getSprite() {
		return sprite;
	}
	
	public String cellName() {
		return "";
	}
	
//	public void draw() {
//		
//	}

}
