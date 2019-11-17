package a2;
/**
 * A cell that teleports the player from one teleporter to another.
 * @author george and Tom
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
	 * @param player reprents the player that will be moved to the new space
	 */
	
	public void doActionTP(String direction, Player player) {
	    player.setX(linkedTP.getX());
	    player.setY(linkedTP.getY());
	    switch (direction) {
	    
	    	case "LEFT":
	    		player.setX(player.getX() - 1);
	    		break;
	    	case "RIGHT":
	    		player.setX(player.getX() + 1);
	    		break;
	    	case "UP":
	    		player.setY(player.getY() + 1);
	    		break;
	    	case "DOWN":
	    		player.setX(player.getY() - 1);
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
	
	
	
	
	
}
