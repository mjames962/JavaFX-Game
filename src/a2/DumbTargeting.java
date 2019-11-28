package a2;

/**
 * This enemy will locate the player, but will include invalid moves.
 * @author Darius Thomas and James Colebourn
 * @version 1.0
 * */
public class DumbTargeting extends Entity {
	
	/**
	 * Constructs the WallFollowing class.
	 * @param currentVector the position of the enemy
	 * @param enemyID the ID of the enemy
	 */
	public DumbTargeting(Vector2D currentVector, int enemyID, Level level) {
		super(currentVector, enemyID, level);
	}
	
	
	/**
	 * The method to determine the enemy's next move.
	 * @param playerVector the position of the player
	 * @param currentVector the position of the enemy
	 * @return returns nextVector, a Vector2D for the next 
	 * location the enemy should move to
	 */
	private Vector2D comparePositions(Vector2D playerVector,
			Vector2D currentVector) {
		/**
		 * pX the extracted x coordinate from the player's current vector
		 * pY the extracted y coordinate from the player's current vector
		 * cX the extracted x coordinate from the enemy's current vector
		 * cY the extracted y coordinate from the enemy's current vector
		 */
		int pX = playerVector.getX();
		int pY = playerVector.getY();
		int cX = currentVector.getX();
		int cY = currentVector.getY();
		Vector2D nextVector;
		if ((pX - cX) > (pY - cY)) {
			if (pX > cX) { 
				cX = cX++;                              //enemy moves right
				nextVector = new Vector2D(cX, cY); 
			} else {
				cX = cX--;                              //enemy moves left
				nextVector = new Vector2D(cX, cY); 
			}
		} else {
			if (pY > cY) {
				cY = cY++;                              //enemy moves up
				nextVector = new Vector2D(cX, cY); 
			} else {
				cY = cY--;                              //enemy moves down
				nextVector = new Vector2D(cX, cY);
			}
		}
		return nextVector;                       //returns vector of chosen move
	
	}
}