package cell;

import a2.Player;
import a2.Player.Direction;
import a2.Vector2D;


/**
 * A cell that teleports the player from one teleporter to another.
 * @author george and Tom Wood
 *
 */
public class Teleporter extends Cell {
	protected static final String SPRITE = "a2/resources/stock photos/Teleporter_Cell.png";
	
	private Vector2D linkedTP;

	
	
	/**
	 * Create a new teleporter linked to another.
	 * @param pos Position of the teleporter
	 * @param linkedTP The linked teleporter
	 */
	public Teleporter(Vector2D pos, Vector2D linkedTP) {
		super(pos);
		this.linkedTP = linkedTP;
	}
	
	/**
	 * Create a new teleporter without a link, to be created later.
	 * @param pos Position of the teleporter
	 */
	public Teleporter(Vector2D pos) {
		super(pos);
	}
	
	/**
	 * Teleport the player.
	 * 
	 * @param direction represents the direction the player is moving in.
	 * @param player represents the player that will be moved to the new space
	 */
	@Override
	public void doAction(Player player) {
		System.out.println(linkedTP);
		Vector2D pos = player.getVector();
		Direction direction = player.getCurrentDirection();
	    pos.setX(linkedTP.getX());
	    pos.setY(linkedTP.getY());
	}
	
	/**
	 * Sets the teleporter That it will be linked with.
	 * 
	 * @param linkedTP the position of the linked teleporter 
	 * 
	 */
	
	public void setLinkedTP(Vector2D linkedTP) {
		this.linkedTP = linkedTP;
	}
	
	public Vector2D getLinkedTP() {
		return this.linkedTP;
	}
	
	/**
	 * Sets up the teleporter linkup on both teleporters at once
	 * 
	 * @param link
	 */
	public void setLinks(Teleporter link) {
		this.setLinkedTP(link.getPos());
		link.setLinkedTP(this.getPos());
	}
	
	public String cellName() {
		return "Teleporter";
	}
	
	public String getSprite() {
		return SPRITE;
	}
	
	public char getChar() {
		return 'T';
	}
	
	
	
	
	
}
