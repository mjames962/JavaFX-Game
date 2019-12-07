package cell;

import a2.Player;
import a2.Sprite;
import a2.Vector2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Holds the Cell Superclass.
 * @author Jensen Beard
 * @version 1.3
 *
 */
public abstract class Cell implements Sprite {
	private final Vector2D pos;
	
	
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
	 * Used in player to determine whether the player can 
	 * 									walk onto this cell or not.
	 * @return true if the player can walk on cell
	 */
	public boolean isWalkable() {
		return true;
	}
	
	/**
	 * Overrideable action method called before the player moves onto the cell.
	 * If not overriden, the cell will do nothing.
	 * @param ply the current player
	 */
	public void doAction(Player ply) { }

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
	 * Getter for the class' sprite.
	 * @return gives the generic sprite
	 */
	public String getSprite() {
		return DEFAULT_SPRITE;
	}
	/**
	 * Getter for the map file format for this class.
	 * @return gives the character default for a non-specified cell
	 */
	public char getChar() {
		return '!';
	}
	
	
	/**
	 * Draws a sprite for the given cell at a specified location.
	 * @param gc the current graphics context the cell will be drawn in
	 * @param drawPosX holds the X coordinate
	 * @param drawPosY holds the Y coordinate
	 */
	public void draw(GraphicsContext gc, int drawPosX, int drawPosY) {
		Image cellImage = new Image(getSprite());
		gc.drawImage(cellImage, drawPosX, drawPosY);
	}
}
