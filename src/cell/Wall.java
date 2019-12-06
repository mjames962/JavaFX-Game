package cell;

import a2.Vector2D;

/**
 * A cell that blocks the player.
 * @author George Williams Walton
 * @version 1.1
 */
public class Wall extends Cell {
	
	public static final String SPRITE = 
			"a2/resources/stock photos/Wall.png";
	
	
	/**
	 * Send the position to the cell superclass.
	 * @param pos Holds the position of the Cell 
	 */
	
	public Wall(Vector2D pos) {
		super(pos);
		
	}
	
	@Override
	/**
	 * Returns false if entity tries to move onto a wall.
	 * @return stops entities moving onto Walls
	 */
	public boolean isWalkable() {
		return false;
	}
	@Override
	/**
	 * Getter for the WallCell Sprite.
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
		return '#';
	}
}
