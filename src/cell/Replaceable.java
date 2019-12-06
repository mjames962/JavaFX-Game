package cell;

import a2.Level;
import a2.MusicPlayer;
import a2.Vector2D;

//TODO is this needed? just one method

/**
 * A cell type that can change into a ground cell.
 * @author george
 *
 */
public abstract class Replaceable extends Cell {

	
	/**
	 * Creates a new replaceable cell.
	 * @param pos The position of the cell
	 */
	public Replaceable(Vector2D pos) {
		super(pos);
	}
	
	/**
	 * Turns a cell into a ground cell.
	 * @param level 
	 * @param cell 
	 */
	public void turnToGround() {
		int xCoord = this.getX();
		int yCoord = this.getY();
		Level.getCurrentLevel().setLevel(new Ground(this.getPos()), xCoord, yCoord);
	}
	

}
