package a2;

/**
 * This enemy moves in a straight line.
 * @author Darius Thomas and James Colebourn
 * @version 1.0
 * */
public class StraightLine extends Entity {
	
	private boolean increaseDirection = true;
	private String direction = "v";
	private static final String SPRITE = "a2/resources/stock photos/Straight_Line_Enemy.png";
	
	/**
	 * Constructs the StraightLine class.
	 * @param enemyID the ID of the enemy
	 * @param currentVector the position of the enemy
	 * @param level the current level in progress
	 * 	 
	 */
	public StraightLine(Vector2D currentVector, int enemyID, Level level) {
		super(currentVector, enemyID, level);
	}
	
	public String getSprite() {
		return SPRITE;
	}
	
	/**
	 * establishes the next move for the enemy.
	 * @return nextVector the requested next cell to move to
	 */
	public Vector2D nextMove() {
		int cX = getCurrentVector().getX();
		int cY = getCurrentVector().getY();
		Vector2D nextVector = new Vector2D(cX, cY);;
		if (direction .equals("v")) { // entity is moving vertically
			if (increaseDirection == true) {
				nextVector.set(cX, ++cY);
			} else {
				nextVector.set(cX, --cY);
			}
		} else {					//entity is moving horizontally
			if (increaseDirection == true) {
				nextVector.set(++cX, cY);
			} else {
				nextVector.set(--cX, cY); 
			} 
		}
		if (this.isValidMove(nextVector) == false) {
			if (increaseDirection == false) {
				increaseDirection = true;
			} else {
				increaseDirection = false;
			}
			return nextMove();
		}
		return nextVector;
	}		
	
	public void move() {
		this.setCurrentVector(nextMove());
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
