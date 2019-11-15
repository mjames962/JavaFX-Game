package a2;

/**
 * This hold the Entity superclass.
 * @author Darius Thomas and James Colebourn
 * @version 1.0
 */

public class Entity {
	
	private Vector2D vector;
	private int entityID;
	
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
	 * This is responsible for handling moves. 
	 */
	public void move() {
		
	}
}
