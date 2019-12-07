package cell;

import a2.Player;
import a2.Vector2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * The token cell that the will add to the users
 * token count when walked on.
 * @author Tom Wood
 * @version 1.5
 */

public class TokenCell extends Replaceable {
	protected static final String SPRITE = 
			"a2/resources/stock photos/Token.png";

	
	/**
	 * Send the position to the cell superclass.
	 * 
	 * @param pos Holds the position of the Cell 
	 */
	public TokenCell(Vector2D pos) {
		super(pos);
	}
	/**
	 * Getter for the TokenCell Sprite.
	 * @return gives the sprite
	 */
	public String getSprite() {
		return SPRITE;
	}

    /**
     * Adds a token cell to the players token count and removes the cell from
     * the map.
     * @param p the player in the level.
     */
	
	@Override
	public void doAction(Player p) {
		p.giveToken();
		turnToGround();
	}
	/**
	 * Draws the token Cell in the GC at the given position.
	 * @param gc the current graphics context
	 * @param drawPosX holds the X coordinate
	 * @param drawPosY holds the Y coordinate
	 */
	public void draw(GraphicsContext gc, int drawPosX, int drawPosY) {
		Image ground = new Image(Ground.SPRITE);
		gc.drawImage(ground, drawPosX, drawPosY);
		Image collectible = new Image(getSprite());
		gc.drawImage(collectible, drawPosX, drawPosY);
	}
	/**
	 * Getter for unique identifying character.
	 * @return gives the unique identifier
	 */
	public char getChar() {
		return 'd';
	}

}
