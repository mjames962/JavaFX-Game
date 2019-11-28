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
	 * @param enemyID the ID of the enemy
	 * @param currentVector the position of the enemy
	 * 	 
	 */
	public StraightLine(Vector2D currentVector, int enemyID, Level level) {
		super(currentVector, enemyID, level);
	}
	
	/**
	 * establishes the next move for the enemy.
	 * @return nextVector the requested next cell to move to
	 */
	public Vector2D nextMove() {
		int cX = currentVector.getX();
		int cY = currentVector.getY();
		int nX;
		int nY;
		Vector2D nextVector = null;
		nextVector.set(cX, cY);
		if (direction == "v") { // entity is moving vertically
			cX = currentVector.getX();
			cY = currentVector.getY();
			if (increaseDirection == true) {
				nY = cY++;
				nextVector.set(cX, nY);
			} else {
				nY = cY--;
				nextVector.set(cX, nY);
			}
		} else {					//entity is moving horizontally
			cX = currentVector.getX();
			cY = currentVector.getY();
			if (increaseDirection == true) {
				nX = cX++;
				nextVector.set(nX, cY);
			} else {
				nX = cX--;
				nextVector.set(nX, cY); 
			} 
		}
		if (this.isValidMove(1, nextVector, level) == false) {
			if (increaseDirection == false) {
				increaseDirection = true;
				nextMove();
			} else {
				increaseDirection = false;
				nextMove();
			}
		}
		return nextVector;
	}		
}
			
			
			
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	/*	if (direction == "v") {		//entity is moving vertically
			X = cX;
			if (increaseDirection == true) {
				cX = currentVector.getX();
				cY = currentVector.getY();
				if (direction == "v") {
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
			
			
			
			
			} else { 				// entity is moving horizontally
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
		}*/
