package cell;

import a2.Flippers;
import a2.Player;
import a2.Vector2D;

/**
 * The water cell that will kill the player if
 * they walk on it without flippers.
 * @author Tom Wood
 * @version 1.3
 */

public class Water extends Cell {
	
	protected static final String SPRITE = 
			"a2/resources/stock photos/Water.png";
	
	/**
	 * Send the position to the cell superclass.
	 * 
	 * @param pos Holds the position of the Cell 
	 */

	public Water(Vector2D pos) {
		super(pos);
		
	}
	
	@Override
	public void doAction(Player ply) {
		
		if (!ply.hasItem(Flippers.class)) {
			ply.playerDeath();
		}
	}
	/**
	 * Getter for the Water Sprite.
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
		return 'W';
	}
}
