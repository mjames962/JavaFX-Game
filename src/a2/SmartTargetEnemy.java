package a2;

import java.util.ArrayList;

import cell.Cell;

//import java.util.Queue;
//import java.util.LinkedList;
/**
 * The enemy type that follows the A* algorithm to hunt the player.
 * @author James Colebourn, Tom Wood
 * @version 2.7
 */
public class SmartTargetEnemy extends Entity {
	
	private static final String SPRITE 
		= "a2/resources/stock photos/Smart_Target_Enemy.png";
	private final int maxViewDistance = 10;
	private Vector2D nextMove;
	private ArrayList<Vector2D> moveList = new ArrayList<>();

//	private Vector2D currentVector = null;
// 	 * @param curShortestPath holds a queue of vectors for next moves	
	/**
	 * Required parameters for a Smart Targeting Enemy.
	 * @param currentVector holds a Vector2D for current position
	 * @param enemyID holds the id for the enemy on the level
	 * @param level holds the current level
	 */
	public SmartTargetEnemy(Vector2D currentVector, int enemyID, Level level) {
		super(currentVector, enemyID, level);
		nextMove = new Vector2D(currentVector.getX(), currentVector.getY());
	}
	/**
	 * returns the sprite for the entity.
	 * @return gives the sprite for the entity
	 */
	public String getSprite() {
		return SPRITE;
	}
	
	/**
	 * Finds the shortest Path and the route it will take to get there.
	 * @param x The current x coordinate that is being checked.
	 * @param y The current x coordinate that is being checked.
	 * @param minDistance The minimum distance to the player found so far.
	 * @param distance the current distance from the original position in 
	 * the current search.
	 * @return The minimum distance from the enemy to player found so far.
	 */
	
	public int shortPathFind(int x, int y, int minDistance, int distance) {
		int playerX = Level.getCurrentLevel().getPlayer().getVector().getX();
		int playerY = Level.getCurrentLevel().getPlayer().getVector().getY();
		if ((x == playerX && y == playerY) || distance >= maxViewDistance) {
			if ((Integer.min(distance, minDistance) == distance)) {
				nextMove = moveList.get(moveList.size() - distance + 1);
			} 
			return Integer.min(distance, minDistance);
		}
		
		moveList.add(new Vector2D(x, y));
	
		//Move Right
		if (isValidMove(new Vector2D(x + 1, y)) && isInMap(x + 1, y)) {
			minDistance = shortPathFind(x + 1,  y, minDistance,
					distance + 1);
		}
		//Move Left
		if (isValidMove(new Vector2D(x - 1, y)) && isInMap(x + 1, y)) {
			minDistance = shortPathFind(x - 1,  y, minDistance,
					distance + 1);
		}
		//Move Up
		if (isValidMove(new Vector2D(x, y + 1)) && isInMap(x + 1, y)) {
			minDistance = shortPathFind(x,  y + 1, minDistance,
					distance + 1);

		}
		//Move Down
		if (isValidMove(new Vector2D(x, y - 1)) && isInMap(x + 1, y)) {
			minDistance = shortPathFind(x,  y - 1, minDistance,
					distance + 1);
		}
		moveList.remove(moveList.size() - 1);
		
		return minDistance;
	}
	
	/**
	 * Finds if the x and y coordinates are inside the level.
	 * @param x The x coordinate being checked.
	 * @param y The y coordinate being checked.
	 * @return If both coordinates are inside the level.
	 */
	
	public boolean isInMap(int x, int y) {
		Level level = Level.getCurrentLevel();
		return (x >= 0 && x < level.levelXLength() &&
				y >= 0 && y < level.levelYLength());
	}
	
	
	/**
	 * Moves the enemy to their new position.
	 */
	public void move() {
		moveList = new ArrayList<>();
		if (!getCurrentVector().equals(Level.getCurrentLevel().
				getPlayer().getVector())) {
			System.out.println(shortPathFind(getCurrentVector().getX(), 
						getCurrentVector().getY(), 
						maxViewDistance, 0));

			this.setCurrentVector(nextMove);
		}
	}

	
	
	


}
