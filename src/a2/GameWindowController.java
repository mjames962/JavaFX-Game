package a2;


import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import a2.Player.Direction;
import cell.Cell;
import cell.Collectible;
import cell.Wall;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


/**
 * Draws the canvas and allows the user to interact.
 * @author Jensen Beard, George Williams Walton
 * @version 1.6
 */
public class GameWindowController implements Initializable {
	public static final int LEVEL_WIDTH = 350;
	public static final int GAME_WIDTH = 350;
	public static final int GAME_HEIGHT = 350;
	public static final int LEVEL_LENGTH = 400;
	public static final int CELL_DIMENSIONS = 50;
	public static final int MIN_DRAW = 3;
	public static final int MAX_DRAW = 5;
	private static GameWindowController currentController;
	

	private GraphicsContext gc;
	private Level level; 

	
	@FXML 
	private AnchorPane gamePane;
	
	@FXML
	private BorderPane gameBorderPane;
	
	@FXML
	private Canvas gameCanvas;
	
	@FXML
	private Button btnQuit;
	
	@FXML
	private Label lbl_MOTD;
	
	@FXML
	private Label lbl_User;
	
	@FXML
	private Label lbl_TokenCount;
	
	/**
	 * Gets the current controller.
	 * @return currentController
	 */
	public static GameWindowController getCurrentController() {
		return currentController;
	}
	
	/**
	 * Creates and displays canvas in the window & displays message of the day.
	 */
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		show();
		refreshLevel();
		try {
			lbl_MOTD.setText(MOTD.getMOTD());
			lbl_MOTD.setWrapText(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		currentController = this;
	}
	
	/**
	 * Moves the map and redraws the cells.
	 * @param playerMove the direction the player has chose to move
	 */
	public void nextTick(Direction playerMove) {
		level.getPlayer().handleInput(playerMove); 
		//maybe can give responsibility to Level instead
		this.drawAll();
	}	
	/**
	 * Checks user input.
	 * @param sc stores the current scene
	 */
	public void hookInput(Scene sc) {
		sc.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) { 
        		switch (ke.getCode()) {
        			case UP:
        				nextTick(Direction.UP);
        				break;
        			case LEFT:
        				nextTick(Direction.LEFT);
        				break;
        			case DOWN:
        				nextTick(Direction.DOWN);
        				break;
        			case RIGHT:
        				nextTick(Direction.RIGHT);
        				break;
        			default:
        				break;
        		}
            }
        });
	}
	
	//TODO
	/**
	 * Shows the scene containing the game window and switches to it.
	 */
	public void show() {
		Scene scene = new Scene(gamePane);
		Main.switchScene(scene);
		
		hookInput(scene);
	}
	
	/**
	 * Updates the current level.
	 */
	public void refreshLevel() {
		this.level = Level.getCurrentLevel();
        this.gc = gameCanvas.getGraphicsContext2D();
        
        drawAll();
		
	}
	
	/**
	 * Draws the cells on the game map.
	 */
	public void drawCells() {
		Image inventImage = new Image(
				"a2/resources/stock photos/Inventory.png");
    	gc.drawImage(inventImage, 0, GAME_HEIGHT);
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
            		currentCell.draw(gc, drawX * 
            				CELL_DIMENSIONS, drawY * CELL_DIMENSIONS);
            		
            			
            	}
        		drawEntities(new Vector2D(x, y), new Vector2D(drawX, drawY));
        		
        	}
        }
	}

	/**
	 * Draws the entity to the canvas.
	 * @param ent the current entity
	 * @param drawPos the position of the entity
	 */
	public void drawEntity(Entity ent, Vector2D drawPos) {
		Image entImage = new Image(ent.getSprite());
		gc.drawImage(entImage, drawPos.getX() * CELL_DIMENSIONS, 
				drawPos.getY() * CELL_DIMENSIONS);
		
	}
	/**
	 * Draws all entities on the file.
	 * @param cellPos stores the position of the cell
	 * @param drawPos stores the position of an entity to be drawn
	 */
	public void drawEntities(Vector2D cellPos, Vector2D drawPos) {
	
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
	
	
    /**
     * Updates the timer, icon of the player + name.
     */
    public void updateExtras() {
    	
		
    	Image image = new Image("a2/resources/stock photos/Player1.png");
    	ImageView imageView = new ImageView(image);
    	lbl_User.setText(UserData.getCurrentUser().getName());
    	lbl_User.setGraphic(imageView);
    	lbl_TokenCount.setText("Token Count: " + Level.getCurrentLevel().getPlayer().getTokens());
    	
		
    	 
	}
	
	/**
	 * Collectively calls all draw methods.
	 */
	public void drawAll() {
		drawCells();
		drawInventory();
		updateExtras();
        
	}
	
	@FXML
	private void handleQuitBtn(ActionEvent event) throws IOException {
		AnchorPane window = FXMLLoader.load(getClass().
				getResource("resources/fxml docs/MainMenu.fxml"));  
		gamePane.getChildren().setAll(window);
		
	}
}

