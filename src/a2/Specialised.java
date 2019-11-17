package a2;

/**
 * A cell that has an action to be performed when the player moves onto it.
 * @author george
 *
 */
public abstract class Specialised extends Cell {

	
	/**
	 * Creates a new specialised cell.
	 * @param pos Position of the cell
	 */
	public Specialised(Vector2D pos) {
		super(pos);
		
	}
	
	/**
	 * The action to perform when the player moves onto the tile.
	 */
	public void doAction() {
		
	}
}
