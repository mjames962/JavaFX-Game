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
					nY = cY++;
					nextVector = (nX,nY);
				} else {
					nY = cY--;
					//nextVector = (nX,nY);
				}
			} else if(direction == "h") {
				Y = cY;
				if (increaseDirection == true) {
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