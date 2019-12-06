package cell;

import a2.Vector2D;

/**
 * The ground cell that the player and enemies can walk on with no other effect.
 * @author Tom Wood
 * @version 1.2
 */

public class Ground extends Cell {

	protected static final String SPRITE = 
			"a2/resources/stock photos/Ground_Cell.png";
	
	/**
	 * Send the position to the cell superclass.
	 * 
	 * @param pos
	 *            Holds the position of the Cell
	 */

	public Ground(Vector2D pos) {
		super(pos);

	}
	/**
	 * Getter for the Ground Sprite.
	 * @return gives the sprite
	 */
	public String getSprite() {
		return SPRITE;
	}
	/**
	 * Getter for unique identifying character.
	 * @return gives the unique identifier
	 */
	public char getChar() {
		return '_';
	}
}
