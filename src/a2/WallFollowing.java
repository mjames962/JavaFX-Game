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
	 */
	public WallFollowing(Vector2D currentVector, int enemyID, String direction, Level level) {
		super(currentVector, enemyID, level);
		enemyID = 2;
		currentVector = this.currentVector;
		this.direction = direction;
		
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
		Vector2D tempVector = null;
		Vector2D nextVector = null;
		if (direction == "v") { //direction is vertical
			if (increaseVDirection == true) {
				cX = currentVector.getX();
				cY = currentVector.getY();
				tempVector.set(cX--, cY);
				if (this.isValidMove(2, tempVector, level) == false) {
					tempVector.set(cX, cY++);
					if (this.isValidMove(2, tempVector, level) == true) {
						nextVector.set(cX, cY++);
					} else {
						increaseVDirection = false;
						nextMove();
					}
				} else {
					nextVector.set(cX--, cY);
					increaseVDirection = false;
					direction = "h";
				}
			} else { //increase v direction false
				
			}
		} else { // direction is horizontal
			
		}	
		return nextVector;
	}
	
}
