package cell;

import a2.Item;
import a2.Player;
import a2.RedKey;
import a2.Vector2D;
/**
 * Class for a type of coloured key door.
 * @author George Williams Walton, Tom Wood
 * @version 1.3
 */
public class RedDoor extends Door {
	protected static final String SPRITE = 
			"a2/resources/stock photos/RedDoor.png";
	/**
	 * creates a door at the position.
	 * @param pos holds current Vector2D of the door
	 */
	public RedDoor(Vector2D pos) {
		super(pos);
	}


    /**
     * Finds if the player has a red key in their inventory.
     * @param ply The player on the level
     * @return if the player has the key in their inventory.
     */
	@Override
	public boolean meetsRequirement(Player ply) {
		Item redKey = ply.getItemOfType(RedKey.class);
		if (redKey != null) {
			ply.removeItem(redKey);
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * Getter for the RedDoorCell Sprite.
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
		return 'R';
	}
}
