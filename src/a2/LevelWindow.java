package a2;

import cell.Cell;
import cell.Wall;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class LevelWindow {

	public static final int LEVEL_WIDTH = 300;
	public static final int LEVEL_LENGTH = 300;
	public static final int CELL_DIMENSIONS = 50;
	public static final int MIN_DRAW = 3;
	public static final int MAX_DRAW = 4;
	
	private Stage levelStage;
	private Group root;
	private Canvas canvas;
	private Scene scene;
	private GraphicsContext gc;
	private Level level; 

	public LevelWindow(Level level) {
		this.levelStage = new Stage();
		this.root = new Group();
        this.canvas = new Canvas(LEVEL_WIDTH, LEVEL_LENGTH);
        root.getChildren().add(canvas);
        this.scene = new Scene(root);
        levelStage.setScene(scene);
        levelStage.show();
        this.gc = canvas.getGraphicsContext2D();
        this.level = level;
        scene.addEventFilter(KeyEvent.KEY_PRESSED, new KeyboardHandler(this,level));
        drawAll();
		
		// TODO Auto-generated constructor stub
	}
	
	public void drawCells() {
		int playerX = level.getPlayer().getVector().getX();
        int playerY = level.getPlayer().getVector().getY();
		for (int x = playerX - MIN_DRAW; x < playerX + MAX_DRAW; x++) {
        	for (int y = playerY - MIN_DRAW; y < playerY + MAX_DRAW; y++) {
        		int drawX = playerX - x + MIN_DRAW;
        		int drawY = y - playerY + MIN_DRAW;
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
        		drawEntities(new Vector2D(x,y), new Vector2D(drawX,drawY));
        		
        	}
        }
	}
	

	/**
	 * .
	 * @param ent
	 * @param drawPos
	 */
	public void drawEntity(Entity ent, Vector2D drawPos) {
		Image entImage = new Image(ent.getSprite());
		gc.drawImage(entImage, drawPos.getX() * CELL_DIMENSIONS, drawPos.getY() * CELL_DIMENSIONS);
		
	}
	
	/**
	 * .
	 * @param cellPos
	 * @param drawPos
	 */
	public void drawEntities(Vector2D cellPos,Vector2D drawPos) {
	
        for (Entity ent : level.getEntityList()) {
        	if (cellPos.equals(ent.getVector())) {
        		drawEntity(ent,drawPos);
        	}
        }
        
	}
	
	/**
	 * 
	 */
	public void drawAll() {
		drawCells();
 
        
	}

}
