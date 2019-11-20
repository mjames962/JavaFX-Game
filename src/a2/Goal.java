package a2;

public class Goal extends Specialised {

	public Goal(Vector2D pos) {
		super(pos);
	}
	
	public void doAction() {
		Level.readFile("level2.txt");
		long finishTime = Timer.checkTimeElapsed();
		//Add time to user profile
		//Level call for method to create a new level.
		Timer.stop();
	}
}
