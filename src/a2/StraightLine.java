package a2;

/**
 * This enemy moves in a straight line.
 * @author Darius Thomas
 * @version 1.0
 * */
public class StraightLine extends Entity {
	
	/**
	 * Constructs the StraightLine class.
	 * @param pos the position of the enemy
	 * @param enemyID the ID of the enemy
	 */
	public StraightLine(Vector2D pos, int enemyID) {
		super(pos, enemyID);
	}

}
