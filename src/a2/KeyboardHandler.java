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
	
	public void nextTick(Direction playerMove) {
		
		level.getPlayer().move(playerMove);
	}
	
	public void handle(Event e) {
		KeyEvent ke = (KeyEvent) e;
		
		switch (ke.getCode()) {
			case W:
				nextTick(Direction.UP);
				break;
			case A:
				nextTick(Direction.LEFT);
				break;
			case S:
				nextTick(Direction.DOWN);
				break;
			case D:
				nextTick(Direction.RIGHT);
				break;
			default:
				break;
		}
	}

}
