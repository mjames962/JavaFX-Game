package a2;

/**
 * This enemy will locate the player, but will include invalid moves.
 * @author Darius Thomas, James Colebourn
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
		super(currentVector);
		setEntityID(3);
	}
	
	
	/**
	 * The method to determine the enemy's next move.
	 * @return returns nextVector, a Vector2D for the next 
	 *                               location the enemy should move to
	 */
	private Vector2D nextMove() {
		/*
		  pX the extracted x coordinate from the player's current vector
		  pY the extracted y coordinate from the player's current vector
		  cX the extracted x coordinate from the enemy's current vector
		  cY the extracted y coordinate from the enemy's current vector
		 */
		Vector2D playerPos = Level.getCurrentLevel().getPlayer().getVector();
		int pX = playerPos.getX();
		int pY = playerPos.getY();
		int cX = getCurrentVector().getX();
		int cY = getCurrentVector().getY();
		if (Math.abs((pX - cX)) > Math.abs((pY - cY))) {
			if (pX > cX) {      
				++cX;
				if (isValidMove(new Vector2D(cX, cY))) {	
					return new Vector2D(cX, cY); //enemy moves right
				}
			} else {        
				--cX; //enemy moves left
				if (isValidMove(new Vector2D(cX, cY))) {	
					return new Vector2D(cX, cY); 
				} 
			}
		} else {
			if (pY > cY) {      
				++cY; //enemy moves up
				if (isValidMove(new Vector2D(cX, cY))) {	
					return new Vector2D(cX, cY); 
				} 
			} else {         
				--cY; //enemy moves down
				if (isValidMove(new Vector2D(cX, cY))) {	
					return new Vector2D(cX, cY); 
				} 
			}
		}
		return getCurrentVector();             //returns vector of chosen move
	
	}
	/**
	 * Returns the sprite for the entity.
	 * @return gives the entity sprite
	 */
	public String getSprite() {
		return SPRITE;
	}
	/**
	 * Moves the Enemy.
	 */
	public void move() {
		setCurrentVector(nextMove());
	}
	
	

	
}