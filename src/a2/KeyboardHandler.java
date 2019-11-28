package a2;

import a2.Player.Direction;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyboardHandler implements EventHandler {

	private Level level;
	
	public KeyboardHandler(Level lvl) {
		this.level = lvl;
		// TODO Auto-generated constructor stub
	}
	
	public void handle(Event e) {
		KeyEvent ke = (KeyEvent) e;
		
		switch (ke.getCode()) {
			case W:
				level.getPlayer().move(Direction.UP);
				break;
			case A:
				level.getPlayer().move(Direction.LEFT);
				break;
			case S:
				level.getPlayer().move(Direction.DOWN);
				break;
			case D:
				level.getPlayer().move(Direction.RIGHT);
				break;
			default:
				break;
		}
	}

}
