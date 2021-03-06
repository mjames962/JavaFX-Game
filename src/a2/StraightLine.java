package a2;

/**
 * This enemy moves in a straight line.
 * @author Darius Thomas, James Colebourn and Tom Wood
 * @version 1.0
 * */
public class StraightLine extends Entity {

	private static final int LINE_ID = 4;
	private static final String SPRITE = 
			"a2/resources/stock photos/Straight_Line_Enemy.png";
	private boolean increaseDirection = true;

	/**
	 * Constructs the StraightLine class.
	 * @param enemyID the ID of the enemy
	 * @param currentVector the position of the enemy
	 * @param level the current level in progress
	 * 	 
	 */
	public StraightLine(Vector2D currentVector, int enemyID, Level level) {
		super(currentVector);
		setEntityID(LINE_ID);
	}
	/**
	 * Getter for the StraightLine Enemy sprite.
	 * @return returns the StraightLine Enemy sprite
	 */
	public String getSprite() {
		return SPRITE;
	}

	/**
	 * Establishes the next move for the enemy.
	 * @return nextVector the requested next cell to move to
	 */
	public Vector2D nextMove() {
		int cX = getCurrentVector().getX();
		int cY = getCurrentVector().getY();
		Vector2D nextVector = new Vector2D(cX, cY);
		String direction = "v";
		if (direction .equals("v")) {
			if (increaseDirection) {
				nextVector.set(cX, ++cY);
			} else {
				nextVector.set(cX, --cY);
			}
		} else {
			//entity is moving horizontally
			if (increaseDirection) {
				nextVector.set(++cX, cY);
			} else {
				nextVector.set(--cX, cY);
			}
		}
		if (!this.isValidMove(nextVector)) {
			increaseDirection = !increaseDirection;
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