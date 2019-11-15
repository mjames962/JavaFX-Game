package a2;

/**
 * This hold the player class.
 * @author Darius Thomas and James Colebourn
 * @version 1.0
 */

public class Player extends Entity {
	
	private boolean alive;
	private int inventory[];
	
	/**
	 * Constructor for the player.
	 * @param x the x coordinate of the player
	 * @param y the y coordinate of the player
	 * @param alive the field to determine whether the player can continue
	 * @param inventory the inventory of the player
	 */
	
	/**
	 * Constructs the player object.
	 * @param vector Inherited from entity. Holds player coordinates
	 * @param entityID Inherited from entity. Holds the ID of the player
	 * @param alive Determines if the player can continue
	 * @param inventory the collection of collectibles the player has
	 */
	public Player(Vector2D vector, int entityID, boolean alive,
			int inventory[]) {
		super(vector, entityID);
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
		
	}
	
	/**
	 * Ensures the player has the correct collectible.
	 * @param item the collectible being picked up
	 */
	public void hasItem(int item) {
		
	}
	
	/**
	 * Deals with the input of the player.
	 * @param input The input of the player
	 */
	public void handleInput(Enum<?> input) {
		
	}
	
	/**
	 * Handles invalid moves.
	 * @param direction The direction the player is moving
	 */
	public void attemptMove(Enum<?> direction) {
		
	}
	
	/**
	 * Clears the player's inventory.
	 */
	public void clearInventory() {
		
	}
	
}