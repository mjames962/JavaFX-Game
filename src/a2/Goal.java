package a2;

public class Goal extends Specialised {

	public Goal(Vector2D pos) {
		super(pos);
	}
	
	public void doAction() {
		Level.readFile("level2.txt");
	}
}
