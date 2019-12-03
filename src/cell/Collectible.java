package cell;

import a2.Item;
import a2.Player;
import a2.Vector2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * A ground cell that has an item that can be picked up on it.
 * @author george
 *
 */
public abstract class Collectible extends Replaceable {
	
	/**
	 * Creates a new collectible cell.
	 * @param pos The position of the collectible
	 */
	public Collectible(Vector2D pos) {
		super(pos);
	}
	@Override
	public void doAction(Player p) {
		p.pickupItem(createItem());
		turnToGround();
	}
	
	public abstract Item createItem();
	
	public void draw(GraphicsContext gc,int drawPosX,int drawPosY) {
		Image ground = new Image(Ground.SPRITE);
		gc.drawImage(ground, drawPosX,drawPosY);
		Image collectible = new Image(getSprite());
		gc.drawImage(collectible, drawPosX,drawPosY);
	}
	
}
