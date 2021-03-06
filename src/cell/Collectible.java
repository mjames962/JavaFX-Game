package cell;

import a2.Item;
import a2.Player;
import a2.Vector2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * A ground cell that has an item that can be picked up on it.
 * @author George Williams Walton
 * @version 1.3
 */
public abstract class Collectible extends Replaceable {
	
	/**
	 * Creates a new collectible cell.
	 * @param pos The position of the collectible
	 */
	public Collectible(Vector2D pos) {
		super(pos);
	}

	/**
	 * The action for removing the collectible and calling turnToGround.
	 * @param p stores the player
	 */
    @Override
	public void doAction(Player p) {
		p.pickupItem(createItem());
		turnToGround();
	}
	/**
	 * Abstract for creating and returning an item.
	 * @return gives the item created
	 */
	public abstract Item createItem();
	
	/**
	 * Draws the collectible to the needed position.
	 * @param drawPosX Holds the X coordinate
	 * @param drawPosY Holds the Y coordinate
	 * @param gc the current graphics context the sprite will be drawn to.
	 */	
	public void draw(GraphicsContext gc, int drawPosX, int drawPosY) {
		Image ground = new Image(Ground.SPRITE);
		gc.drawImage(ground, drawPosX, drawPosY);
		Image collectible = new Image(getSprite());
		gc.drawImage(collectible, drawPosX, drawPosY);
	}
	
}
