package a2;
/**
 * A cell that teleports the player from one teleporter to another.
 * @author george and Tom Wood
 *
 */
public class Teleporter extends Specialised {
	
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
	
	public void doActionTP(String direction, Player player) {
		Vector2D pos = player.getVector();
	    pos.setX(linkedTP.getX());
	    pos.setY(linkedTP.getY());
	    switch (direction) {	    
	    	case "LEFT":
	    		pos.setX(pos.getX() - 1);
	    		break;
	    	case "RIGHT":
	    		pos.setX(pos.getX() + 1);
	    		break;
	    	case "UP":
	    		pos.setY(pos.getY() + 1);
	    		break;
	    	case "DOWN":
	    		pos.setX(pos.getY() - 1);
	    		break;
	    	
	    }
	    	

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
	
	
	
	
	
}
