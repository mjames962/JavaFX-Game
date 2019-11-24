package a2;

import java.util.ArrayList;

/**
 * This hold the player class.
 * @author Darius Thomas and James Colebourn
 * @version 1.0
 */

public class Player extends Entity {
	
	/**
	 * Set of collectible.
	 * @author 	George Williams-Walton
	 */
	public enum Item {
		RED_KEY,
		BLUE_KEY,
		GREEN_KEY,
		FLIPPERS,
		FIRE_BOOTS,
	}
	
	/**
	 * Sets of directions the player can move.
	 * @author Darius Thomas
	 */
	public enum Direction {
		UP,
		DOWN,
		LEFT,
		RIGHT,
	}
	
	private boolean alive;
	private ArrayList<Item> inventory;
	private Vector2D nextVector;
	private Vector2D currentVector;
	/**
	 * Constructs the player object.
	 * @param entityID = 0, defines the entity as a player type
	 * @param alive Determines if the player can continue
	 * @param inventory the collection of collectibles the player has
	 * @param currentVector the current location of the player
	 */
	public Player(Vector2D currentVector, int entityID, boolean alive,
			ArrayList<Item> inventory) {
		super(currentVector, entityID);
		this.alive = alive;
		this.inventory = inventory;
	}
	/**
	 * the method for enacting a movement specified by the player.
	 * @param currentVector is the current position of the player
	 * @param input is the intended movement direction of the player
	 * @return returns an updated position for the player
	 */
	
	public Vector2D move(Vector2D currentVector, direction input) {
		int cX = currentVector.getX();
		int cY = currentVector.getY();
		switch (input) {
			case 0:
				input = UP;
				Vector2D nextVector = new Vector2D(cX, cY++);
				break;
			case 1:
				input = DOWN;
				Vector2D nextVector = new Vector2D(cX, cY--);
				break;
			case 2:
				input = LEFT;
				Vector2D nextVector = new Vector2D(cX--, cY);
				break;
			case 3:
				input = RIGHT;
				Vector2D nextVector = new Vector2D(cX++, cY);
				break;
			default:
				input = null;
		}
		isValidMove(0, nextVector);
		return null; //needs changing from null to something else to 
		             //change the players location and update the map
	}
	
	/**
	 * Overrides the isValidMove method in Entity for player entities.
	 * @return returns a boolean for if the requested move is valid
	 */	
	public boolean isValidMove() {
		if (getCellAt(nextVector) == Wall) {
			return false;
		}
		else if (getCellAt(nextVector) == Door) {
			if (Door.hasItem(inventory()) = true) {
				return true;
			}
			else {
				return false;
			}
			
		}
		else {
			return true;
		}
		
		
	}
	
	/**
	 * This method handles the death of the player.
	 */
	
	private void playerDeath() {
		alive = false;
	}
	
	/**
	 * Puts an item into the player's inventory.
	 */
	
	public void pickupItem() {
		inventory.add(null); //Needs fixing
	}
	
	/**
	 * Ensures the player has the correct collectible.
	 * @param item the collectible being picked up
	 * @return true If the item is in the inventory
	 * or false if the item isn't present
	 */
	public boolean hasItem(Item item) {
		if (inventory.contains(item)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Deals with the input of the player.
	 * @param input The input of the player
	 */
	public void handleInput(Direction input) {
		//TODO
	}
	
	/**
	 * Handles invalid moves.
	 * @param direction The direction the player is moving
	 */
	public void attemptMove(Direction direction) {
		//TODO
	}
	
	/**
	 * Clears the player's inventory.
	 */
	public void clearInventory() {
		inventory.clear();
	}
	
}