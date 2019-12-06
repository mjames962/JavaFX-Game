package cell;

import a2.Player;
import a2.Vector2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * The token cell that the will add to the users
 * token count when walked on.
 * @author tomwo
 *
 */

public class TokenCell extends Replaceable {
	protected static final String SPRITE = "a2/resources/stock photos/Token.png";

	
	/**
	 * Send the position to the cell superclass.
	 * 
	 * @param pos Holds the position of the Cell 
	 */
	public TokenCell(Vector2D pos) {
		super(pos);
	}
	
	public String getSprite() {
		return SPRITE;
	}
	
	@Override
	public void doAction(Player p) {
		p.giveToken();
		turnToGround();
	}
	
	public void draw(GraphicsContext gc,int drawPosX,int drawPosY) {
		Image ground = new Image(Ground.SPRITE);
		gc.drawImage(ground, drawPosX,drawPosY);
		Image collectible = new Image(getSprite());
		gc.drawImage(collectible, drawPosX,drawPosY);
	}
	
	public char getChar() {
		return 'd';
	}

}
