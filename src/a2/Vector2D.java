package a2;

/**
 * A class that represents a coordinate in 2d.
 * @author George Williams Walton
 * @version 1.4
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
	 * Empty Constructor.
	 */
	public Vector2D() {
		
	}
	/**
	 * Duplicates a Vector2D.
	 * @param othervec a vector2D being taken in
	 */
	public Vector2D(Vector2D othervec) {
		copy(othervec);
	}
	/**
	 * returns a string for referencing purposes.
	 * @return 's a string for the reference
	 */
	public String toString() {
		return String.format("%d, %d", this.x, this.y);
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
	 * Subtraction for computation of vectors.
	 * @param otherVec the vector being subtracted
	 */
	public void takeAway(Vector2D otherVec) {
		set(this.x - otherVec.getX(), this.y - otherVec.getY());
	}
	/**
	 * Addition for computation of vectors.
	 * @param otherVec the vector being added
	 */
	public void add(Vector2D otherVec) {
		set(this.x + otherVec.getX(), this.y + otherVec.getY());
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
	/**
	 * Compares two vectors.
	 * @param otherVec the vector being compared to
	 * @return gives the resultant Vector2D
	 */
	public boolean equals(Vector2D otherVec) {
		boolean xEqual = this.x == otherVec.x;
		boolean yEqual = this.y == otherVec.y;
		return xEqual && yEqual;
	}

}