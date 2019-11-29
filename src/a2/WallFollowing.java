package a2;

/**
 * This enemy follows the wall to its left
 * placed on the lower and right side of the wall.
 * @author James Colebourn
 * @version 1.0
 */
public class WallFollowing extends Entity {
	private Vector2D currentVector;
	private boolean increaseVDirection = true;
	private boolean increaseHDirection = true;
	private String direction;
	/**
	 * Constructs the Wall Following Enemy.
	 * @param currentVector the position of the enemy
	 * @param enemyID the ID of the enemy
	 * @param direction the starting direction of the enemy
	 * @param level The given Level the enemy is on for a reference
	 */
	public WallFollowing(Vector2D currentVector, 
			int enemyID, String direction, Level level) {
		super(currentVector, enemyID, level);
		enemyID = 2;
		currentVector = this.currentVector;
		this.direction = direction;
		
	}
	
	/**
	 * establishes the next move for the enemy.
	 * @return nextVector the requested next cell to move to
	 */
	@SuppressWarnings("null")
	public Vector2D nextMove() {
		int cX = currentVector.getX();
		int cY = currentVector.getY();
		Vector2D tempVector = null;
		Vector2D nextVector = null;
		cX = currentVector.getX();
		cY = currentVector.getY();
		if (direction == "v") { 					//direction is Vertical
			if (increaseVDirection == true) {
				tempVector.set(cX--, cY);
				if (this.isValidMove(2, tempVector, level) == false) {
					tempVector.set(cX, cY++);
					if (this.isValidMove(2, tempVector, level) == true) {
						nextVector.set(cX, cY++);
					} else {
						increaseVDirection = false;	//dead end, entity direction
						nextMove();					//flips+recalls nextMove
					}
				} else {
					nextVector.set(cX--, cY);
					increaseVDirection = false;		//turns corner+edits values
					direction = "h";				//new direction of travel
				}
			} else { //increase v direction false
				tempVector.set(cX++, cY);
				if (this.isValidMove(2, tempVector, level) == false) {
					tempVector.set(cX, cY--);
					if (this.isValidMove(2, tempVector, level) == true) {
						nextVector.set(cX, cY--);
					} else {
						increaseVDirection = true;	//dead end, entity direction
						nextMove();					//flips+recalls nextMove
					}
				} else {
					nextVector.set(cX++, cY);
					increaseVDirection = true;		//turns corner+edits values
					direction = "h";				//new direction of travel
				}
			}
		} else { 									// direction is Horizontal
			if (increaseHDirection == true) {		//increaseHDirection true
				tempVector.set(cX, cY++);
				if (this.isValidMove(2, tempVector, level) == false) {
					tempVector.set(cX++, cY);
					if (this.isValidMove(2, tempVector, level) == true) {
						nextVector.set(cX++, cY);
					} else {
						increaseHDirection = false;	//dead end, entity direction
						nextMove();					//flips+recalls nextMove
					}
				} else {
					nextVector.set(cX, cY++);
					increaseHDirection = false;		//turns corner+edits values
					direction = "v";				//new direction of travel
				}
			} else { 								//increaseHDirection false
				tempVector.set(cX, cY--);
				if (this.isValidMove(2, tempVector, level) == false) {
					tempVector.set(cX--, cY);
					if (this.isValidMove(2, tempVector, level) == true) {
						nextVector.set(cX--, cY);
					} else {
						increaseHDirection = true;	//dead end, entity direction
						nextMove();					//flips+recalls nextMove-
					}
				} else {
					nextVector.set(cX, cY--);
					increaseHDirection = true;		//turns corner+edits values
					direction = "v";				//new direction of travel
				}
			}
		}	
		return nextVector;
	}
	
}
