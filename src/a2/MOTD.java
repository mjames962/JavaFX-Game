package a2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Handles the message of the day and its assorted puzzles.
 * @author George Williams Walton
 * @version 1.3
 */
public class MOTD {
	private static final int MIN_CHAR = 65;
    private static final int MAX_CHAR = 90;
    private static final String BASE_URL = "http://cswebcat.swan.ac.uk/";
    private static final String PUZZLE_GET = "puzzle";
    private static final String PUZZLE_RESPONSE = "message?solution=";

    
    
    /**
     * Retrieves message of the day.
     * @return Final motd
     * @throws InvalidPuzzleSolutionException If our attempt to solve the puzzle was wrong/too slow.
     * @throws IOException for invalid inputs
     */
    public static String getMOTD() throws IOException, InvalidPuzzleSolutionException {
    	String puzzle = getPuzzle();
    	String solve = solvePuzzle(puzzle);
    	return submitSolvedPuzzle(solve);
    }
    
    /**
     * Gets the puzzle from the webpage.
     * @return The puzzle to solve
     * @throws IOException If our attempt to get the puzzle fails.
     */
    private static String getPuzzle() throws IOException {
        return attemptReadResponse(new URL(BASE_URL + PUZZLE_GET));
    }
    
    /**
     * Moves a character forward or backward in the alphabet.
     * @param chr The character to shift
     * @param forward Forward true Backward false
     * @return The shifted char
     */
    private static char shift(char chr, boolean forward) {
        int shiftNum;
        if (forward) {
            shiftNum = 1;
        } else {
            shiftNum = -1;
        }
        int newchr = chr + shiftNum; 
        if (newchr > MAX_CHAR) {
            return MIN_CHAR;
        } else if (newchr < MIN_CHAR) {
            return MAX_CHAR;
        } else {
            return (char) newchr;
        }
    }
    
    /**
     * Attempts to solve the given puzzle.
     * @param puz Puzzle string
     * @return The solution to the puzzle
     */
    private static String solvePuzzle(String puz) {
        char[] solvedchars = new char[puz.length()];
        boolean forward = true;
        for (int i = 0; i < puz.length(); i++) {
            char chr = puz.charAt(i);
            solvedchars[i] = shift(chr, forward);
            forward = !forward;
        }
        return new String(solvedchars);
    }
    
    /**
     * Attempts to try to get a string response from the given url.
     * @param url The solution url to try
     * @return The string response
     * @throws InvalidPuzzleSolutionException The puzzle solution given was rejected (403)
     * @throws IOException Any other invalid response from the server
     */
    private static String attemptReadResponse(URL url) 
    		throws IOException, InvalidPuzzleSolutionException {
    	Integer response = null;
    	try {
    		HttpURLConnection httpresponse = (HttpURLConnection) 
    				url.openConnection();
        	response = httpresponse.getResponseCode();
    		InputStreamReader stream = new InputStreamReader(url.openStream());
    		BufferedReader in = new BufferedReader(stream);
    		String line = in.readLine();
    		in.close();
            return line;
    	} catch (IOException e) {
    		if ((response != null) && 
    				(response == HttpURLConnection.HTTP_FORBIDDEN)) {
    			throw new InvalidPuzzleSolutionException();
    		} else {
    			throw e;
    		}
    	}
    }
   
    /**
     * Attempts to submit the solved puzzle, and retrieve the final MOTD.
     * @param solvedPuzzle The solution to the puzzle
     * @return Final MOTD if successful
     * @throws InvalidPuzzleSolutionException If the puzzle solution was incorrect
     * @throws IOException Any other invalid response
     */
    private static String submitSolvedPuzzle(String solvedPuzzle) 
    		throws IOException,InvalidPuzzleSolutionException {
    	String solvedPuz = attemptReadResponse(
    			new URL(BASE_URL + PUZZLE_RESPONSE + solvedPuzzle)
    			);
    	return solvedPuz;
    }
}