package a2;

import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.plaf.basic.BasicScrollPaneUI.VSBChangeListener;

import cell.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * This hold the player class.
 * 
 * @author James Colebourn, Darius Thomas
 * @version 1.0
 */

public class Player extends Entity {

	/**
	 * Sets of directions the player can move.
	*/
	public enum Direction {
		UP, DOWN, LEFT, RIGHT,
	}
	
	
	/**
	 * Sets of directions the player can shoot the dagger.
	 * @author tomwo
	 *
	 */
	public enum ShootDirection {
		UP, DOWN, LEFT, RIGHT,
	}
	private static final int IV = 4;
	private static final int V = 5;
	private static final String SPRITE = 
			"a2/resources/stock photos/Player1.png";
	private boolean alive;
	private LinkedList<Item> inventory;
	private int tokenCount;

	private Direction curDirection;
	
	

	/**
	 * Constructs the player object.
	 * 
	 * @param entityID = 0, defines the entity as a player type
	 * @param currentVector the current location of the player    
	 * @param level stores the current level 
	 */
	public Player(Vector2D currentVector, int entityID, Level level) {
		
		super(currentVector);
		System.out.println("CURRENTVECTOR2" + currentVector + 
				this.getCurrentVector());
		this.alive = true;
		this.inventory = new LinkedList<Item>();
		this.tokenCount = 0;
		
	}
	/**
	 * Performs the Teleport Function.
	 * @param tele the teleporter in question
	 */
	public void useTeleporter(Teleporter tele) {
		
	}
	/**
	 * Returns the direction the player is/has moving/moved.
	 * @return gives the current Direction of travel
	 */
	public Direction getCurrentDirection() {
		return curDirection;
	}
	
	/**
	 * The method for enacting a moving the player.
	 * @param dir gives the direction the player has chosen to move
	 * @return nextVector gives the location the player is moving to
	 */
	public Vector2D move(Direction dir) {
		Vector2D nextVector = new Vector2D(getCurrentVector());
		
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
	
	public Vector2D shoot(ShootDirection dir) {
		Vector2D nextVector = new Vector2D(getCurrentVector());
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
	/**
	 * Sets number of tokens.
	 * @param tokenCount 
	 */
	public void setTokenCount(int tokenCount) {
		this.tokenCount = tokenCount;
	}
	/**
	 * Adds 1 to the players Token Count.
	 */
	public void giveToken() {
		this.tokenCount += 1;
	}
	/**
	 * Returns the number of tokens collected by the player.
	 * @return gives the players number of tokens
	 */
	public int getTokenCount() {
		return tokenCount;
	}
	/**
	 * Compares the required tokens vs the number of tokens the player has.
	 * @param tokenCount number of tokens
	 * @return gives a boolean for if the player has enough tokens
	 */
	public boolean hasTokens(int tokenCount) {
		return this.tokenCount >= tokenCount;
	}
	/**
	 * Handles the player movement input.
	 * @param input the number of tokens held
	*/
	public void handleInput(Direction input) {
		curDirection = input;
		System.out.println("CURRENT VECTOR" + getCurrentVector());
		Cell cell = Level.getCurrentLevel().getCellAt(move(input));
		doMoveAction(move(input));
		if (cell.cellName() != "Goal") {
			if (isValidMove(move(input))) {
				Iterator<Entity> iter = Level.getCurrentLevel().
						getEntityList().iterator();
				while (iter.hasNext()) {
					Entity ent = iter.next();
					if (ent == this) { 
						setCurrentVector(move(input));
					} else {
						checkPlayerIntersectEnemy(ent);
						ent.move();
						if (ent.getRemove()) {
							iter.remove();
						}
						checkPlayerIntersectEnemy(ent);
					}
				}
			}
		}
	}
	
	
	public void handleShoot(ShootDirection input) {
		//curDirection = input;
		System.out.println("CURRENT VECTOR" + getCurrentVector());
		Cell cell = Level.getCurrentLevel().getCellAt(shoot(input));
		for (Entity ent : Level.getCurrentLevel().getEntityList()) {
			if (ent != this) { 
				checkPlayerIntersectEnemy(ent);
				ent.move();
				checkPlayerIntersectEnemy(ent);
			}
		}
		final int DAGGER_ID = 10; 
		for (Item item : this.inventory) {
			if (item.getItemID() == DAGGER_ID) {
				this.inventory.remove(item);
				Dagger newDagger = new Dagger(shoot(input));
				newDagger.setDirection(newDagger.getDirection(input));
				Level.getCurrentLevel().getEntityList().add(
						newDagger);
			}
		}
		 
	}

	/**
	 * Kills the player if it and an enemy share the same cell.
	 * @param e checks if the Player and an enemy share the same cell
	 */
	public void checkPlayerIntersectEnemy(Entity e) {
		if (getCurrentVector().equals(e.getVector())) {
			playerDeath();
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
	public boolean isValidMove(Vector2D cellPos) {
		Cell cell = Level.getCurrentLevel().getCellAt(cellPos);
		if (!crepCheck(cell)) {			
			playerDeath();
		}
		return cell.isWalkable();
	}
	/**
	 * Moves the Player.
	 * @param cellPos is the position of a cell
	 */
	public void doMoveAction(Vector2D cellPos) {
		Cell cell = Level.getCurrentLevel().getCellAt(cellPos);
		cell.doAction(this);
		if (cell instanceof Teleporter) {
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
				if (item.getItemID() == IV) { // if player has at least one pair
												// of fireboots
					return true;
				}
			}
			return false;
		} else if (cell.cellName().equals("Water")) {
			for (Item item : this.inventory) {
				if (item.getItemID() == V) { // if player has at least one pair
												// of flippers
					return true;
				}
			}
			return false;
		}

		return true;
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
			door.turnToGround();
			return true;
		}
		return false;
	}
	/**
	 * Checking for keys before opening doors.
	 * @param keyType the required item to open the door
	 * @return returns boolean for if door can be opened
	 */
	public boolean hasKey(char keyType) {
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

		for (Item item : this.inventory) {
			if (item.getItemID() == keyID) {
				this.inventory.remove(item);
				//door.turnToGround();
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
		return getCurrentVector();
		
	}
	
	
	
}