package a2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import a2.Player.Direction;
import cell.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Class for the creation of the level.
 * @author Jensen Beard, Mitch James
 * @version 2.5
 */
public class Level {

	private Cell[][] level;
	private File levelFile;
	private ArrayList<Entity> entityList = new ArrayList<>();
	private int xLength;
	private int yLength;
	private static Level currentLevel;
	public static final String LEVEL_STORAGE = "src/a2/resources/file formats";


	private int levelNo;
	
	
	/**
	 * Reads in the level and starts the timer assuming filename is known.
	 * 			Overloading for different parameters. Used when makign the level
	 * @param fileName name of the file    
	 */
	public Level(String fileName) {
		levelFile = new File(fileName);
		this.readFile();
		currentLevel = this;
		try {
			File levelLeaderboard = new File("src/a2/resources/Leaderboards/LB"
					+ levelFile.getName());
			levelLeaderboard.createNewFile();
		} catch (IOException e) {
			System.out.println("fail");
		}
		Timer.start();
		
	}
	/**
	 * Reads in the level and starts the timer when the file is already made.
	 * 			Overloading for different parameters. Used when the player dies.
	 * @param levelFile the file being reloaded for the level layout
	 */
	public Level(File levelFile) {
		this.levelFile = levelFile;
		this.readFile();
		currentLevel = this;
		try {
			File levelLeaderboard = new File("src/a2/resources/Leaderboards/LB"
					+ levelFile.getName());
			
			
			levelLeaderboard.createNewFile();
		} catch (IOException e) {
			System.out.println("fail");
		}
		Timer.start();
	}
	/**
	 * Returns the current level
	 * @return gives the currently instantiated level
	 */
	public static Level getCurrentLevel() {
		return currentLevel;
	}
	/**
	 * Calls the level to be reloaded in the event of player death.
	 */
	public static void restartLevel() {
		currentLevel = new Level(currentLevel.levelFile);
		GameWindowController.getCurrentController().refreshLevel();
	}
	
	

	/**
	 * Returns the LevelFile.
	 * @return gives the file storing the level being handled.
	 */
	public File getLevelFile() {
		return levelFile;
	}
	

	/**
	 * Method for .
	 * @return entityList list of entities
	 */
	public ArrayList<Entity> getEntityList() {
		return entityList;
	}
	
	

	/**
	 * Method for instantiating entities onto the level.
	 * @param entity is the entity to be added to level       
	 */
	public void addEntity(Entity entity) {
		this.entityList.add(entity);
	}

	/**
	 * Returns the Level being queried.
	 * @return level layout of level
	 */
	public Cell[][] getLevel() {
		return this.level;
	}
	
	/**
	 * Returns the cell type at a requested position.
	 * @param x cell coordinate
	 * @param y cell coordinate
	 * @return level[x][y] cell at (x,y)
	 */
	public Cell getCellAt(int x, int y) {
		if (x == 18 || y == 18) {
			System.out.println("");
		}

		return this.level[x][y];
	}
	/**
	 * gives the type of cell at the requested location.
	 * @param vector the location to look at the cell type
	 * @return the type of cell  at the location
	 */
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
	 * Method for placing cells in the level.
	 * @param cell the cell to be in-putted
	 * @param x coordinate where the cell is going
	 * @param y coordinate where the cell is going
	 *            
	 */
	public void setLevel(Cell cell, int x, int y) {
		this.level[x][y] = cell;
	}
	/**
	 * returns the length of the level in # of cells.
	 * @return gives the length of the level (x axis)
	 */
	public int levelXLength() {
		return this.xLength;
	}
	/**
	 * returns the height of the level in # of cells.
	 * @return gives the height of the level (y axis)
	 */
	public int levelYLength() {
		return this.yLength;
	}
	/**
	 * returns the current entity for player.
	 * @return e , returns the entity (a player) 
	 */
	public Player getPlayer() {
		for (Entity e : this.entityList) { 
		    if (e instanceof Player) {
		    	return (Player) e;
		    }
		}
		
		return null;
	}
	/**
	 * Returns the unique identifier in level names.
	 * @return gives an int to be searched for in level files.
	 */
	public int getLevelNumber() {
		String fileName = levelFile.getName();
		Matcher matcher = Pattern.compile("([0-9]+)\\.txt").matcher(fileName);
		if (matcher.find()) {
			return Integer.parseInt(matcher.group(1));
		} else {
			return -1;
		}
	}
	/**
	 * Returns the unique identifier for a level.
	 * @return gives the int level identifier
	 */
	public String getLevelIdentifier() {
		String fileName = levelFile.getName();
		return fileName.replaceFirst("[0-9]+\\.txt", "");
	}
	/**
	 * Returns the unique identifier for the next level.
	 * @return gives the next level identifier to look for
	 */
	public File getNextLevelFile() {
		int nextLevelNo = getLevelNumber() + 1;
		System.out.println(levelFile.getParent() + "/" + getLevelIdentifier() + nextLevelNo);
		return new File(levelFile.getParent() + "/" + getLevelIdentifier() + nextLevelNo + ".txt");
	}
	/**
	 * Loads the next Level.
	 */
	public void loadNextLevel() {
		File nextLevel = getNextLevelFile();
		if (nextLevel.exists()) {
			currentLevel = new Level(nextLevel);
			GameWindowController.getCurrentController().refreshLevel();
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("All levels completed!");
			alert.setHeaderText("Congratulations!!!");
			//alert.setContentText("Your time was: " + new TimeValue(finishTime).toString());
			alert.showAndWait();
			
		}
	}

	/**
	 * Reads the file for level data.
	 * @param fileName identifies the file extension of the level file
	 * @return Level[][] gives the level layout array
	 */
	public void readFile() {
		
		System.out.println(levelFile.exists());
		
		Scanner in = null;
		try {
			in = new Scanner(levelFile);
		} catch (FileNotFoundException e) {
			System.out.println("Cannot open '" + levelFile.toString() + "'");
		}

		this.readFile(in);
	}
	
	

	/**
	 * Reads files to allow for data permanence.
	 * @param in needed for scanner
	 * @return 2D array of Cell type
	 */
	private void readFile(Scanner in) {

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
			readTokenDoor(line);
			line = in.nextLine();
		}
		line = in.nextLine();

		// teleporters
		while (!line.equals("*")) {
			readTeleporter(line);
			line = in.nextLine();
		}
		line = in.nextLine();
		
		// player inventory
		while (!line.equals("*")) {
			readInventory(line);
			line = in.nextLine();
		}
		
		in.close();
	}
	
	public void readInventory(String str) {
		Scanner in = new Scanner(str);

		Player player = this.getPlayer();
		int redKeys = in.nextInt();
		int greenKeys = in.nextInt();
		int blueKeys = in.nextInt();
		int tokenCount = in.nextInt();
		int daggerCount = in.nextInt();
		Boolean hasFireBoots = in.nextBoolean();
		Boolean hasFlippers = in.nextBoolean();

		for (int i = 0; i < redKeys; i++) {
			player.getInventory().add(new RedKey());
		}

		for (int i = 0; i < greenKeys; i++) {
			player.getInventory().add(new GreenKey());
		}

		for (int i = 0; i < blueKeys; i++) {
			player.getInventory().add(new BlueKey());
		}

		player.setTokenCount(tokenCount);

		for (int i = 0; i < daggerCount; i++) {
			player.getInventory().add(new DaggerItem());
		}

		if (hasFireBoots) {
			player.getInventory().add(new FireBoots());
		}

		if (hasFlippers) {
			player.getInventory().add(new Flippers());
		}

	}
	
	/**
	 * Reads in data for Teleporter Cells.
	 * @param str is the string being read in for the 
	 * 						location of both TP cells in a linked
	 */
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
	 * reads in Entities and their data.
	 * @param in needed for Scanner
	 * @param str is the string read in from file
	 */
	public void readEntity(String str) {
		Scanner in = new Scanner(str);

		int startX = in.nextInt() - 1; // -1 to convert file to 0 indexed array
		int startY = in.nextInt() - 1;
		int entityID = in.nextInt();


		Vector2D vector = new Vector2D(startX, startY);

		Entity entity = null;

		switch (entityID) {
		case 0:
			entity = new Player(vector, entityID, this);
			break;
		case 1:
			entity = new StraightLine(vector, entityID, this);
			break;
		case 2:
			entity = new WallFollowing(vector, entityID, this);
			break;
		case 3:
			entity = new DumbTargeting(vector, entityID, this);
			break;
		case 4:
			entity = new SmartTargetEnemy(vector, entityID, this);
			break;
		default:
			entity = null;
		}

		this.addEntity(entity);
		
		in.close();

	}

	/**
	 * Reading in data for location and required tokens of token doors.
	 * @param str is the string containing relevant data in the file
	 * @param in needed for Scanner
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
	 * @param c the type of cell
	 * @param x coordinate
	 * @param y coordinate         
	 * @return cell gives the newly created cell
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
			case 's':
				return new DaggerCell(position);
			default:
				return new Wall(position);
		}
	}
	
	public void saveLevelProgress(Profile currentUser) throws IOException {

		//Profile currentUser = UserData.getCurrentUser();
		String name = currentUser.getName();
		String levelName = this.levelFile.getName();
		String saveFilePath = LEVEL_STORAGE + "/" + name + "_" + levelName;
		File saveFile = new File(saveFilePath);
		saveFile.delete();
		saveFile.createNewFile();
		
		int xLength = this.levelXLength();
		int yLength = this.levelYLength();
		

		// write x and y length
		Files.write(Paths.get(saveFilePath), (xLength + " " + yLength + "\n").getBytes(),
				StandardOpenOption.APPEND);
		
		Files.write(Paths.get(saveFilePath), ("*" + "\n").getBytes(),
				StandardOpenOption.APPEND);

		
		
		String printLine = "";
		// write level layout
		for (int i = yLength - 1; i >= 0; i--) {
			for (int j = 0; j < xLength; j++) {
				Cell cell = this.getCellAt(j, i);
				printLine = printLine + cell.getChar();
			}
			Files.write(Paths.get(saveFilePath), (printLine + "\n").getBytes(), StandardOpenOption.APPEND);
			printLine = "";
		}
		
		Files.write(Paths.get(saveFilePath), ("*" + "\n").getBytes(),
				StandardOpenOption.APPEND);

		
		printLine = "";
		// write entities
		for (Entity e : this.entityList) {
			printLine = printLine + (e.getVector().getX() + 1) + " ";
			printLine = printLine + (e.getVector().getY() + 1) + " ";
			printLine = printLine + e.getEntityID() + " ";
			//printLine = printLine + e.getDirection();
			
			Files.write(Paths.get(saveFilePath), (printLine + "\n").getBytes(), StandardOpenOption.APPEND);
			printLine = "";
		}
		
		Files.write(Paths.get(saveFilePath), ("*" + "\n").getBytes(),
				StandardOpenOption.APPEND);

		printLine = "";
		// write token doors
		for (int i = yLength - 1; i >= 0; i--) {
			for (int j = 0; j < xLength; j++) {
				Cell cell = this.getCellAt(j, i);
				char cellChar = cell.getChar();
				if (cellChar == 'D') {
					printLine = printLine + (cell.getX() + 1) + " " + (cell.getY() + 1) + " ";
					printLine = printLine + ((TokenDoor) cell).getRequiredTokens();
					
					Files.write(Paths.get(saveFilePath), (printLine + "\n").getBytes(), StandardOpenOption.APPEND);
					printLine = "";
				}
				
			}
		}
		
		Files.write(Paths.get(saveFilePath), ("*" + "\n").getBytes(),
				StandardOpenOption.APPEND);

		printLine = "";
		// write teleporters
		for (int i = yLength - 1; i >= 0; i--) {
			for (int j = 0; j < xLength; j++) {
				Cell cell = this.getCellAt(j, i);
				char cellChar = cell.getChar();
				if (cellChar == 'T') {
					printLine = printLine + (cell.getX() + 1) + " " + (cell.getY() + 1) + " ";
					Vector2D vector = ((Teleporter) cell).getLinkedTP();
					printLine = printLine + (vector.getX() + 1) + " " + (vector.getY() + 1);

					Files.write(Paths.get(saveFilePath), (printLine + "\n").getBytes(), StandardOpenOption.APPEND);
					printLine = "";
				}

			}
		}
		
		Files.write(Paths.get(saveFilePath), ("*" + "\n").getBytes(),
				StandardOpenOption.APPEND);

		printLine = "";
		// write inventory
		LinkedList<Item> inventory = this.getPlayer().getInventory();
		int tokenCount = this.getPlayer().getTokenCount();
		int redKeys = 0;
		int greenKeys = 0;
		int blueKeys = 0;
		int daggerCount = 0;
		boolean fireBoots = false;
		boolean flippers = false;
		
		for (Item i : inventory) {
			switch (i.getItemID()) {
			case (1) :
				redKeys++;
			break;
			case (2) :
				greenKeys++;
			break;
			case (3) :
				blueKeys++;
				break;
			case (4) :
				fireBoots = true;
				break;
			case (5) :
				flippers = true;
				break;
			case (10) :
				daggerCount++;
			default :
			}
		}
		
		printLine = printLine + redKeys + " ";
		printLine = printLine + greenKeys + " ";
		printLine = printLine + blueKeys + " ";
		printLine = printLine + tokenCount + " ";
		printLine = printLine + daggerCount + " ";
		printLine = printLine + fireBoots + " ";
		printLine = printLine + flippers;
		
		Files.write(Paths.get(saveFilePath), (printLine + "\n").getBytes(),
				StandardOpenOption.APPEND);
		
		
		Files.write(Paths.get(saveFilePath), ("*" + "\n").getBytes(),
				StandardOpenOption.APPEND);
	}
	

	/**
	 * Returns the level number.
	 * @return gives the level number
	 */
	public int getLevelNo() {
		return levelNo;
	}
}
