package a2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import cell.*;

/**
 * class for the creation of the level.
 * 
 * @author Jensen, Mitch
 *
 */
public class Level {

	private Cell[][] level;
	private ArrayList<Entity> entityList = new ArrayList<>();
	private int xLength;
	private int yLength;

	private int levelNo;

	/**
	 * .
	 * 
	 * @param fileName
	 *            name of the file
	 */
	public Level(String fileName) {
		this.readFile(fileName);
	}

	/**
	 * .
	 * 
	 * @return entityList list of entities
	 */
	public ArrayList<Entity> getEntityList() {
		return entityList;
	}

	/**
	 * .
	 * 
	 * @param entity
	 *            entity to be added to level
	 */
	public void addEntity(Entity entity) {
		this.entityList.add(entity);
	}

	/**
	 * .
	 * 
	 * @return level layout of level
	 */
	public Cell[][] getLevel() {
		return this.level;
	}

	/**
	 * .
	 * 
	 * @param x
	 *            coordinate
	 * @param y
	 *            coordinate
	 * @return level[x][y] cell at coords
	 */
	public Cell getCellAt(int x, int y) {

		return this.level[x][y];
	}
	
	public Cell getCellAt(Vector2D vector) {
		int xCoord = vector.getX();
		int yCoord = vector.getY();
		
		return getCellAt(xCoord, yCoord);
	}

	/**
	 * Converts the requested cell into a ground cell.
	 * @param x cell x coordinate
	 * @param y cell y coordinate
	 */
	public void setCellAt(int x, int y) {
		this.level[x][y] = readChar('_', x, y);
	}
	
	/**
	 * placing cells in the level.
	 * 
	 * @param cell
	 *            cell to be input
	 * @param x
	 *            x-coord
	 * @param y
	 *            y-coord
	 */
	public void setLevel(Cell cell, int x, int y) {
		this.level[x][y] = cell;
	}
	/**
	 * returns the length of the level in # of cells.
	 * @return the length of the level
	 */
	public int levelXLength() {
		return this.xLength;
	}
	/**
	 * returns the height of the level in # of cells.
	 * @return the height of the level
	 */
	public int levelYLength() {
		return this.yLength;
	}
	/**
	 * returns the current player.
	 * @return e , the player
	 */
	public Entity getPlayer() {
		for (Entity e : this.entityList) { 
		    if (e.getEntityID() == 0) {
		    	return e;
		    }
		}
		
		return null;
	}

	/**
	 * reads the file for level data.
	 * 
	 * @param fileName
	 *            the name and file extension of the level file
	 * @return Level[][] level layout array
	 */
	public void readFile(String fileName) {
		File inputFile = new File(fileName);
		System.out.println(inputFile.exists());
		Scanner in = null;
		try {
			in = new Scanner(inputFile);
		} catch (FileNotFoundException e) {
			System.out.println("Cannot open '" + fileName + "'");
			System.exit(0);
		}

		this.readFile(in);
	}

	/**
	 * reads files.
	 * 
	 * @param in
	 *            scanner
	 * @return 2D array of Cell type
	 */
	public void readFile(Scanner in) {

		int x = in.nextInt();
		this.xLength = x;
		
		int y = in.nextInt();
		this.yLength = y;

		in.nextLine();
		in.nextLine();

		this.level = new Cell[x][y];

		String line = in.nextLine();
		char c;

		// level grid
		for (int i = y - 1; i >= 0; i--) {
			for (int j = 0; j < x; j++) {
				c = line.charAt(j);
				Cell cell = readChar(c, j, i);
				this.setLevel(cell, j, i);
			}
			line = in.nextLine();
		}

		line = in.nextLine();

		// entity info
		while (!line.equals("*")) {
			readEntity(line);
			line = in.nextLine();
		}
		line = in.nextLine();

		// token doors
		while (!line.equals("*")) {
			// readTokenDoor(line);
			line = in.nextLine();
		}
		line = in.nextLine();

		// teleporters
		while (!line.equals("*")) {
			readTeleporter(line);
			line = in.nextLine();
		}
		
		in.close();
	}

	private void readTeleporter(String str) {
		Scanner in = new Scanner(str);

		int xPosition1 = in.nextInt() - 1; // -1 because of 0 indexed array
		int yPosition1 = in.nextInt() - 1;
		Cell teleporter1 = this.getCellAt(xPosition1, yPosition1);

		int xPosition2 = in.nextInt() - 1;
		int yPosition2 = in.nextInt() - 1;
		Cell teleporter2 = this.getCellAt(xPosition2, yPosition2);

		((Teleporter) teleporter1).setLinks((Teleporter) teleporter2);
		
		in.close();
	}

	/**
	 * reads in Entities + ajoined data.
	 * 
	 * @param in
	 *            integral to Scanner
	 * @param str
	 *            a string read in from file
	 */
	public void readEntity(String str) {
		Scanner in = new Scanner(str);

		int startX = in.nextInt() - 1; // -1 to convert file to 0 indexed array
		int startY = in.nextInt() - 1;
		int entityID = in.nextInt();

		String direction = in.next();

		Vector2D vector = new Vector2D(startX, startY);

		Entity entity = null;

		switch (entityID) {
		case 0:
			entity = new Player(vector, entityID, false, null);
			break;
		case 1:
			entity = new StraightLine(vector, entityID);
			break;
		case 2:
			entity = new WallFollowing(vector, entityID);
			break;
		case 3:
			entity = new DumbTargeting(vector, entityID);
			break;
		case 4:
			// entity = new SmartTargeting();
			break;
		default:
			entity = null;
		}

		this.addEntity(entity);
		
		in.close();

	}

	/**
	 * reading in token doors.
	 * 
	 * @param str
	 *            needed for reading in from file
	 * @param in
	 *            integral to Scanner
	 */
	public void readTokenDoor(String str) {
		Scanner in = new Scanner(str);

		int xPosition = in.nextInt() - 1; // -1 because of 0 indexed array
		int yPosition = in.nextInt() - 1;
		Cell door = getCellAt(xPosition, yPosition);

		int tokenCount = in.nextInt();

		((TokenDoor) door).setReqTokens(tokenCount);
		in.close();

	}

	/**
	 * Checks cell type and creates corresponding cell.
	 * 
	 * @param c
	 *            cell type
	 * @param x
	 *            x coordinate
	 * @param y
	 *            y coordinate
	 * @return cell newly created cell
	 */
	public Cell readChar(char c, int x, int y) {
		Vector2D position = new Vector2D(x, y);

		switch (c) {
		case '#':
			return new Wall(position);
		case '_':
			return new Ground(position);
		case 'X':
			return new Goal(position);
		case 'T':
			return new Teleporter(position);
		case 'F':
			return new Fire(position);
		case 'W':
			return new Water(position);
		case 'R':
			return new RedDoor(position);
		case 'B':
			return new BlueDoor(position);
		case 'G':
			return new GreenDoor(position);
		case 'w':
			return new FlippersCell(position);
		case 'f':
			return new FireBootsCell(position);
		case 'r':
			return new RedKeyCell(position);
		case 'b':
			return new BlueKeyCell(position);
		case 'g':
			return new GreenKeyCell(position);
		case 'D':
			return new TokenDoor(position);
		case 'd':
			return new TokenCell(position);
		default:
			return new Wall(position);
		}
	}

	/**
	 * gives the level number.
	 * 
	 * @return returns the level number
	 */
	public int getLevelNo() {
		return levelNo;
	}
}
