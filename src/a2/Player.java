package a2;

import java.util.Iterator;
import java.util.LinkedList;
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
		this.alive = true;
		this.inventory = new LinkedList<>();
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
	
	/**
	 * The method for enacting the throwing of a dagger.
	 * @param dir the direction the dagger is being thrown in
	 * @return the next position of the dagger.
	 */
	
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
				nextVector.add(new Vector2D(1, 0));
				break;
			case RIGHT:
				nextVector.add(new Vector2D(-1, 0));
				break;
		}
		return nextVector;
	}
	/**
	 * Sets number of tokens.
	 * @param tokenCount The number of token the new count is being set to.
	 */
	public void setTokenCount(int tokenCount) {
		this.tokenCount = tokenCount;
	}
	/**
	 * Adds 1 to the players Token Count.
	 */
	public void giveToken() {
		this.tokenCount += 1;
		String audioFilePath =
				"src\\a2\\resources\\Sound bytes/Token Collect.wav";
		MusicPlayer tokenClip = new MusicPlayer(audioFilePath);
		tokenClip.play();
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
		Cell cell = Level.getCurrentLevel().getCellAt(move(input));
		doMoveAction(move(input));
		if (!(cell instanceof Goal)) {
			if (isValidMove(move(input))) {
				for (Entity ent : Level.getCurrentLevel().getEntityList()) {
					if (ent == this) { 
						setCurrentVector(move(input));
					} else {
						checkPlayerIntersectEnemy(ent);
						ent.hasHitDagger();
						ent.move();	
						checkPlayerIntersectEnemy(ent);
						ent.hasHitDagger();
					}
				}
				Iterator<Entity> iter = Level.getCurrentLevel().
						getEntityList().iterator();
				while (iter.hasNext()) {
					Entity ent = iter.next();
					if (ent.getRemove()) {
						iter.remove();
					}
				}
			}
		}
	}
	
	
	/**
	 * Handles the throwing of the dagger.
	 * @param input the direction the dagger is being thrown in.
	 */
	
	public void handleShoot(ShootDirection input) {
		//curDirection = input;
		Cell cell = Level.getCurrentLevel().getCellAt(shoot(input));
		for (Entity ent : Level.getCurrentLevel().getEntityList()) {
			if (ent != this) { 
				checkPlayerIntersectEnemy(ent);
				ent.hasHitDagger();
				ent.move();
				checkPlayerIntersectEnemy(ent);
				ent.hasHitDagger();
			}
		}
		final int DAGGER_ID = 10; 
		Iterator<Entity> iter = Level.getCurrentLevel().
				getEntityList().iterator();
		while (iter.hasNext()) {
			Entity ent = iter.next();
			if (ent.getRemove()) {
				iter.remove();
			}
		}
		if (cell instanceof Ground) {
			for (Item item : this.inventory) {
				if (item.getItemID() == DAGGER_ID) {
					this.inventory.remove(item);
					Dagger newDagger = new Dagger(shoot(input), input);
					newDagger.setDirection(newDagger.getDirection(input));
					Level.getCurrentLevel().getEntityList().add(
							newDagger);
                    String audioFilePath =
                            "src/a2/resources/Sound bytes/Pick up item.wav";
                    MusicPlayer daggerClip = new MusicPlayer(audioFilePath);
                    daggerClip.play();
				}
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
	 * Determines whether the requested move is valid.
	 * @param cellPos the position of the cell on the map
	 * @return a boolean to show whether the cell can be moved onto
	 */
	public boolean isValidMove(Vector2D cellPos) {
		Cell cell = Level.getCurrentLevel().getCellAt(cellPos);
		return cell.isWalkable();
	}
	/**
	 * Moves the Player.
	 * @param cellPos is the position of a cell
	 */
	public void doMoveAction(Vector2D cellPos) {
		Cell cell = Level.getCurrentLevel().getCellAt(cellPos);
		cell.doAction(this);
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
			String audioFilePath =
					"src\\a2\\resources\\Sound bytes/Door unlock.wav";
			MusicPlayer doorClip = new MusicPlayer(audioFilePath);
			doorClip.play();
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

	public void playerDeath() {
		this.alive = false;
		String audioFilePath =
				"src\\a2\\resources\\Sound bytes/Player Death.wav";
		MusicPlayer deathClip = new MusicPlayer(audioFilePath);
		deathClip.play();
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
		String audioFilePath =
				"src\\a2\\resources\\Sound bytes/Pick up item.wav";
		MusicPlayer itemClip = new MusicPlayer(audioFilePath);
		itemClip.play();
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
			if (itemCheck.isInstance(item)) {
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
		return CharSelectController.getCharSprite();
	}
	
	/**
	 * Returns the players position as a Vector2D.
	 * @return players position
	 */
	public Vector2D getPlayerVector() {
		return getCurrentVector();
		
	}
}