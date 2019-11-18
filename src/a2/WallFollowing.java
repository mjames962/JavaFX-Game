package a2;

/**
 * This enemy follows the nearest wall it can locate.
 * @author Darius Thomas
 * @version 1.0
 */
public class WallFollowing extends Entity {
	
	/**
	 * Constructs the Wall Following Enemy.
	 * @param pos the position of the enemy
	 * @param enemyID the ID of the enemy
	 */
	public WallFollowing(Vector2D pos, int enemyID) {
		super(pos, enemyID);
	}
	
	/**
	 * Moves the enemy depending on the movement of the player.
	 */
	@Override
	public void move() {
		//TODO
	}
	
}
