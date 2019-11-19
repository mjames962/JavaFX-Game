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

	private static Cell[][] level;
	private static ArrayList<Entity> entityList = new ArrayList<>();

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
	public static void addEntity(Entity entity) {
		entityList.add(entity);
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
	public static Cell getCellAt(int x, int y) {
		return level[x][y];
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
	public static Cell[][] readFile(String fileName) {
		File inputFile = new File(fileName);

		Scanner in = null;
		try {
			in = new Scanner(inputFile);
		} catch (FileNotFoundException e) {
			System.out.println("Cannot open '" + fileName + "'");
			System.exit(0);
		}

		return Level.readFile(in);
	}

	/**
	 * @param in scanner
	 * @return 2D array of Cell type
	 */
	public static Cell[][] readFile(Scanner in) {

		int xLength = in.nextInt();
		int yLength = in.nextInt();

		Cell[][] level = new Cell[xLength][yLength];

		in.nextLine();
		String line;
		char str;

		do {
		// level grid
		for (int i = yLength; i > 0; i--) {
			line = in.nextLine();
			for (int j = 0; j < xLength; j++) {
				str = line.charAt(j);
				switch(str) {
				case '#':
					level[j][i] = new Wall(new Vector2D(j, i));
					break;
				case '_':
					level[j][i] = new Ground();
					break;
				case 'X':
					level[j][i] = new Cell();
					break;
				case 'T':
					level[j][i] = new Cell();
					break;
				case 'F':
					level[j][i] = new Cell();
					break;
				case 'W':
					level[j][i] = new Cell();
					break;
				case 'R':
					level[j][i] = new Cell();
					break;
				case 'B':
					level[j][i] = new Cell();
					break;
				case 'G':
					level[j][i] = new Cell();
					break;
				case 'w':
					level[j][i] = new Cell();
					break;
				case 'f':
					level[j][i] = new Cell();
					break;
				case 'r':
					level[j][i] = new Cell();
					break;
				case 'b':
					level[j][i] = new Cell();
					break;
				case 'g':
					level[j][i] = new Cell();
					break;
				case 'D':
					level[j][i] = new Cell();
					break;
				case 'd':
					level[j][i] = new Cell();
					break;
				default:
					break;
				}
			}
		}
} while();

		in.nextLine();

		String temp = in.nextLine();

		// entity info
		while (!temp.equals("*")) {
			readEntity(in);
			temp = in.nextLine();
		}

		// token doors
		temp = in.nextLine();
		if (!temp.equals("*")) {
			readTokenDoor(in);
		}

		return level;
	}

	/**
	 * @param in scanner
	 */
	public static void readEntity(Scanner in) {
		int startX = in.nextInt();
		int startY = in.nextInt();
		int entityID = in.nextInt();

		in.useDelimiter("~");

		String direction = in.next();

		Vector2D vector = new Vector2D(startX, startY);

		addEntity(new Entity(vector, entityID));

	}

	/**
	 * @param in
	 */
	public static void readTokenDoor(Scanner in) {
		int xPosition = in.nextInt();
		int yPosition = in.nextInt();
		Cell door = getCellAt(xPosition, yPosition);

		in.useDelimiter("~");

		String tokenCount = in.next();

		// update object info

	}
}
