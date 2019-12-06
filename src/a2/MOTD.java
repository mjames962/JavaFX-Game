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
    
    private static String attemptReadResponse(URL url) throws IOException {
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
    		if ((response != null) && (response
    				== HttpURLConnection.HTTP_FORBIDDEN)) {
    			throw new InvalidPuzzleSolutionException();
    		} else {
    			throw e;
    		}
    	}
    }
    
    private static String getPuzzle() throws IOException {
    	String puz = attemptReadResponse(new URL(BASE_URL + PUZZLE_GET));
        return puz;
    }
   
    private static String submitSolvedPuzzle(String solvedPuzzle) 
    		throws IOException {
    	String solvedPuz = attemptReadResponse(
    			new URL(BASE_URL + PUZZLE_RESPONSE + solvedPuzzle)
    			);
    	return solvedPuz;
    }
    
    //Could be useful to handle no connection differently 
    //-> catch UnknownHostException
    //In possiblility we are too slow to get solution 
    //-> catch InvalidPuzzleSolutionException
    //Other errors -> IOException
    
    
    /**
     * Retrieves message of the day becuz Liam sez so.
     * @return gives the solution to the puzzle to satisfy Liam
     * @throws IOException for invalid inputs
     */
    public static String getMOTD() throws IOException {
    	String puzzle = getPuzzle();
    	String solve = solvePuzzle(puzzle);
    	return submitSolvedPuzzle(solve);
    }
    
}
