package a2;

/**
 * A class that represents a coordinate in 2d.
 * @author george
 *
 */
public class Vector2D {
	
	private int x = 0;
	private int y = 0;
	
	/**
	 * Creates a new 2d coordinate.
	 * @param x the initial x coordinate
	 * @param y the initial y coordinate
	 */
	public Vector2D(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Gets the x coordinate.
	 * @return x coordinate
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Gets the y coordinate.
	 * @return y coordinate
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Copies another vectors values.
	 * @param vec the other vector
	 */
	public void copy(Vector2D vec) {
		set(vec.getX(), vec.getY());
	}
	
	/**
	 * Sets the x coordinate of the vector.
	 * @param x x coordinate
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Sets the y coordinate of the vector.
	 * @param y y coordinate
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Sets the x and y coordinates of the vector.
	 * @param x x coordinate
	 * @param y y coordinate
	 */
	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}
	

}
