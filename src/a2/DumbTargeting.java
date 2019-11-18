package a2;

/**
 * This enemy will locate the player, but will include invalid moves.
 * @author Darius Thomas
 * @version 1.0
 * */
public class DumbTargeting extends Entity {
	
	/**
	 * Constructs the WallFollowing class.
	 * @param pos the position of the enemy
	 * @param enemyID the ID of the enemy
	 */
	public DumbTargeting(Vector2D pos, int enemyID) {
		super(pos, enemyID);
	}

}
