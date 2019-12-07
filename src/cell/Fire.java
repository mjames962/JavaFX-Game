package cell;

import a2.FireBoots;
import a2.Player;
import a2.Vector2D;

/**
 * A fire cell that will kill the player if they do not
 * Have fire boots in their inventory.
 * @author Tom Wood
 * @version 1.2
 */

public class Fire extends Cell {
	
	protected static final  String SPRITE = 
			"a2/resources/stock photos/Fire.png";
	
	/**
	 * Send the position to the cell superclass.
	 * 
	 * @param pos Holds the position of the Cell 
	 */
	public Fire(Vector2D pos) {
		super(pos);
		
	}
	

	/**
	 * Kills the player conditioned on having fire boots.
	 * @param ply references the player
	 */
    @Override
	public void doAction(Player ply) {
		
		if (!ply.hasItem(FireBoots.class)) {
			ply.playerDeath();
		}
	}
	
	/**
	 * Getter for the Fire Sprite.
	 * @return gives the cell sprite
	 */
	public String getSprite() {
		return SPRITE;
	}
	/**
	 * Getter for the cell character.
	 * @return character identifier
	 */
	public char getChar() {
		return 'F';
	}
}
