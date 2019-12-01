package a2;

import a2.Player.Direction;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * ¯\_(o_o)_/¯
 * @author George, Tom
 *
 */
public class KeyboardHandler implements EventHandler<KeyEvent> {

	private Level level;
	private GameWindowController lw;
	
	/**
	 * ¯\_(o_o)_/¯
	 * @param GameWindowController
	 * @param lvl
	 */
	public KeyboardHandler(GameWindowController GameWindowController,Level lvl) {
		this.level = lvl;
		this.lw = GameWindowController;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Updates game state.
	 * @param playerMove ¯\_(o_o)_/¯
	 */
	public void nextTick(Direction playerMove) {
		System.out.println("Inital: " + level.getPlayer().getVector());
		level.getPlayer().handleInput(playerMove);
		lw.drawAll();
		System.out.println("Final: " + level.getPlayer().getVector());
		System.out.println("NEXT TICK");
	}
	
	/**
	 * Checks user input
	 */
	public void handle(KeyEvent e) {
		System.out.println("keeeyd pressed");
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
