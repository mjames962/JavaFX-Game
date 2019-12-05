package a2;

import a2.Player.ShootDirection;

/**
 * This hold the Entity superclass.
 * @author Darius Thomas and James Colebourn
 * @version 1.0
 */

public class Entity implements Sprite {
	


	private static final String SPRITE = 
			"a2/resources/stock photos/Straight_Line_Enemy.png";
	private Vector2D currentVector;
	private Vector2D nextVector;
	private Level level;	
	private Vector2D vector;
	private int entityID;
	private boolean remove = false;


	/**
	 * Constructor for the Entity superclass.
	 * @param vector the entity coordinates
	 */
	public Entity(Vector2D vector) {
		this.setCurrentVector(vector);
		this.entityID = entityID;
		this.level = level;
	}
	/**
	 * Method to be overwritten for getting the sprite of an entity.
	 * @return gives the Sprite
	 */
	public String getSprite() {
		return SPRITE;
	}
	
	/**
	 * Returns the current level.
	 * @return gives the current level
	 */
	public Level getLevel() {
		return this.level;
	}
	
	public boolean getRemove() {
		return remove;
	}
	
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
	
	public void setDirection(int dir) {
		
	}
	
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
			if (Level.getCurrentLevel().getCellAt(nextVector).cellName()
					== "Ground") {
				return true;						//Ground Cell class
			} else {
				return false;
			}
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
	
	
	public void hasHitDagger() {
		if (this.getEntityID() != 10) {
			for (Entity ent : Level.getCurrentLevel().getEntityList()) {
				if (ent != this) { 
					if ((ent.getEntityID() == 10) && 
						(this.getCurrentVector() == ent.getCurrentVector())) {
						remove = true;
					}
				}
			}
		}
	}
	/**
	 * @return the currentVector
	 */
	public Vector2D getCurrentVector() {
		return currentVector;
	}
	/**
	 * @param currentVector the currentVector to set
	 */
	public void setCurrentVector(Vector2D currentVector) {
		this.currentVector = currentVector;
	}
	
	
}
