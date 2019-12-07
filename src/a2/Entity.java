package a2;

import a2.Player.ShootDirection;
import cell.Ground;

/**
 * This hold the Entity superclass.
 * @author Darius Thomas, James Colebourn and Tom Wood
 * @version 1.0
 */

public class Entity implements Sprite {
	


	private static final String SPRITE = 
			"a2/resources/stock photos/Straight_Line_Enemy.png";
	private Vector2D currentVector;
	private Level level;	
	private int entityID;
	private boolean remove = false;


	/**
	 * Constructor for the Entity superclass.
	 * @param vector the entity coordinates
	 */
	public Entity(Vector2D vector) {
		this.setCurrentVector(vector);
	}
	/**
	 * Method to be overwritten for getting the sprite of an entity.
	 * @return gives the Sprite
	 */
	public String getSprite() {
		return SPRITE;
	}
	
	/**
	 * Gets the direction the entity is moving.
	 * @return the direction the enemy is moving. If the direction does
	 * not change return -1.
	 */
	
	public int getDirection() {
		return -1;
		
	}
	
	
	
	/**
	 * Returns the current level.
	 * @return gives the current level
	 */
	public Level getLevel() {
		return this.level;
	}
	
	/**
	 * Gets the value of the remove boolean to see
	 * if the entity has been killed.
	 * @return if the enemy has been killed.
	 */
	
	public boolean getRemove() {
		return remove;
	}
	
	/**
	 * Gets the current position of the entity.
	 * @return the currentVector
	 */
	public Vector2D getCurrentVector() {
		return currentVector;
	}
	/**
	 * Sets the current position of the enemy.
	 * @param currentVector the currentVector to set
	 */
	public void setCurrentVector(Vector2D currentVector) {
		this.currentVector = currentVector;
	}
	
	/**
	 * Sets the value of remove to the specified
	 * value.
	 * @param re the value remove is being set to.
	 */
	
	public void setRemove(boolean re) {
		remove = re;
	}
	
	
	
	/**
	 * gets the coordinates of the entity. 
	 * @return vector 
	 */
	
	public Vector2D getVector() {
		return getCurrentVector();
	}

	
	/**
	 * Retrieves the entity ID.
	 * @return entityID
	 */
	public int getEntityID() {
		return entityID;
	}
	
	/**
	 * Sets the direction the enemy should move in.
	 * @param dir the new direction the enemy should move in
	 */
	
	public void setDirection(int dir) {
		
	}
	
	/**
	 * Gets the integer direction from a value
	 * of ShhotDirection.
	 * @param dir the direction you are finding the value for.
	 * @return the direction being moved. -1 if the enemy does not
	 * change direction.
	 */
	
	public int getDirection(ShootDirection dir) {
		return -1;
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
	 * @param nextVector the intended next location for the entity
	 */
	
	public boolean isValidMove(Vector2D nextVector) {
		if (this.entityID != 0) { // Enemies
				return Level.getCurrentLevel().getCellAt(nextVector) instanceof
                        Ground;
		} else { //player class will override with access restraints.
			return false;
		}
	}
	
	
	
	/**
	 * This is responsible for handling movement of entities. 
	 * 											Will be overwritten.
	 */
	public void move() {
		
	}
	
	/**
	 * Finds if the enemy has hit a dagger and sets remove
	 * to true if it has.
	 */
	public void hasHitDagger() {
		if (!(this instanceof Dagger)) {
			for (Entity ent : Level.getCurrentLevel().getEntityList()) {
				if (ent != this) { 
					if ((ent instanceof Dagger) && 
						(this.getCurrentVector().equals(
								ent.getCurrentVector()))) {
						remove = true;
					}
				}
			}
		}
	}
	
	
	
	
}
