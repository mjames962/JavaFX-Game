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
	
	/**
	 * Constructs the player object.
	 * @param pos Inherited from entity. Holds player coordinates
	 * @param playerID Inherited from entity. Holds the ID of the player
	 * @param alive Determines if the player can continue
	 * @param inventory the collection of collectibles the player has
	 */
	public Player(Vector2D pos, int playerID, boolean alive,
			ArrayList<Item> inventory) {
		super(pos, playerID);
		this.alive = alive;
		this.inventory = inventory;
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