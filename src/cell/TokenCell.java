package cell;

import a2.Vector2D;

/**
 * The token cell that the will add to the users
 * token count when walked on.
 * @author tomwo
 *
 */

public class TokenCell extends Cell {
	protected static final String SPRITE = "a2/resources/stock photos/Token.png";

	
	/**
	 * Send the position to the cell superclass.
	 * 
	 * @param pos Holds the position of the Cell 
	 */
	public TokenCell(Vector2D pos) {
		super(pos);
	}
	
	public String cellName() {
		return "TokenCell";
	}
	
	

}
