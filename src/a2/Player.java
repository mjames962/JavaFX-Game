package a2;

import java.util.ArrayList;
import java.util.LinkedList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * This hold the player class.
 * @author Darius Thomas and James Colebourn
 * @version 1.0
 */

public class Player extends Entity {
	
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
	private LinkedList<Item> inventory;
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
			LinkedList<Item> inventory) {
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
	/*	if (nextVector == Goal) {
			//end level
		}*/
	}
	
	/**
	 * Overrides the isValidMove method in Entity for player entities.
	 * @param Level the given level currently loaded
	 * @param nextVector the requested cell to move to
	 * @return returns a boolean for if the requested move is valid
	 */	
	public boolean isValidMove(Vector2D nextVector, Level Level) {
		int cX = currentVector.getX();
		int cY = currentVector.getY();
		String cellType = (Level.getCellAt(cX, cY).cellName());
		Cell cell = Level.getCellAt(cX, cY);
	//	Class<?> typ = cell.getClass();
	//	Class<Ground> = Ground.class;   
		switch (cellType) {
		
			case "Ground": //Ground
				return false;
				break;
			case "Wall": //Wall
				return false;
				break;
			case "TokenDoor": //Token Door
				TokenDoor td = (TokenDoor) cell;
				if (td.meetsRequirement(tokenCount)) {
					//call turnToGround()
					return true;
				} 
				else {
					return false;						
				}
			case "Token":
				tokenCount = tokenCount++;
				//call turnToGround()
				return true;
				break;
			case "BlueDoor": //Door of Blue
				//call turnToGround()
				return true;
				return false;
				break;
			case "BlueKeyCell": //Door of Blue
				//call turnToGround()
				return true;
				return false;
				break;
			case "GreenDoor": //Door of Blue
				//call turnToGround()
				return true;
				return false;
				break;
			case "GreenKeyCell": //Door of Blue
				//call turnToGround()
				return true;
				return false;
				break;
			case "RedDoor": //Door of Blue
				//call turnToGround()
				return true;
				return false;
				break;
			case "RedKeyCell": //Door of Blue
				//call turnToGround()
				return true;
				return false;
				break;
			case "Fire":
				//check if FB owned
				//react
				return true;
				break;
			
			case "FireBoots":
				//add to inv
				//call replace
				return true;
				break;
			
			case "Water":
				//check if Flippers owned
				//react
				return true;
				break;
			
			case "Flippers":
				//add to inv
				//call replace
				return true;
				break;
			case "Teleporter":
				return true;
				break;
			case "Goal":
				return true;
				break;
			default:
				return false;
				break;
		}
		
		
	
	/**
	 * This method handles the death of the player.
	 */
	
	private void playerDeath() {
		alive = false;
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Death");
		alert.setHeaderText("You died.");
		alert.setContentText(null);
		alert.showAndWait();
	}
	
	/**
	 * Puts an item into the player's inventory.
	 * @param collectible 
	 */
	
	public void pickupItem(Item item) {
		inventory.add(item);
	}
	
	/**
	 * Ensures the player has the correct collectible.
	 * @param item the collectible being picked up
	 * @return true If the item is in the inventory
	 * or false if the item isn't present
	 */
	public boolean hasItem(Item item) {
		boolean status = inventory.contains(item); 
        if(status) 
            return true; 
        else
            return false;
	}
	
	/**
	 * This removes the item from the inventory
	 * @param item the current item being removed
	 */
	public void removeItem(Item item) {
		inventory.remove(item);
	}
	
	/**
	 * Gets the number of tokens
	 * @return tokenCount
	 */
	public int getTokens() {
		return tokenCount;
	}
	
}