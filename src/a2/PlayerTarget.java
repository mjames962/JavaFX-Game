package a2;

/**
 * Class that holds the location of the player.
 * @author Darius Thomas and James Colebourn
 * @version 1.0
 */

public abstract class PlayerTarget extends Entity {
	
/*
 * @param playerX holds the player's current Horizontal position
 * @param playerY holds the player's current Vertical position	
 */
	
	public PlayerTarget(Vector2D vector, int entityID) {
		super(vector, entityID);
	}

	private Object playerVector;
	
	/**
	 * See below method tag.
	 * @method getPlayerVector aquires the players current location
	 */
	public void gerPlayerVector() {
		this.playerVector = playerVector;
	}
	
	/**
	 * Getter for seeing the player's location.
	 * @method returns the current location of the player as requested
	 * @return returns a type Vector2D
	 */
	public Object getPlayerVector() {
		return playerVector;
	}
	
	
}
