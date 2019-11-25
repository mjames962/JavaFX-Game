package cell;

import a2.Vector2D;

/**
 * the door that will open when the user has
 * the required number of tokens.
 * @author tomwo
 *
 */

public class TokenDoor extends Door {
	
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
	 * of tokens to open the door.
	 * @param tokenCount #of tokens held by the player
	 * @return if the players token total is higher than the required
	 * tokens.
	 */
	
	public boolean meetsRequirement(int tokenCount) {
		return requiredTokens <= tokenCount;
	}
	
	/**
	 * Sets the number of tokens required by the door.
	 * @param requiredTokens # of tokens needed to open the door
	 */
	public void setReqTokens(int requiredTokens) {
		this.requiredTokens = requiredTokens;
	}
	/**
	 * allows the name of the cell to compared as a string.
	 * @return the name as a string
	 */
	public String cellName() {
		return "TokenDoor";
	}
	
	
	
	
}
