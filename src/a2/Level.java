package a2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Jensen, Mitch
 *
 */
public class Level {
	
	
	
	
	/**
	 * @param fileName
	 * @return
	 */
	public static Cell[][] readFile(String fileName) {
		File inputFile = new File(fileName);

		Scanner in = null;
		try {
			in = new Scanner(inputFile);
		}
		catch (FileNotFoundException e) {
			System.out.println("Cannot open '" + fileName + "'");
			System.exit(0);
		}

		return Level.readFile(in);
	}
	
	/**
	 * @param in
	 * @return 2D array of Cell type
	 */
	public static Cell[][] readFile(Scanner in){
		
		int xLength = in.nextInt();
		int yLength = in.nextInt();
		
		Cell[][] level = new Cell[xLength][yLength];
		
		in.nextLine();
		
		for (int i = yLength; i > 0; i-- ) {
			for (int j = 0; j < xLength; j++) {
				level[j][i] = new Cell();
			}
		}

		in.nextLine();
		
		
		while (in.hasNextLine()) {
			String temp = in.nextLine();
			while(!temp.equals("*")) {
				readEntity(in);
				temp = in.nextLine();
			}
			
			
			temp = in.nextLine();
			if (!temp.equals("*")) {
				//
			}
			
		}


		return null;
	}
	
	/**
	 * @param in
	 */
	public static void readEntity(Scanner in) {
		int startX = in.nextInt();
		int startY = in.nextInt();
		int entityID = in.nextInt();
		
		in.useDelimiter("~");
		
		String direction = in.next();
		
	}
}
