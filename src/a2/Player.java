package a2;

import java.util.LinkedList;
import cell.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * This hold the player class.
 * 
 * @author Darius Thomas and James Colebourn
 * @version 1.0
 */

public class Player extends Entity {

	/**
	 * Sets of directions the player can move.
	 * 
	 * @author Darius Thomas
	 */
	public enum Direction {
		UP, DOWN, LEFT, RIGHT,
	}

	private boolean alive;
	private LinkedList<Item> inventory;
	private Vector2D nextVector;
	private Vector2D currentVector;
	private int tokenCount;

	/**
	 * Constructs the player object.
	 * 
	 * @param entityID
	 *            = 0, defines the entity as a player type
	 * @param alive
	 *            Determines if the player can continue
	 * @param inventory
	 *            the collection of collectible(s) the player has
	 * @param currentVector
	 *            the current location of the player
	 */
	public Player(Vector2D currentVector, int entityID, boolean alive,
			LinkedList<Item> inventory) {
		super(currentVector, entityID);
		this.alive = alive;
		this.inventory = inventory;
		this.tokenCount = 0;
	}

	/**
	 * the method for enacting a movement specified by the player.
	 * 
	 * @param currentVector
	 *            is the current position of the player
	 * @param input
	 *            is the intended movement direction of the player
	 * @return returns an updated position for the player
	 */

	public Vector2D move(Direction input) {
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
		return null; // needs changing from null to something else to
						// change the players location and update the map
		/*
		 * if (nextVector == Goal) { //end level }
		 */
	}

	/**
	 * Overrides the isValidMove method in Entity for player entities.
	 * @param level the given level currently loaded          
	 * @param nextVector the requested cell to move to
	 * @return returns a boolean for if the requested move is valid
	 */
	/*public boolean isValidMove(Vector2D nextVector, Level level) {
		int cX = nextVector.getX();
		int cY = nextVector.getY();
		Cell cell = level.getCellAt(cX, cY);
		String cellType = cell.cellName();
		// Class<?> typ = cell.getClass();
		// Class<Ground> = Ground.class;

		return isValidMove(cellType, nextVector, level);
	}*/
	/**
	 * Determines whether the requested move is valid.
	 * @param cellType the type of cell the player wants to move onto
	 * @param cellPos the position of the cell on the map
	 * @param level the level currently being completed
	 * @return a boolean to show whether the cell can be moved onto
	 */
	public Boolean isValidMove(Vector2D cellPos, Level level) {

		Cell cell = level.getCellAt(cellPos);
		String cellType = cell.cellName();
		
		switch (cellType) {
			case "Ground":
				return true;
			case "Wall":
				return false;
			case "TokenDoor":
				return this.openDoor((Door) cell, level);
			case "TokenCell":
				this.tokenCount++;
				// convert to ground
				return true;
			case "RedDoor":
				return this.openDoor((Door) cell, level);
			case "RedDoorKey":
				this.pickupItem(new RedKey(), cellPos, level);
				return true;
			case "GreenDoor":
				return this.openDoor((Door) cell, level);
			case "GreenDoorKey":
				this.pickupItem(new GreenKey(), cellPos, level);
				return true;
			case "BlueDoor":
				return this.openDoor((Door) cell, level);
			case "BlueDoorKey":
				this.pickupItem(new BlueKey(), cellPos, level);
				return true;
			case "Fire":
				// method checks
			case "Water":
				// method checks
			case "FireBootsCell":
				this.pickupItem(new FireBoots(), cellPos, level);
				return true;
			case "FlippersCell":
				this.pickupItem(new Flippers(), cellPos, level);
				return true;
			case "Goal":
				return true;
			default:
				return false;
		}
	}
	
	public boolean openDoor(Door door, Level level) {
		String doorType = door.cellName();

		switch (doorType) {
		case "TokenDoor":
			return this.openTokenDoor(door, level);
		case "RedDoor":
			return this.hasKey(door,'r', level);
		case "BlueDoor":
			return this.hasKey(door, 'b', level);
		case "GreenDoor":
			return this.hasKey(door,'g', level);
		default:
		}
		
		return false;
	}
	
	public boolean openTokenDoor(Door door, Level level) {
		int requiredTokens = ((TokenDoor) door).getRequiredTokens();
		
		if (this.tokenCount >= requiredTokens) {
			this.tokenCount = this.tokenCount - requiredTokens;
			door.turnToGround(level);
			return true;
		}
		return false;
	}

	public boolean hasKey(Door door, char keyType, Level level) {
		int keyID;

		switch (keyType) {
		case 'r':
			keyID = new RedKey().getItemID();
			break;
		case 'g':
			keyID = new GreenKey().getItemID();
			break;
		case 'b':
			keyID = new BlueKey().getItemID();
			break;
		default:
			keyID = -1;
		}

		for (int i = 0; i < this.inventory.size(); i++) {
			if (this.inventory.get(i).getItemID() == keyID) {
				this.inventory.remove(i);
				door.turnToGround(level);
				return true;
			}
		}
		
		return false;
	}
	

	/**
	 * This method handles the death of the player.
	 */

	private void playerDeath() {
		this.alive = false;
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Death");
		alert.setHeaderText("You died.");
		alert.setContentText(null);
		alert.showAndWait();
	}

	/**
	 * Puts an item into the player's inventory.
	 * @param level the current level being completed
	 * @param item the thing being picked up
	 * @param itemPos the position of the item being picked up           
	 */

	public void pickupItem(Item item, Vector2D itemPos, Level level) {
		Cell cell = level.getCellAt(itemPos);

		inventory.add(item);
		((Replaceable) cell).turnToGround(level);
	}

	/**
	 * Ensures the player has the correct collectible.
	 * 
	 * @param item
	 *            the collectible being picked up
	 * @return true If the item is in the inventory or false if the item isn't
	 *         present
	 */
	public boolean hasItem(Item item) {
		return this.inventory.contains(item);
	}

	/**
	 * This removes the item from the inventory.
	 * 
	 * @param item
	 *            the current item being removed
	 */
	public void removeItem(Item item) {
		this.inventory.remove(item);
	}

	/**
	 * Gets the number of tokens.
	 * 
	 * @return tokenCount
	 */
	public int getTokens() {
		return tokenCount;
	}
	
	public LinkedList<Item> getInventory() {
		return this.inventory;
	}

}