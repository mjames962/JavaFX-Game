package a2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Jensen, Mitch
 *
 */
public class Level {

	private Cell[][] level;
	private ArrayList<Entity> entityList = new ArrayList<>();

	public int levelNo;

	/**
	 * @param fileName
	 */
	public Level(String fileName) {
		this.level = readFile(fileName);
	}

	/**
	 * @return entityList
	 */
	public ArrayList<Entity> getEntityList() {
		return entityList;
	}

	/**
	 * @param entity entity to be added to level
	 */
	public void addEntity(Entity entity) {
		this.entityList.add(entity);
	}

	/**
	 * @return level layout of level
	 */
	public Cell[][] getLevel() {
		return this.level;
	}

	/**
	 * @param x
	 * @param y
	 * @return level[x][y] cell at coords
	 */
	public Cell getCellAt(int x, int y) {
		return this.level[x][y];
	}

	/**
	 * @param cell cell to be input
	 * @param x    x-coord
	 * @param y    y-coord
	 */
	public void setLevel(Cell cell, int x, int y) {
		this.level[x][y] = cell;
	}

	/**
	 * @param fileName the name and file extension of the level file
	 * @return Level[][] level layout array
	 */
	public Cell[][] readFile(String fileName) {
		File inputFile = new File(fileName);

		Scanner in = null;
		try {
			in = new Scanner(inputFile);
		} catch (FileNotFoundException e) {
			System.out.println("Cannot open '" + fileName + "'");
			System.exit(0);
		}

		return readFile(in);
	}

	/**
	 * @param in scanner
	 * @return 2D array of Cell type
	 */
	public Cell[][] readFile(Scanner in) {

		int x = in.nextInt();
		int y = in.nextInt();

		in.nextLine();
		in.nextLine();

		Cell[][] level = new Cell[x][y];

		String line = in.nextLine();
		char c;

		// level grid
		for (int i = y - 1; i >= 0; i--) {
			for (int j = 0; j < x; j++) {
				c = line.charAt(j);
				level[j][i] = readChar(c, j, y);
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
			// readTeleporter(line);
			line = in.nextLine();
		}

		return level;
	}

	private static void readTeleporter(String str) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param in scanner
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

	}

	/**
	 * 
	 * @param in
	 */
	public void readTokenDoor(String str) {
		Scanner in = new Scanner(str);

		int xPosition = in.nextInt();
		int yPosition = in.nextInt();
		Cell door = getCellAt(xPosition, yPosition);

		String tokenCount = in.next();

		// update object info

	}

	/**
	 * Checks cell type and creates corresponding cell.
	 * 
	 * @param c cell type
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
			return new ColourDoor(position);
		case 'B':
			return new ColourDoor(position);
		case 'G':
			return new ColourDoor(position);
		case 'w':
			return new Flippers(position);
		case 'f':
			return new FireBoots(position);
		case 'r':
			return new Key(position, Player.Item.RED_KEY);
		case 'b':
			return new Key(position, Player.Item.BLUE_KEY);
		case 'g':
			return new Key(position, Player.Item.GREEN_KEY);
		case 'D':
			return new TokenDoor(position);
		case 'd':
			return new Token(position);
		default:
			return new Wall(position);
		}
	}

	public int getLevelNo() {
		return levelNo;
	}
}
