package a2;

/**
 * This hold the Entity superclass.
 * @author Darius Thomas and James Colebourn
 * @version 1.0
 */

public class Entity implements Sprite {
	
	private Vector2D vector;
	private int entityID;
	protected Vector2D currentVector;
	protected Vector2D nextVector;
	private static final String SPRITE = "a2/resources/stock photos/Straight_Line_Enemy.png";
	
	
	public String getSprite() {
		return SPRITE;
	}
	

	protected Level level;
	/**
	 * Constructor for the Entity superclass.
	 * @param vector the entity coordinates
	 * @param entityID the ID of the entity
	 * @param currentVector current location
	 * @param nextVector requested next position
	 */
	public Entity(Vector2D vector, int entityID, Level level) {
		this.currentVector = vector;
		this.entityID = entityID;
		this.level = level;
	}

	/**
	 * gets the coordinates of the entity. 
	 * @return vector 
	 */
	
	public Vector2D getVector() {
		return currentVector;
	}

	
	/**
	 * Retrieves the entity ID.
	 * @return entityID
	 */
	public int getEntityID() {
		return entityID;
	}
	
	/**
	 * Sets the entity ID.
	 * @param entityID The ID of the entity
	 */
	public void setEntityID(int entityID) {
		this.entityID = entityID;
	}
	
	/**
	 * determines validity of moving onto the requested cell.
	 * @return returns a boolean for if the requested move is legal
	 * @param entityID the unique identifier for an entity type
	 * @param nextVector the intended next location for the entity
	 */
	
	public boolean isValidMove(int entityID, Vector2D nextVector, Level level) {
		if (entityID != 0) { // Enemies

			if (level.getCellAt(nextVector) == Ground) { //getCellAt from level
				return true;						//Ground Cell class
			} else {
				return false;
			}
		} else { //player class will override with access restraints.
			return false;
		}
	}
	
	
	
	/**
	 * This is responsible for handling moves. 
	 */
	public void move() {
		
	}
}
