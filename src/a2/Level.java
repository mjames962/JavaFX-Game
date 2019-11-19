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

		String line = in.nextLine();
		char c;

		do {
			// level grid
			for (int i = yLength; i > 0; i--) {
				line = in.nextLine();
				for (int j = 0; j < xLength; j++) {
					c = line.charAt(j);
					level[j][i] = readChar(c);
				}
			}
		} while (!line.equals("*"));

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

		// teleporters
		temp = in.nextLine();
		if (!temp.equals("*")) {
			readTeleporter(in);
		}

		return level;
	}

	private static void readTeleporter(Scanner in) {
		// TODO Auto-generated method stub

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
	 * 
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

	/**
	 * Checks cell type and creates corresponding cell.
	 * @param c cell type
	 * @return cell newly created cell
	 */
	public static Cell readChar(char c) {
		switch (c) {
			case '#':
				return new Wall(new Vector2D(j, i));
			case '_':
				return new Ground();
			case 'X':
				return new Cell();
			case 'T':
				return new Cell();
			case 'F':
				return new Cell();
			case 'W':
				return new Cell();
			case 'R':
				return new Cell();
			case 'B':
				return new Cell();
			case 'G':
				return new Cell();
			case 'w':
				return new Cell();
			case 'f':
				return new Cell();
			case 'r':
				return new Cell();
			case 'b':
				return new Cell();
			case 'g':
				return new Cell();
			case 'D':
				return new Cell();
			case 'd':
				return new Cell();
			default:
				return new Cell();
		}
	}
}
