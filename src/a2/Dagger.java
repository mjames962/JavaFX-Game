package a2;

import a2.Player.ShootDirection;
import cell.Cell;
import cell.DaggerCell;
/**
 * The Class object for the moving dagger entity.
 * @author Tom Wood
 * @version 1.2
 */
public class Dagger extends Entity {
	public static final String SPRITE = "a2/resources/stock photos/Dagger.png";
	private static final int DAGGER_ID = 10;	
	private int direction = -1; //0=up,1=right,2=down,3=left
	private boolean move = true;
	/**		 
	 * Holds directions for the moving Dagger.
	 * @param pos holds the position
	 * @param dir holds the direction
	 */
	public Dagger(Vector2D pos, ShootDirection dir) {
		super(pos);
		setEntityID(DAGGER_ID);
	}
	
	public Dagger(Vector2D pos, int dir) {
		super(pos);
		setEntityID(DAGGER_ID);
		direction = dir;
	}
	
	@Override
	public void setDirection(int dir) {
		direction = dir;
	}
	

	
	/**
	* Returns the sprite.
	* @return gives the sprite
	*/
	public String getSprite() {
		return SPRITE;
	}

    /**
     * Moves the dagger in the intended direction.
     */
	
	@Override
	public void move() {
		if (move) {
			int nextX = getCurrentVector().getX() + getMoveModifier(true);
			int nextY = getCurrentVector().getY() + getMoveModifier(false);
			if (isValidMove(new Vector2D(nextX, nextY))) {
				getCurrentVector().set(nextX, nextY);
			} else {
				move = false;
				setRemove(true);
				int curX = getCurrentVector().getX();
				int curY = getCurrentVector().getY();
				Cell[][] level = Level.getCurrentLevel().getLevel();
				level[curX][curY] = new DaggerCell(new Vector2D(curX, curY));
			}
		}
	}
	/**
	 * Checks the direction and calculates the next move.
	 * @param isX Checks if direction of travel is Vertical or Horizontal
	 * @return gives next modifier to reach the next location
	 */
	public int getMoveModifier(boolean isX) {
		final int UP_DIR = 0;
		final int DOWN_DIR = 2;
		final int LEFT_DIR = 1;
		final int RIGHT_DIR = 3;
		switch (direction) {
			case UP_DIR:
				if (isX) {
					return 0;
				} else {
					return 1;
				}
			case LEFT_DIR:
				if (isX) {
					return 1;
				} else {
					return 0;
				}
			case DOWN_DIR:
				if (isX) {
					return 0;
				} else {
					return -1;
				}
			case RIGHT_DIR:
				if (isX) {
					return -1;
				} else {
					return 0;
				}
			default:
				return 0;
			
		}
	}
	/**
	 * Getter for the direction of movement of the entity.
	 * @param dir reads in the direction
	 * @return gives the case for direction of travel
	 */
	public int getDirection(ShootDirection dir) {
		final int UP_DIR = 0;
		final int DOWN_DIR = 2;
		final int LEFT_DIR = 1;
		final int RIGHT_DIR = 3;
		switch (dir) {
			case UP:
				return UP_DIR;
			case DOWN:
				return DOWN_DIR;
			case LEFT:
				return LEFT_DIR;
			case RIGHT:
				return RIGHT_DIR;
			default:
				return 0;	
		}
	}
	
	
	/**
	 * Gets the direction the dagger is
	 * Travelling in.
	 * 
	 * @return the direction the dagger is moving
	 */
	public int getDirection() {
        return this.direction;
    }

}
