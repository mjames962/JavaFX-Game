package a2;

/**
 * This enemy moves in a straight line.
 * @author Darius Thomas and James Colebourn
 * @version 1.0
 * */
public class StraightLine extends Entity {

	private static final int MAGIC4 = 4;
	private static final String SPRITE = 
			"a2/resources/stock photos/Straight_Line_Enemy.png";
	private boolean increaseDirection = true;
	private String direction = "v";
	
	/**
	 * Constructs the StraightLine class.
	 * @param enemyID the ID of the enemy
	 * @param currentVector the position of the enemy
	 * @param level the current level in progress
	 * 	 
	 */
	public StraightLine(Vector2D currentVector, int enemyID, Level level) {
		super(currentVector);
		setEntityID(MAGIC4);
	}
	/**
	 * Getter for the StraightLine Enemy sprite.
	 * @return returns the StraightLine Enemy sprite
	 */
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
	/**
	 * Enacts the next move.
	 */
	public void move() {
		this.setCurrentVector(nextMove());
	}
}
			