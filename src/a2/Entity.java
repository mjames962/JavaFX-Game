package a2;

/**
 * This hold the Entity superclass.
 * @author Darius Thomas and James Colebourn
 * @version 1.0
 */

public class Entity {
	
	private Vector2D vector;
	private int entityID;
	protected Vector2D currentVector;
	protected Vector2D nextVector;
	
	/**
	 * Constructor for the Entity superclass.
	 * @param vector the entity coordinates
	 * @param entityID the ID of the entity
	 */
	public Entity(Vector2D vector, int entityID) {
		this.vector = vector;
		this.entityID = entityID;
	}

	/**
	 * gets the coordinates of the entity. 
	 * @return vector 
	 */
	
	public Vector2D getVector() {
		return vector;
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
	 */
	
	public boolean isValidMove(int entityID) {
		if (entityID != 0) { // Enemies

			if (getCellAt(nextVector) == ground) { 	//getCellAt from level
				return true;						//Ground Cell class
			}
			else {
				return false;
			}
		}
		else { //player class will override with access restraints.
			return false;
		}
	}
	
	
	
	/**
	 * This is responsible for handling moves. 
	 */
	public void move() {
		
	}
}
