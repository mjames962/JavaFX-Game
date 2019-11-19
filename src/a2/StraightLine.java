package a2;

/**
 * This enemy moves in a straight line.
 * @author Darius Thomas
 * @version 1.0
 * */
public class StraightLine extends Entity {

	private static final String FINAL = null;
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
	 * determines the next moves the enemy.
	 */

	//Send to J to fix logic
	/*	public void nextMove() {
		int cX = currentVector.getX();
		int cY = currentVector.getY();
		Vector2D nextVector = null;
		if (direction == v) {
			FINAL int X = cX;
			if (validMove == True) {
				nY = cY++;
				
			}
			else {
				nY = cY--
				
			}
			
		}
		else {
			FINAL int Y = cY;
		}
		
		if (validMove == True){
			
		} 
		else
	} */

}
