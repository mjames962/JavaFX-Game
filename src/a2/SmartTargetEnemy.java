package a2;

import java.util.ArrayList;

import cell.Cell;

//import java.util.Queue;
//import java.util.LinkedList;
/**
 * The enemy type that follows the A* algorithm to hunt the player.
 * @author James Colebourn
 * @version 1.0
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
	 * 
	 * @param x next X coordinate in path
	 * @param y next y coordinate in path
	 * @param visited The visited locations in the map
	 */
	
	public int shortPathFind(int x, int y, boolean[][] visited, int minDistance, int distance) {
		int playerX = Level.getCurrentLevel().getPlayer().getVector().getX();
		int playerY = Level.getCurrentLevel().getPlayer().getVector().getY();
		if ((x == playerX && y == playerY) || distance >= maxViewDistance) {
			if ((Integer.min(distance, minDistance) == distance)) {
				nextMove = moveList.get(moveList.size() - distance + 1);
			} 
			return Integer.min(distance, minDistance);
		}
		
		visited[x][y] = true;
		moveList.add(new Vector2D(x, y));
	
		//Move Right
		if (isValidMove(new Vector2D(x + 1, y)) && isInMap(x + 1, y)) {
			minDistance = shortPathFind(x + 1,  y, visited, minDistance,
					distance + 1);
		}
		//Move Left
		if (isValidMove(new Vector2D(x - 1, y)) && isInMap(x + 1, y)) {
			minDistance = shortPathFind(x - 1,  y, visited, minDistance,
					distance + 1);
		}
		//Move Up
		if (isValidMove(new Vector2D(x, y + 1)) && isInMap(x + 1, y)) {
			minDistance = shortPathFind(x,  y + 1, visited, minDistance,
					distance + 1);

		}
		//Move Down
		if (isValidMove(new Vector2D(x, y - 1)) && isInMap(x + 1, y)) {
			minDistance = shortPathFind(x,  y - 1, visited, minDistance,
					distance + 1);
		}
		visited[x][y] = false;
		moveList.remove(moveList.size() - 1);
		
		return minDistance;
	}
	
	public boolean isInMap(int x, int y) {
		Level level = Level.getCurrentLevel();
		return (x >= 0 && x < level.levelXLength() &&
				y >= 0 && y < level.levelYLength());
	}
	
	public void move() {
		moveList = new ArrayList<>();
		boolean[][] visited = new boolean 
				[Level.getCurrentLevel().levelXLength()]
				  [Level.getCurrentLevel().levelYLength()];
		System.out.println(shortPathFind(currentVector.getX(), 
				currentVector.getY(), visited, 
				  maxViewDistance, 0));
//		System.out.println(nextMove.getX() + " final " + nextMove.getY());
		this.currentVector = nextMove;
	}
//	private Queue<Vector2D> curShortestPath = new LinkedList<>();

	
//	private seekAlgorithm(Vector2D currentPosition, Vector2D playerVector){
//		return Vector2D ;
//	}
	
	
//	private void 
	
	
	


}
