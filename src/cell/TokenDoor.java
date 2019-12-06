package cell;

import a2.Player;
import a2.Vector2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * the door that will open when the user has
 * the required number of tokens.
 * @author Tom Wood
 * @version 1.4
 */

public class TokenDoor extends Door {
	
	protected static final String SPRITE = 
			"a2/resources/stock photos/TokenDoor.png";
	
	private int requiredTokens;
	
	/**
	 * Send the position to the cell superclass.
	 * 
	 * @param pos Holds the position of the Cell 
	 */

	public TokenDoor(Vector2D pos) {
		super(pos);
	}
	
	/**
	 * Checks to see if the player has the required number
	 *									 of tokens to open the door.
	 * @param p reference to current player
	 * @return if the players token total is higher than the required
	 * 														tokens.
	 */
	
	public boolean meetsRequirement(Player p) {
		return p.hasTokens(requiredTokens);
	}
	/**
	 * Returns the #of tokens needed to open the door.
	 * @return the number of required tokens for the door
	 */
	public int getRequiredTokens() {
		return this.requiredTokens;
	}
	
	/**
	 * Calls method to notify player they don't have enough tokens.
	 * @param ply reference to current player
	 */
	public void doAction(Player ply) {
		
		super.doAction(ply);
		if (!meetsRequirement(ply)) {
			displayTokensRequired();
		}
	}
	/**
	 * Creates a pop-up to tell the user how many tokens are needed.
	 */
	public void displayTokensRequired() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Token Door");
		alert.setHeaderText("Tokens required: " + getRequiredTokens());
		alert.setContentText(null);
		alert.showAndWait();
	}
	
	/**
	 * Sets the number of tokens required by the door.
	 * @param requiredTokens # of tokens needed to open the door
	 */
	public void setReqTokens(int requiredTokens) {
		this.requiredTokens = requiredTokens;
	}
	/**
	 * Getter for the TokenDoorCell Sprite.
	 * @return gives the sprite
	 */
	public String getSprite() {
		return SPRITE;
	}
	/**
	 * Getter for unique identifying character.
	 * @return gives the unique identifier
	 */
	public char getChar() {
		return 'D';
	}
	
	
	
}
