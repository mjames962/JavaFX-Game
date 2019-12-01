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
	
	private static final String SPRITE 
		= "a2/resources/stock photos/Wall_Following_Enemy.png";
	private boolean increaseVDirection = true;
	private boolean increaseHDirection = true;
	private int direction = 0; //0=left,1=up,2=right,3=down
	private boolean escapeMove = false;
	
	
	/**
	 * Constructs the Wall Following Enemy.
	 * @param currentVector the position of the enemy
	 * @param enemyID the ID of the enemy
	 * @param direction holds the direction of travel
	 * @param level holds the current level
	 */
	public WallFollowing(Vector2D currentVector, 
			int enemyID, String direction, Level level) {
		super(currentVector, enemyID, level);
		enemyID = 2;
		currentVector = this.currentVector;

		
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
		int cX = currentVector.getX();
		int cY = currentVector.getY();
		Vector2D nextVector = new Vector2D(cX + modifier(true),
				cY + modifier(false));
		Vector2D leftWall = new Vector2D(cX + findLeft(true),
				cY + findLeft(false));
		
		
		
		
		if (isValidMove(2, leftWall)) {
			decreaseDircetion();
			return leftWall;
		} else if (isValidMove(2, nextVector)) {
			return nextVector;
		} else {
			increaseDirection();
			return nextMove();
		}
	}
	
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
			case 3:
				if (isX) {
					return 0;
				} else {
					return -1;
				}
			default:
				return 0;
				
		}
	}
	
	
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
			case 3:
				if (isX) {
					return 1;
				} else {
					return 0;
				}
			default:
				return 0;
				
		}
	}
	
	public void increaseDirection() {
		if (direction == 3) {
			direction = 0;
		} else {
			++direction;
		}
	}
	
	public void decreaseDircetion() {
		if (direction == 0) {
			direction = 3;
		} else {
			--direction;
		}
	}
	
		
	public void move() {
		currentVector = nextMove();
	}
	
}
