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
	
	
	
	 //determines the next moves the enemy.
	 


		public Vector2D nextMove() {
<<<<<<< HEAD
			int cX = currentVector.getX();
			int cY = currentVector.getY();
			final int X;
			final int Y;
			int nX = 0;
			int nY = 0;
			Vector2D nextVector;
			
			if (direction == "v") {
				X = cX;
				if (increaseDirection == true) {
=======
		int cX = currentVector.getX();
		int cY = currentVector.getY();
		Vector2D nextVector = null;
			if (direction == V) {
				FINAL int X = cX;
				if (+Direction == true) {
>>>>>>> branch 'master' of https://gitlab.com/cs230g34/a2.git
					nY = cY++;
					nextVector = (nX,nY);
				} else {
					nY = cY--;
					//nextVector = (nX,nY);
				}
<<<<<<< HEAD
			} else if(direction == "h") {
				Y = cY;
				if (increaseDirection == true) {
=======
				else {
					nY = cY--
							nextVector = (nX,nY);
				}
			
			}
			else { // direction == H
				FINAL int Y = cY;
				if (+Direction == true) {
>>>>>>> branch 'master' of https://gitlab.com/cs230g34/a2.git
					nX = cX++;
					//nextVector = (nX,nY);
				} else {
					nX = cX--;
					//nextVector = (nX,nY);
				}
				if (isValidMove(nextVector) == false) {
					if (increaseDirection == false); {
						increaseDirection = true;
						nextMove();
					}
				} else {
					increaseDirection = false;
					nextMove();
				} 
				return nextVector;
				
			}
		}
} 