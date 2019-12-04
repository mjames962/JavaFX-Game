package a2;

/**
 * Class that holds the location of the player.
 * @author James Colebourn, Darius Thomas 
 * @version 1.0
 */

public abstract class PlayerTarget extends Entity {
	/**
	 * Shared data constructor for the enemy types that need player location.
	 * @param currentVector the current location of the entity
	 * @param entityID holds what type of entity is it
	 * @param level stores the current level
	 */
	public PlayerTarget(Vector2D currentVector, int entityID, Level level) {
		super(currentVector, entityID, level);
	}
	
	
	/**
	 * Getter for seeing the player's location.
	 * @method returns the current location of the player as requested
	 * @return returns a type Vector2D
	 */
	public Vector2D getPlayerVector() {
		Vector2D playerVector = Player.getVector(this.Player);
		return playerVector;
	}
	
	
}
