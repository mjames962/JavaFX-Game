package a2;

import a2.Player.Direction;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyboardHandler implements EventHandler {

	private Level level;
	private GameWindowController lw;
	
	public KeyboardHandler(GameWindowController GameWindowController,Level lvl) {
		this.level = lvl;
		this.lw = GameWindowController;
		// TODO Auto-generated constructor stub
	}
	
	public void nextTick(Direction playerMove) {
		System.out.println("Inital: " + level.getPlayer().getVector());
		level.getPlayer().handleInput(playerMove);
		lw.drawAll();
		System.out.println("Final: " + level.getPlayer().getVector());
		System.out.println("NEXT TICK");
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
