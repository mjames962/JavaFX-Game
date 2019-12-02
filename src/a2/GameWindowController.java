package a2;


import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import a2.Player.Direction;
import cell.Cell;
import cell.Wall;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * Draws the canvas and allows the user to interact.
 * @author Jensen, George
 * 
 */
public class GameWindowController implements Initializable {
	public static final int LEVEL_WIDTH = 350;
	public static final int GAME_WIDTH = 350;
	public static final int GAME_HEIGHT = 350;
	public static final int LEVEL_LENGTH = 400;
	public static final int CELL_DIMENSIONS = 50;
	public static final int MIN_DRAW = 3;
	public static final int MAX_DRAW = 5;
	

	private GraphicsContext gc;
	private Level level; 

	
	@FXML 
	private AnchorPane gamePane;
	
	@FXML
	private BorderPane gameBorderPane;
	
	@FXML
	private Canvas gameCanvas;
	
	@FXML
	private Label lbl_MOTD;
	
	private static GameWindowController currentController;
	
	public static GameWindowController getCurrentController() {
		return currentController;
	}
	
	/**
	 * Creates and displays canvas in the window.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		show();
		refreshLevel();
		currentController = this;
	}
	
	public void nextTick(Direction playerMove) {
		level.getPlayer().handleInput(playerMove); //maybe can give responsibility to Level instead
		this.drawAll();
	}	
	/**
	 * Checks user input
	 */
	public void hookInput(Scene sc) {
		sc.addEventFilter(KeyEvent.KEY_PRESSED,new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) { 
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
        });
	}
	
	public void show() {
		Scene scene = new Scene(gamePane);
		Main.switchScene(scene);
		hookInput(scene);
	}
	
	public void refreshLevel() {
		this.level = Level.getCurrentLevel();
        this.gc = gameCanvas.getGraphicsContext2D();
        drawAll();
		
	}
	
	public void drawCells() {
		Image inventImage = new Image("a2/resources/stock photos/Inventory.png");
    	gc.drawImage(inventImage,0 ,GAME_HEIGHT);
		int playerX = level.getPlayer().getVector().getX();
        int playerY = level.getPlayer().getVector().getY();
		for (int x = playerX - MIN_DRAW; x < playerX + MAX_DRAW; x++) {
        	for (int y = playerY - MIN_DRAW; y < playerY + MAX_DRAW; y++) {
        		int drawX = x - playerX + MIN_DRAW;
        		int drawY = playerY - y + MIN_DRAW;
        		boolean xValid = !(x < 0 || x > level.levelXLength() - 1);
        		boolean yValid = !(y < 0 || y > level.levelYLength() - 1);
        		
        		if (!xValid || !yValid) {
            		Image wallImage = new Image(Wall.SPRITE);
            		gc.drawImage(wallImage, drawX * CELL_DIMENSIONS, 
            				drawY * CELL_DIMENSIONS);
            	} else {
            		Cell currentCell = level.getCellAt(x, y);
            		Image cellImage = new Image(currentCell.getSprite());
            		gc.drawImage(cellImage, drawX * CELL_DIMENSIONS, 
            				drawY * CELL_DIMENSIONS);
            	}
        		drawEntities(new Vector2D(x, y), new Vector2D(drawX, drawY));
        		
        	}
        }
	}
	
	public void handleInput(KeyEvent e) {
		
	}
	

	/**
	 * Draws all entities to the canvas
	 * @param ent
	 * @param drawPos
	 */
	public void drawEntity(Entity ent, Vector2D drawPos) {
		Image entImage = new Image(ent.getSprite());
		gc.drawImage(entImage, drawPos.getX() * CELL_DIMENSIONS, 
				drawPos.getY() * CELL_DIMENSIONS);
		
	}
	/**
	 * .
	 * @param cellPos
	 * @param drawPos
	 */
	public void drawEntities(Vector2D cellPos,Vector2D drawPos) {
	
        for (Entity ent : level.getEntityList()) {
        	if (cellPos.equals(ent.getVector())) {
        		drawEntity(ent, drawPos);
        	}
        }
        
	}
	
	
	
	/**
	 * draws inventory window to canvas.
	 */
	public void drawInventory() {
		LinkedList<Item> inventory = 
				Level.getCurrentLevel().getPlayer().getInventory();
        int imageNum = 0;
        for (Item item : inventory) {
        	Image itemImage = new Image(item.getSprite());
    		gc.drawImage(itemImage, imageNum * CELL_DIMENSIONS, 
    				LEVEL_LENGTH - CELL_DIMENSIONS);
    		++imageNum;
        }

	}   
	
	
    public void updateExtras() {
    	//Image sideBarImage = new Image("a2/resources/stock photos/sideBar.png");
    	//gc.drawImage(sideBarImage, GAME_WIDTH, 0);
    	//try {
    	//	gc.strokeText(MOTD.getMOTD(), 5, 15, 500);
    	//}
    	//catch(Exception e){
    	//	System.out.println ("MOTD failed to load");
    	//}
    	
		try {
			lbl_MOTD.setText(MOTD.getMOTD());
			lbl_MOTD.setWrapText(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		
    	 
	}
	
	/**
	 * Collectively calls all draw methods.
	 */
	public void drawAll() {
		drawCells();
		drawInventory();
		updateExtras();
        
	}
}
