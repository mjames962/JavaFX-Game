package a2;

import a2.Player.ShootDirection;
import cell.Cell;
import cell.DaggerCell;

public class Dagger extends Entity {
	
	private static final String 			
		SPRITE = "a2/resources/stock photos/Dagger.png";
	private int direction = -1; //0=up,1=right,2=down,3=left
	private boolean move = true;
	/**		 * An item of type blue key.
	 */
	public Dagger(Vector2D pos) {
		super(pos);
		setEntityID(10);
	}
	
	@Override
	public void setDirection(int dir) {
		direction = dir;
	}
	
	
	/**
	 * Returns the itemID.		
	 * @return gives the itemID of 3
	 */
	
	/**
	* Returns the sprite.
	* @return gives the sprite
	*/
	public String getSprite() {
		return SPRITE;
	}
	
	@Override
	public void move() {
		if (move) {
			int nextX = getCurrentVector().getX() + getMoveModifier(true);
			int nextY = getCurrentVector().getY() + getMoveModifier(false);
			if (isValidMove(new Vector2D(nextX, nextY))) {
				getCurrentVector().set(nextX, nextY);;
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
	
	public int getMoveModifier(boolean isX) {
		switch (direction) {
			case 0:
				if (isX) {
					return 0;
				} else {
					return 1;
				}
			case 1:
				if (isX) {
					return 1;
				} else {
					return 0;
				}
			case 2:
				if (isX) {
					return 0;
				} else {
					return -1;
				}
			case 3:
				if (isX) {
					return -1;
				} else {
					return 0;
				}
			default:
				return 0;
			
		}
	}
		
	public int getDirection(ShootDirection dir) {
		switch (dir) {
			case UP:
				return 0;
			case DOWN:
				return 2;
			case LEFT:
				return 3;
			case RIGHT:
				return 1;
			default:
				return 0;	
		}
	}
		

}
