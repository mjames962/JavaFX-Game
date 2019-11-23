package a2;

/**
 * This enemy moves in a straight line.
 * @author Darius Thomas and James Colebourn
 * @version 1.0
 * */
public class StraightLine extends Entity {
	
	private Vector2D currentVector;
	private boolean increaseDirection = true;
	private String direction;
	
	/**
	 * Constructs the StraightLine class.
	 * @param currentVector the position of the enemy
	 * @param enemyID the ID of the enemy
	 * 	 
	 */
	private StraightLine(Vector2D currentVector, int enemyID) {
		super(currentVector, enemyID);
	}
	
	/**
	 * .
	 * @return nextVector
	 */
	public Vector2D nextMove() {

		int cX = currentVector.getX();
		int cY = currentVector.getY();
		final int X;
		final int Y;
		int nX = 0;
		int nY = 0;
		Vector2D nextVector = null;
		nextVector.set(cX, cY);

		if (direction == "v") {
			X = cX;
			if (increaseDirection == true) {
				cX = currentVector.getX();
				cY = currentVector.getY();
				if (direction == "V") {
					X = cX;
					if (increaseDirection == true) {
						nY = cY++;
						nextVector.set(nX, nY);
					} else {
						nY = cY--;
						nextVector.set(nX, nY); 
					} 
				} else if (direction == "h") {
					Y = cY;
					if (increaseDirection == true) {
					}
				} else {
					nY = cY--;
					nextVector.set(nX, nY);
				}
			} else { // direction == H
				Y = cY;
				if (increaseDirection == true) {
					nX = cX++;
					nextVector.set(nX, nY);				
				} else {
					nX = cX--;
					nextVector.set(nX, nY);
				}
				if (isValidMove(this.getEntityID()) == false) {
					if (increaseDirection == false) {
						increaseDirection = true;
						nextMove();
					}
				} else {
					increaseDirection = false;
					nextMove();
				}
			}
		}
		return nextVector;
	}		
}