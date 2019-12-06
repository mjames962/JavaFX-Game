package a2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * This enemy follows the wall to its left
 * placed on the lower and right side of the wall.
 * @author James Colebourn
 * @version 1.0
 */
public class WallFollowing extends Entity {
	private static final int MAGIC3 = 3;
	private static final String SPRITE 
		= "a2/resources/stock photos/Wall_Following_Enemy.png";
	private int direction = 0; //0=left,1=up,2=right,3=down
	
	
	/**
	 * Constructs the Wall Following Enemy.
	 * @param currentVector the position of the enemy
	 * @param enemyID the ID of the enemy
	 * @param direction holds the direction of travel
	 * @param level holds the current level
	 */
	public WallFollowing(Vector2D currentVector, 
			int enemyID, Level level) {
		super(currentVector);
		setEntityID(2);
		currentVector = this.getCurrentVector();

		
	}
	/**
	 * method for getting the sprite.
	 * @return  gives the sprite for the entity
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
		Vector2D nextVector = new Vector2D(cX + modifier(true),
				cY + modifier(false));
		Vector2D leftWall = new Vector2D(cX + findLeft(true),
				cY + findLeft(false));
		if (isValidMove(leftWall)) {
			decreaseDircetion();
			return leftWall;
		} else if (isValidMove(nextVector)) {
			return nextVector;
		} else {
			increaseDirection();
			return nextMove();
		}
		
	}
	
	
	/**
	 * Finds the change in position after the next movement. 	
	 * @param isX Stores if the coordinate being sent is x or y.
	 * @return the change in position of the coordinate after the next move.
	 */
	public int modifier(boolean isX) {
		switch (direction) {
			case 0:
				if (isX) {
					return -1;
				} else {
					return 0;
				}
			case 1:
				if (isX) {
					return 0;
				} else {
					return 1;
				}
			case 2:
				if (isX) {
					return 1;
				} else {
					return 0;
				}
			case MAGIC3:
				if (isX) {
					return 0;
				} else {
					return -1;
				}
			default:
				return 0;
				
		}
	}
	
	
	/**
	 * Finds the difference in coordinate with the cell to
	 * the left of the Enemy. 
	 * @param isX Stores if the coordinate being sent is x or y.
	 * @return the difference in position between the coordinate sent and the 
	 * enemy.
	 */
	public int findLeft(boolean isX) {
		switch (direction) {
			case 0:
				if (isX) {
					return 0;
				} else {
					return -1;
				}
			case 1:
				if (isX) {
					return -1;
				} else {
					return 0;
				}
			case 2:
				if (isX) {
					return 0;
				} else {
					return 1;
				}
			case MAGIC3:
				if (isX) {
					return 1;
				} else {
					return 0;
				}
			default:
				return 0;
				
		}
	}
	
	/**
	 * Rotates the enemy 90 degrees clockwise.
	 */
	
	public void increaseDirection() {
		if (direction == MAGIC3) {
			direction = 0;
		} else {
			++direction;
		}
	}
	
	/**
	 * Rotates the enemy 90 degrees anti clockwise.
	 */
	
	public void decreaseDircetion() {
		if (direction == 0) {
			direction = MAGIC3;
		} else {
			--direction;
		}
	}
	
	
	/**
	 * Changes the current position to the next position the
	 * enemy will move to.
	 */
		
	public void move() {
		setCurrentVector(nextMove());
	}
	
}
