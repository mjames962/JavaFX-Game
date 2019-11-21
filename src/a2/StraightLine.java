package a2;

/**
 * This enemy moves in a straight line.
 * @author Darius Thomas and James Colebourn
 * @version 1.0
 * */
public class StraightLine extends Entity {

	private static final String FINAL = null;
	private Vector2D currentVector;
	//private boolean +Direction = true;
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
	 
/*

		public Vector2D nextMove() {
		int cX = currentVector.getX();
		int cY = currentVector.getY();
		Vector2D nextVector = null;
			if (direction == v) {
				FINAL int X = cX;
				if (+Direction == true) {
					nY = cY++;
					nextVector = (nX,nY);
				}
				else {
					nY = cY--
							nextVector = (nX,nY);
				}
			
			}
			else { // direction == h
				FINAL int Y = cY;
				if (+Direction == true) {
					nX = cX++;
					nextVector = (nX,nY);
				}
				else {
					nX = cX--;
					nextVector = (nX,nY);
				}
			}
			

			if (isValidMove(this.nextVector) == False) {
				if (+Direction == false); {
					+Direction = True;
					nextMove();
				}
				else {
					+Direction = False;
					nextMove();
				}
			}
			else {
				return this.nextVector;
			}
		} 
	*/
} 
