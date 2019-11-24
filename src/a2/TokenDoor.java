package a2;

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
	 * @param ply the player on the map
	 * @return if the players token total is higher than the required
	 * tokens.
	 */
	
	public boolean meetsRequirement(Player ply) {
		return requiredTokens <= ply.getTokens();
	}
	
	/**
	 * Sets the number of tokens required by the door
	 * @param requiredTokens
	 */
	public void setReqTokens(int requiredTokens) {
		this.requiredTokens = requiredTokens;
	}
	
	
	
	
}
