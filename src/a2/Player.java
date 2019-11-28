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
	public Player(Vector2D currentVector, int entityID, Level level) {
		super(currentVector, entityID, level);
		this.alive = true;
		this.inventory = new LinkedList<Item>();
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
		int cX = this.currentVector.getX();
		int cY = this.currentVector.getY();
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
		
		if(isValidMove(nextVector)) {
			return nextVector;
		}
		
		return this.currentVector;
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
	public Boolean isValidMove(Vector2D cellPos) {

		Cell cell = this.level.getCellAt(cellPos);
		String cellType = cell.cellName();
		
		switch (cellType) {
			case "Ground":
				return true;
			case "Wall":
				return false;
			case "TokenDoor":
				return this.openDoor((Door) cell);
			case "TokenCell":
				this.tokenCount++;
				((Replaceable) cell).turnToGround(this.level);
				return true;
			case "RedDoor":
				return this.openDoor((Door) cell);
			case "RedDoorKey":
				this.pickupItem(new RedKey(), cellPos);
				return true;
			case "GreenDoor":
				return this.openDoor((Door) cell);
			case "GreenDoorKey":
				this.pickupItem(new GreenKey(), cellPos);
				return true;
			case "BlueDoor":
				return this.openDoor((Door) cell);
			case "BlueDoorKey":
				this.pickupItem(new BlueKey(), cellPos);
				return true;
			case "Fire":
				this.crepCheck(cell);
			case "Water":
				this.crepCheck(cell);
			case "FireBootsCell":
				this.pickupItem(new FireBoots(), cellPos);
				return true;
			case "FlippersCell":
				this.pickupItem(new Flippers(), cellPos);
				return true;
			case "Goal":
				return true;
			default:
				return false;
		}
	}
	
	public boolean crepCheck(Cell cell) {
		if (cell.cellName().equals("Fire")) {
			for (Item item : this.inventory) {
				if (item.getItemID() == 4) { // if player has at least one pair
												// of fireboots
					return true;
				}
			}
		} else if (cell.cellName().equals("Water")) {
			for (Item item : this.inventory) {
				if (item.getItemID() == 5) { // if player has at least one pair
												// of flippers
					return true;
				}
			}
		}

		return false;
	}
	
	public boolean openDoor(Door door) {
		String doorType = door.cellName();

		switch (doorType) {
		case "TokenDoor":
			return this.openTokenDoor(door);
		case "RedDoor":
			return this.hasKey(door,'r');
		case "BlueDoor":
			return this.hasKey(door, 'b');
		case "GreenDoor":
			return this.hasKey(door,'g');
		default:
		}
		
		return false;
	}
	
	public boolean openTokenDoor(Door door) {
		int requiredTokens = ((TokenDoor) door).getRequiredTokens();
		
		if (this.tokenCount >= requiredTokens) {
			this.tokenCount = this.tokenCount - requiredTokens;
			door.turnToGround(this.level);
			return true;
		}
		return false;
	}

	public boolean hasKey(Door door, char keyType) {
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
				door.turnToGround(this.level);
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

	public void pickupItem(Item item, Vector2D itemPos) {
		Cell cell = this.level.getCellAt(itemPos);

		inventory.add(item);
		((Replaceable) cell).turnToGround(this.level);
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