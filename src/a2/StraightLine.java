package a2;

/**
 * This enemy moves in a straight line.
 * @author Darius Thomas
 * @version 1.0
 * */
public class StraightLine extends Entity {

	private Vector2D currentVector;

	/**
	 * Constructs the StraightLine class.
	 * @param currentVector the position of the enemy
	 * @param enemyID the ID of the enemy
	 * 	 */
	public StraightLine(Vector2D currentVector, int enemyID) {
		super(currentVector, enemyID);
	}
	
	/**
	 * Moves the enemy.
	 */
	public void move() {
		int cX = currentVector.getX();
		int cY = currentVector.getY();
		
	}

}
