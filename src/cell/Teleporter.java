package cell;

import a2.MusicPlayer;
import a2.Player;
import a2.Vector2D;


/**
 * A cell that teleports the player from one teleporter to another.
 * @author George Williams Walton, Tom Wood
 *
 */
public class Teleporter extends Cell {
	protected static final String SPRITE = 
			"a2/resources/stock photos/Teleporter_Cell.png";
	
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
	 * @param player represents the player that will be moved to the new space
	 */
	@Override
	public void doAction(Player player) {
		Vector2D pos = player.getVector();
	    pos.setX(linkedTP.getX());
	    pos.setY(linkedTP.getY());
		String audioFilePath =
				"src\\a2\\resources\\Sound bytes/Teleport.wav";
		MusicPlayer teleportClip = new MusicPlayer(audioFilePath);
		teleportClip.play();
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
	/**
	 * Identifies the Teleporter linking to current Teleporter.
	 * @return gives Vector2D of the linked teleporter.
	 */
	public Vector2D getLinkedTP() {
		return this.linkedTP;
	}
	
	/**
	 * Sets up the teleporter linkup on both teleporters at once.
	 * @param link the Teleporter this cell is linked to
	 */
	public void setLinks(Teleporter link) {
		this.setLinkedTP(link.getPos());
		link.setLinkedTP(this.getPos());
	}
	/**
	 * Getter for the TeleporterCell Sprite.
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
		return 'T';
	}
	
	
	
	
	
}
