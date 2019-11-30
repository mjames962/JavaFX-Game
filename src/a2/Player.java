package a2;

import java.util.LinkedList;

import javax.swing.plaf.basic.BasicScrollPaneUI.VSBChangeListener;

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
	private int tokenCount;
	private static final String SPRITE = "a2/resources/stock photos/Straight_Line_Enemy.png";
	private Direction curDirection;
	

	/**
	 * Constructs the player object.
	 * 
	 * @param entityID = 0, defines the entity as a player type
	 * @param alive Determines if the player can continue       
	 * @param inventory the collection of collectible(s) the player has
	 * @param currentVector the current location of the player    
	 * @param level stores the current level 
	 */
	public Player(Vector2D currentVector, int entityID, Level level) {
		
		super(currentVector, entityID, level);
		System.out.println("CURRENTVECTOR2" + currentVector + this.currentVector);
		this.alive = true;
		this.inventory = new LinkedList<Item>();
		this.tokenCount = 0;
		
	}

	public void useTeleporter(Teleporter tele) {
		
	}
	
	public Direction getCurrentDirection() {
		return curDirection;
	}
	
	/**
	 * the method for enacting a movement specified by the player.
	 * @param input is the intended movement direction of the player           
	 */
	
	public void move(Direction dir) {
		
		
	}
	
	public Vector2D getNextMoveVector(Direction dir) {
		Vector2D nextVector = new Vector2D(currentVector);
		switch (dir) {
			case UP:
				nextVector.add(new Vector2D(0, 1));
				break;
			case DOWN:
				nextVector.add(new Vector2D(0, -1));
				break;
			case LEFT:
				nextVector.add(new Vector2D(-1, 0));
				break;
			case RIGHT:
				nextVector.add(new Vector2D(1, 0));
				break;
		}
		return nextVector;
	}

	
	public void handleInput(Direction input) {
		curDirection = input;
		//System.out.println("CURRENT VECTOR" + currentVector);
		Vector2D nextVector = getNextMoveVector(input);
		doMoveAction(nextVector);
		if (isValidMove(nextVector)) {
			currentVector = nextVector;
		}
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
	 * @param cellPos the position of the cell on the map
	 * @return a boolean to show whether the cell can be moved onto
	 */
	public Boolean isValidMove(Vector2D cellPos) {
		Cell cell = this.level.getCellAt(cellPos);
		if (!crepCheck(cell)) { //TODO improve, instanceof
			
			playerDeath();
		}
		return cell.isWalkable();
	}
	
	public void doMoveAction(Vector2D cellPos) {
		Cell cell = this.level.getCellAt(cellPos);
		cell.doAction(this);
		if (cell instanceof Teleporter ) {
			System.out.println("stuff");
		}
	}
	
	

	/**
	 * Checks for Flippers/FireBoots on Water/Fire cells.
	 * @param cell the cell being walked on
	 * @return boolean for if the player has needed item
	 */
	public boolean crepCheck(Cell cell) {
		
		if (cell.cellName().equals("Fire")) {
			for (Item item : this.inventory) {
				System.out.println("item" + item.getItemID());
				if (item.getItemID() == 4) { // if player has at least one pair
												// of fireboots
					return true;
				}
			}
			return false;
		} else if (cell.cellName().equals("Water")) {
			for (Item item : this.inventory) {
				if (item.getItemID() == 5) { // if player has at least one pair
												// of flippers
					return true;
				}
			}
			return false;
		}

		return true;
	}
	/**
	 * The function to open a door.
	 * @param door the door being opened
	 * @return boolean for if possible
	 */
	public boolean openDoor(Door door) {
		String doorType = door.cellName();

		switch (doorType) {
			case "TokenDoor":
				return this.openTokenDoor(door);
			case "RedDoor":
				return this.hasKey(door, 'r');
			case "BlueDoor":
				return this.hasKey(door, 'b');
			case "GreenDoor":
				return this.hasKey(door, 'g');
			default:
		}
		
		return false;
	}
	/**
	 * Checks token door vs number of held tokens.
	 * @param door the door being checked for.
	 * @return boolean for if the door can be opened
	 */
	public boolean openTokenDoor(Door door) {
		int requiredTokens = ((TokenDoor) door).getRequiredTokens();
		
		if (this.tokenCount >= requiredTokens) {
			this.tokenCount = this.tokenCount - requiredTokens;
			door.turnToGround(this.level);
			return true;
		}
		return false;
	}
	/**
	 * Checking for keys before opening doors.
	 * @param door the door being checked
	 * @param keyType the required item to open the door
	 * @return returns boolean for if door can be opened
	 */
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
		Level.restartLevel();
	}

	/**
	 * Puts an item into the player's inventory.
	 * @param item the thing being picked up
	 */
	public void pickupItem(Item item) {
		inventory.add(item);	
	}

	/**
	 * Ensures the player has the correct collectible.
	 * @param itemCheck the collectible being picked up
	 * @return true If the item is in the inventory or false if the item isn't
	 *         present
	 */
	public boolean hasItem(Class<?> itemCheck) {
		for (Item item : this.inventory) {
			if (item.getClass() == itemCheck) {
				System.out.println("returned true");
				return true;
			}
		}
		return false;
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
	/**
	 * The inventory for the player.
	 * @return 's the inventory
	 */
	public LinkedList<Item> getInventory() {
		return this.inventory;
	}
	
	/**
	 * Returns the sprite for the player.
	 * @return gives the player sprite
	 */
	public String getSprite() {
		return SPRITE;
	}
	
	/**
	 * Returns the players position as a Vector2D.
	 * @return players position
	 */
	public Vector2D getPlayerVector() {
		return currentVector;
		
	}
}