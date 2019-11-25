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
	private int tokenCount;
	/**
	 * Constructs the player object.
	 * @param entityID = 0, defines the entity as a player type
	 * @param alive Determines if the player can continue
	 * @param inventory the collection of collectible(s) the player has
	 * @param currentVector the current location of the player
	 */
	public Player(Vector2D currentVector, int entityID, boolean alive,
			ArrayList<Item> inventory) {
		super(currentVector, entityID);
		this.alive = alive;
		this.inventory = inventory;
		this.tokenCount = 0;
	}
	/**
	 * the method for enacting a movement specified by the player.
	 * @param currentVector is the current position of the player
	 * @param input is the intended movement direction of the player
	 * @return returns an updated position for the player
	 */
	
	public Vector2D move(Vector2D currentVector, Direction input) {
		int cX = currentVector.getX();
		int cY = currentVector.getY();
		Vector2D nextVector = null;
		switch (input) {
			case UP:
				nextVector = new Vector2D(cX, cY++);
				break;
			case DOWN:
				nextVector = new Vector2D(cX, cY--);
				break;
			case LEFT:
				nextVector = new Vector2D(cX--, cY);
				break;
			case RIGHT:
				nextVector = new Vector2D(cX++, cY);
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
	public boolean isValidMove(Vector2D currentVector) {
		int cX = currentVector.getX();
		int cY = currentVector.getY();
		
		String cellType = (Level.getCellAt(cX, cY).cellName);
		
		switch (cellType) {
			case "Wall": //Wall
				return false;
				break;
			case "TokenDoor": //Token Door
				if (TokenDoor.meetsRequirement(tokenCount)) {
					return true;
				}
				else {
					return false;						
				}

				break;
			case "Door": //blue door
				return true;
				return false;
				break;
			case "Fire":
				;
				break;
			case "FireBoots":
				;
				break;
			case "Water":
				;
				break;
			case "Flippers":
				;
				break;
			default:
				return true;
				break;
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
	
	public int getTokens() {
		return tokenCount;
	}
	
}