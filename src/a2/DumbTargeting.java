package a2;

/**
 * This enemy will locate the player, but will include invalid moves.
 * @author Darius Thomas and James Colebourn
 * @version 1.0
 * */
public class DumbTargeting extends Entity {
	
	private static final String SPRITE 
		= "a2/resources/stock photos/Dumb_Target_Enemy.png";
	
	/**
	 * Constructs the WallFollowing class.
	 * @param currentVector the position of the enemy
	 * @param enemyID the ID of the enemy
	 * @param level stores the current level
	 */
	public DumbTargeting(Vector2D currentVector, int enemyID, Level level) {
		super(currentVector, enemyID, level);
	}
	
	
	/**
	 * The method to determine the enemy's next move.
	 * @param playerVector the position of the player
	 * @param currentVector the position of the enemy
	 * @return returns nextVector, a Vector2D for the next 
	 *                               location the enemy should move to
	 */
	@SuppressWarnings("unused")
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
	/**
	 * Returns the sprite for the entity.
	 * @return gives the entity sprite
	 */
	public String getSprite() {
		return SPRITE;
	}
	
	
}