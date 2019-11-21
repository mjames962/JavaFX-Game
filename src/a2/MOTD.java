package a2;

public class MOTD {
	private static final int MIN_CHAR = 65;
    private static final int MAX_CHAR = 90;
    private static final String BASE_URL = "http://cswebcat.swan.ac.uk/";
    private static final String PUZZLE_GET = "puzzle";
    private static final String PUZZLE_RESPONSE = "message?solution=";

    public static char shift(char c,Boolean forward) {
        int s;
        if (forward) {
            s = 1;
        } else {
            s = -1;
        }
        int tmp = c + s; //possible improvement to algo involving mods
        if (tmp > MAX_CHAR) {
            return MIN_CHAR;
        } else if (tmp < MIN_CHAR) {
            return MAX_CHAR;
        } else {
            return (char) tmp;
        }
    }
    
    public static String solvePuzzle(String puz) {
        char[] solvedchars = new char[puz.length()];
        boolean forward = true;
        for (int i = 0; i < puz.length(); i++) {
            char c = puz.charAt(i);
            System.out.println(c);
            solvedchars[i] = shift(c, forward);
            forward = !forward;
        }
        return new String(solvedchars);
    }
}
